package com.studiog.intrinsicimagecapture

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView

class TutorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        var videoView: VideoView = findViewById(R.id.videoView)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        val settingUpUri = Uri.parse("android.resource://$packageName/${R.raw.settingupdiffuser}")


        videoView.setVideoURI(settingUpUri)
        videoView.setMediaController(mediaController)
        videoView.requestFocus()
        videoView.start()



    }
}