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

class TutorialActivity : AppCompatActivity() {

    lateinit var videoView: VideoView
    lateinit var videoView2: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        videoView =findViewById(R.id.videoView)
        videoView2 =findViewById(R.id.videoView2)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        val mediaController2 = MediaController(this)
        mediaController2.setAnchorView(videoView2)

        val settingUpUri = Uri.parse("android.resource://$packageName/${R.raw.video1}")
        videoView.setVideoURI(settingUpUri)
        videoView.setMediaController(mediaController)
        videoView.seekTo(2000)

        val settingUpUri2 = Uri.parse("android.resource://$packageName/${R.raw.video2}")
        videoView2.setVideoURI(settingUpUri2)
        videoView2.setMediaController(mediaController2)
        videoView2.seekTo(4000)

        val next: ImageButton = findViewById(R.id.slideShow)
        next.setOnClickListener {
            goToGallery()
        }

    }

    fun goToGallery(){
        val intent = Intent(this, ProcessingGallery::class.java)
        startActivity(intent)
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition( R.anim.slide_out_down,R.anim.slide_in_down)
    }

}