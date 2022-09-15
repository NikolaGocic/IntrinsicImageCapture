package com.studiog.intrinsicimagecapture

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.studiog.intrinsicimagecapture.ui.SplashScreen
import java.util.*

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
            overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up )
        }, 3000)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        Handler().postDelayed(Runnable() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up )
        }, 3000)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed(Runnable() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up )
        }, 3000)
    }

}