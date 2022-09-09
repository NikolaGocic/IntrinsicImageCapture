package com.studiog.intrinsicimagecapture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.studiog.intrinsicimagecapture.ui.SplashScreen

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rnds = (1..7).shuffled().last()

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                SplashScreen(this,rnds)
            }
        }


        Handler().postDelayed(Runnable() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 2000)

    }
}