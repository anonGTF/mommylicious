package com.mommylicious.mobile.ui.auth

import android.content.Intent
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.afollestad.vvalidator.form
import com.mommylicious.mobile.R
import com.mommylicious.mobile.base.BaseActivity
import com.mommylicious.mobile.databinding.ActivityRegisterBinding
import com.mommylicious.mobile.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityRegisterBinding =
        ActivityRegisterBinding::inflate
    private val viewModel: AuthViewModel by viewModels()

    override fun setup() {
        form {
            useRealTimeValidation()
            input(binding.etName, name = null) {
                isNotEmpty().description("Nama wajib diisi")
            }

            input(binding.etNik, name = null) {
                isNotEmpty().description("NIK wajib diisi")
            }

            input(binding.etEmail, name = null) {
                isNotEmpty().description("Email wajib diisi")
                isEmail().description("Silahkan masukan email yang valid!")
            }

            input(binding.etPassword, name = null) {
                isNotEmpty().description("Password wajib diisi")
            }

            submitWith(binding.btnRegister) {
                viewModel.register(binding.etName.text.toString(), binding.etNik.text.toString(),
                    binding.etEmail.text.toString(), binding.etPassword.text.toString())
                    .observe(this@RegisterActivity, setRegisterObserver())
            }
        }
        val spannable = SpannableStringBuilder("Sudah punya akun? Masuk")
        spannable.setSpan(
            ForegroundColorSpan(getColorResource(R.color.pink_300)),
            18, // start
            23, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        binding.tvLogin.text = spannable
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun setRegisterObserver() = setObserver<Void?>(
        onSuccess = {
            binding.progressBar.gone()
            showToast("Registrasi berhasil")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        },
        onError = {
            binding.progressBar.gone()
            showToast(it.message.toString())
        },
        onLoading = { binding.progressBar.visible() }
    )
}