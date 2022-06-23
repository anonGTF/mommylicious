package com.mommylicious.mobile.ui.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.mommylicious.mobile.base.BaseFragment
import com.mommylicious.mobile.data.model.Record
import com.mommylicious.mobile.databinding.FragmentRecordListBinding
import com.mommylicious.mobile.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordListFragment(private val type: String, private val isMale: Boolean) : BaseFragment<FragmentRecordListBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecordListBinding =
        FragmentRecordListBinding::inflate

    private val viewModel: RecordViewModel by activityViewModels()

    override fun setup() {
        viewModel.getAllRecords().observe(viewLifecycleOwner, setupRecordsObserver())
    }

    private fun setupRecordsObserver() = setObserver<List<Record>?>(
        onSuccess = {
            populateData(it.data ?: listOf())
        }
    )

    private fun populateData(data: List<Record>) {
        val defaultData = getData().map { AASeriesElement().data(it.toTypedArray()) }.toTypedArray()
        val aggregatedData = aggregate(data)
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Line)
            .markerRadius(0.0f)
            .legendEnabled(false)
            .series(arrayOf(
                *defaultData,
                AASeriesElement()
                    .name(type)
                    .data(aggregatedData.toTypedArray()),
            ))

        binding.chart.aa_drawChartWithChartModel(aaChartModel)

        setupStatus(aggregatedData)
    }

    private fun setupStatus(data: List<Double>) {
        val (sd1, sdneg1) = getSd1()
        val (sd2, sdneg2) = getSd2()
        val last = data.last()
        val idx = data.lastIndex
        var severity = "normal"

        if (last > sd2[idx] || last < sdneg2[idx]) {
            severity = "critical"
        } else if (last > sd1[idx] || last < sdneg1[idx]) {
            severity = "danger"
        }

        val title = when(severity) {
            "normal" -> "Pertumbuhan anak Anda sangat baik"
            "danger" -> "Pertumbuhan anak kurang baik"
            "critical" -> "Anak Anda terancam stunting"
            else -> ""
        }

        val message = when(severity) {
            "normal" -> "Pertahankan dan jaga selalu pola asuh serta nutrisi Anak Anda. Dengan mengikuti saran dari aplikasi ini, anak Anda terjamin dari ancaman stunting."
            "danger" -> "lakukan konsultasi dengan dokter tentang anak atau menjadwalkan ke dokter spesialis anak untuk pemeriksaan dan penanganan lebih lanjut"
            "critical" -> "Segera konsultasikan dengan dokter tentang pola asuh dan nutrisi Anak Anda! Dokter akan menyarankan tindakan dan pola asuh yang sesuai agar Anak Anda terhidar dari Stunting."
            else -> ""
        }

        with(binding) {
            tvInfoTitle.text = title
            tvInfo.text = message
        }
    }

    private fun aggregate(data: List<Record>): List<Double> = data.map {
            when (type) {
                "Tinggi" -> it.height
                "Berat" -> it.weight
                else -> it.head
            }
        }

    private fun getData() = if (isMale) getDataMale() else getDataFemale()

    private fun getDataFemale() = when(type) {
        "Tinggi" -> arrayOf(HEIGHT_GIRLS_SD2NEG, HEIGHT_GIRLS_SD1NEG, HEIGHT_GIRLS_SD0, HEIGHT_GIRLS_SD1, HEIGHT_GIRLS_SD2)
        "Berat" -> arrayOf(WEIGHT_GIRLS_SD2NEG, WEIGHT_GIRLS_SD1NEG, WEIGHT_GIRLS_SD0, WEIGHT_GIRLS_SD1, WEIGHT_GIRLS_SD2)
        else -> arrayOf(HEAD_GIRLS_SD2NEG, HEAD_GIRLS_SD1NEG, HEAD_GIRLS_SD0, HEAD_GIRLS_SD1, HEAD_GIRLS_SD2)
    }

    private fun getDataMale() = when(type) {
        "Tinggi" -> arrayOf(HEIGHT_BOYS_SD2NEG, HEIGHT_BOYS_SD1NEG, HEIGHT_BOYS_SD0, HEIGHT_BOYS_SD1, HEIGHT_BOYS_SD2)
        "Berat" -> arrayOf(WEIGHT_BOYS_SD2NEG, WEIGHT_BOYS_SD1NEG, WEIGHT_BOYS_SD0, WEIGHT_BOYS_SD1, WEIGHT_BOYS_SD2)
        else -> arrayOf(HEAD_BOYS_SD2NEG, HEAD_BOYS_SD1NEG, HEAD_BOYS_SD0, HEAD_BOYS_SD1, HEAD_BOYS_SD2)
    }

    private fun getSd1() = if (isMale) getSd1Male() else getSd1Female()

    private fun getSd1Male() = when (type) {
        "Tinggi" -> Pair(HEIGHT_BOYS_SD1, HEIGHT_BOYS_SD1NEG)
        "Berat" -> Pair(WEIGHT_BOYS_SD1, WEIGHT_BOYS_SD1NEG)
        else -> Pair(HEAD_BOYS_SD1, HEAD_BOYS_SD1NEG)
    }

    private fun getSd1Female() = when (type) {
        "Tinggi" -> Pair(HEIGHT_GIRLS_SD1, HEIGHT_GIRLS_SD1NEG)
        "Berat" -> Pair(WEIGHT_GIRLS_SD1, WEIGHT_GIRLS_SD1NEG)
        else -> Pair(HEAD_GIRLS_SD1, HEAD_GIRLS_SD1NEG)
    }

    private fun getSd2() = if (isMale) getSd2Male() else getSd2Female()

    private fun getSd2Male() = when (type) {
        "Tinggi" -> Pair(HEIGHT_BOYS_SD2, HEIGHT_BOYS_SD2NEG)
        "Berat" -> Pair(WEIGHT_BOYS_SD2, WEIGHT_BOYS_SD2NEG)
        else -> Pair(HEAD_BOYS_SD2, HEAD_BOYS_SD2NEG)
    }

    private fun getSd2Female() = when (type) {
        "Tinggi" -> Pair(HEIGHT_GIRLS_SD2, HEIGHT_GIRLS_SD2NEG)
        "Berat" -> Pair(WEIGHT_GIRLS_SD2, WEIGHT_GIRLS_SD2NEG)
        else -> Pair(HEAD_GIRLS_SD2, HEAD_GIRLS_SD2NEG)
    }
}