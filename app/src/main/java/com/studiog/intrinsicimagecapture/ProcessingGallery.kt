package com.studiog.intrinsicimagecapture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.studiog.intrinsicimagecapture.ui.MainScreen
import com.studiog.intrinsicimagecapture.ui.ProcessingGallery

class ProcessingGallery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                ProcessingGallery(processingGallery = this)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition( R.anim.slide_out_down,R.anim.slide_in_down)
    }
}