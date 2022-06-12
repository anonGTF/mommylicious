package com.mommylicious.mobile.ui.article

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.webkit.WebView
import android.webkit.WebViewClient
import com.mommylicious.mobile.BuildConfig
import com.mommylicious.mobile.base.BaseActivity
import com.mommylicious.mobile.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : BaseActivity<ActivityDetailArticleBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityDetailArticleBinding =
        ActivityDetailArticleBinding::inflate

    private var link = ""

    override fun setup() {
        WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
        setupBackButton()
        link = intent.getStringExtra(EXTRA_DATA).orEmpty()
        loadPage()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadPage() {
        Log.d("coba", link)
        with(binding.wvArticle) {
            settings.domStorageEnabled = true
            settings.allowContentAccess = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (url != null) {
                        view?.loadUrl(url)
                    }
                    return true
                }
            }
            loadUrl(link)
        }
    }

    companion object {
        const val EXTRA_DATA = "data"
        fun startActivity(ctx: Context, link: String) {
            val intent = Intent(ctx, DetailArticleActivity::class.java)
            intent.putExtra(EXTRA_DATA, link)
            ctx.startActivity(intent)
        }
    }
}