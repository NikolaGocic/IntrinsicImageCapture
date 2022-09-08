package com.studiog.intrinsicimagecapture

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class ImageProcessingActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_processing)

        webView = findViewById(R.id.webview)

        val  webSettings : WebSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.webViewClient=WebViewClient()
        webView.loadUrl("https://portal.quantcyte.org/login")

    }

}