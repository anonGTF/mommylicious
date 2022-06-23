package com.mommylicious.mobile.ui.menu

import android.content.Context
import android.content.Intent
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import com.mommylicious.mobile.base.BaseActivity
import com.mommylicious.mobile.data.model.Menu
import com.mommylicious.mobile.databinding.ActivityDetailMenuBinding
import com.mommylicious.mobile.utils.OrderedListSpan

class DetailMenuActivity : BaseActivity<ActivityDetailMenuBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityDetailMenuBinding =
        ActivityDetailMenuBinding::inflate

    override fun setup() {
        val bahanBuilder = SpannableStringBuilder()
        BAHAN.forEachIndexed { index, item ->
            bahanBuilder.append(
                "$item\n\n",
                OrderedListSpan(WIDTH, "${index + 1}."),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        val prosedurBuilder = SpannableStringBuilder()
        PROSEDUR.forEachIndexed { index, item ->
            prosedurBuilder.append(
                "$item\n\n",
                OrderedListSpan(WIDTH, "${index + 1}."),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        with(binding) {
            tvBahan.text = bahanBuilder
            tvProsedur.text = prosedurBuilder
        }
    }

    companion object {
        const val EXTRA_DATA = "data"
        const val WIDTH = 70
        val PROSEDUR = arrayOf(
            "siapkan panci, kemudian tuang air secukupnya dan rebus hingga mendidih",
            "setelah airnya mendidih, masukkan bawang merah dan bawang putih yang telah diiris tipis-tipis. Masak bumbu hingga tercium aroma harum",
            "masukkan jagung manis dan wortel ke dalam panic. Rebus hingga matang dan empuk",
            "masukkan garam sambil diaduk-aduk. Kemudian masukkan daun bayam dan gula pasir. Aduk-aduk hingga tercampur rata",
            "koreksi rasa sayur bayam. Jika dirasa kurang, tambahkan gula pasir atau garam",
            "jika sudah mendidih, masukkan tomat yang sudah dipotong-potong, aduk sebentar, kemudian angkat. Sajikan sayur bayam di dalam mangkuk dan nikmati selagi hangat"
        )
        val BAHAN = arrayOf(
            "25gr Kentang, potong dadu",
            "15gr Daging Sapi Giling",
            "15gr Wortel",
            "10gr Tomat",
            "1 Siung Bawang Putih",
            "1 Siung Bawang Merah"
        )
        fun startActivity(ctx: Context, data: Menu) {
            val intent = Intent(ctx, DetailMenuActivity::class.java)
            intent.putExtra(EXTRA_DATA, data)
            ctx.startActivity(intent)
        }
    }
}