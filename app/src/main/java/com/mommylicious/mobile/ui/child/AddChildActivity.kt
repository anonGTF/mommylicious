package com.mommylicious.mobile.ui.child

import android.app.DatePickerDialog
import android.content.Intent
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.afollestad.vvalidator.form
import com.mommylicious.mobile.R
import com.mommylicious.mobile.base.BaseActivity
import com.mommylicious.mobile.data.model.Child
import com.mommylicious.mobile.databinding.ActivityAddChildBinding
import com.mommylicious.mobile.ui.main.MainActivity
import com.mommylicious.mobile.utils.toString
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddChildActivity : BaseActivity<ActivityAddChildBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityAddChildBinding =
        ActivityAddChildBinding::inflate

    private val viewModel: ChildViewModel by viewModels()
    private var gender: String = ""

    override fun setup() {
        setupDropdown()
        setupDatePicker()
        form {
            useRealTimeValidation()
            input(binding.etName, name = null) {
                isNotEmpty().description("Nama wajib diisi")
            }

            input(binding.etBirthdate, name = null) {
                isNotEmpty().description("Tanggal lahir wajib diisi")
            }

            input(binding.inputGender, name = null) {
                isNotEmpty().description("Jenis kelamin wajib diisi")
            }

            input(binding.etMaternityAge, name = null) {
                isNotEmpty().description("Usia kehamilan wajib diisi")
            }

            submitWith(binding.btnAddChild) {
                viewModel.addChild(binding.etName.text.toString(), binding.etBirthdate.text.toString(),
                gender, binding.etMaternityAge.text.toString().toInt())
                    .observe(this@AddChildActivity, setChildObserver())
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

                binding.etBirthdate.setText(cal.time.toString("dd/MM/yyyy"))
            }

        binding.etBirthdate.setOnClickListener {
            DatePickerDialog(this@AddChildActivity,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun setupDropdown() {
        val genderAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this, R.layout.item_gender, GENDER
        )
        binding.inputGender.setAdapter(genderAdapter)

        binding.inputGender.setOnItemClickListener { _, _, position, _ ->
            gender = GENDER[position]
        }
    }

    private fun setChildObserver() = setObserver<Child?>(
        onSuccess = {
            binding.progressBar.gone()
            viewModel.saveChildId(it.data?.childId.orEmpty())
            showToast("Data anak berhasil ditambahkan")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        },
        onError = {
            binding.progressBar.gone()
            showToast(it.message.toString())
        },
        onLoading = { binding.progressBar.visible() }
    )

    companion object {
        val GENDER = listOf("Laki-laki", "Perempuan")
    }
}