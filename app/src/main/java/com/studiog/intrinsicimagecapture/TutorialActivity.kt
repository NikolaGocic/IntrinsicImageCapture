package com.studiog.intrinsicimagecapture

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.studiog.intrinsicimagecapture.ui.TutorialScreen

class TutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            TutorialScreen(this)
        }
    }

    fun goToGallery(){
        val intent = Intent(this, ProcessingGallery::class.java)
        startActivity(intent)
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up )
    }

    fun goToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition( R.anim.slide_out_down,R.anim.slide_in_down)
    }



}