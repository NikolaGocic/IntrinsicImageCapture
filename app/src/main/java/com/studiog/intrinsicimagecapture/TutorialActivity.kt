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

        val videoView: VideoView =findViewById(R.id.videoView)
        val videoView2: VideoView =findViewById(R.id.videoView2)

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

    }
}