package com.mommylicious.mobile.ui.record

import android.app.DatePickerDialog
import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.afollestad.vvalidator.form
import com.mommylicious.mobile.base.BaseActivity
import com.mommylicious.mobile.data.model.Record
import com.mommylicious.mobile.databinding.ActivityAddRecordBinding
import com.mommylicious.mobile.ui.main.MainActivity
import com.mommylicious.mobile.utils.toString
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddRecordActivity : BaseActivity<ActivityAddRecordBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityAddRecordBinding =
        ActivityAddRecordBinding::inflate

    private val viewModel: RecordViewModel by viewModels()

    override fun setup() {
        setupDatePicker()
        form {
            useRealTimeValidation()
            input(binding.etDate, name = null) {
                isNotEmpty().description("Tanggal wajib diisi")
            }

            input(binding.etHeight, name = null) {
                isNotEmpty().description("Tinggi badan wajib diisi")
            }

            input(binding.etWeight, name = null) {
                isNotEmpty().description("Berat badan wajib diisi")
            }

            input(binding.etHead, name = null) {
                isNotEmpty().description("Lingkar kepala wajib diisi")
            }

            submitWith(binding.btnAddRecord) {
                viewModel.addRecord(binding.etHeight.text.toString().toDouble(),
                    binding.etWeight.text.toString().toDouble(),
                    binding.etHead.text.toString().toDouble(),
                    binding.etDate.text.toString())
                    .observe(this@AddRecordActivity, setRecordObserver())
            }
        }
    }

    private fun setupDatePicker() {
        val cal = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                binding.etDate.setText(cal.time.toString("dd/MM/yyyy"))
            }

        binding.etDate.setOnClickListener {
            DatePickerDialog(this@AddRecordActivity,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun setRecordObserver() = setObserver<Record?>(
        onSuccess = {
            showToast("Data berhasil ditambahkan")
            startActivity(Intent(this, MainActivity::class.java))
        }
    )
}