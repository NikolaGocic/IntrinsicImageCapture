package com.studiog.intrinsicimagecapture.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.studiog.intrinsicimagecapture.R
import com.studiog.intrinsicimagecapture.SplashActivity
import com.studiog.intrinsicimagecapture.ui.theme.BackgroundDark
import com.studiog.intrinsicimagecapture.ui.theme.BackgroundLight

@OptIn(ExperimentalUnitApi::class)
@Composable
fun SplashScreen(splashActivity: SplashActivity, rnds: Int){

    Image(modifier= Modifier.fillMaxSize(),painter = painterResource(id = R.drawable.background2) , contentDescription ="", contentScale = ContentScale.FillBounds )

    Column(
        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(
            text = "Intrinsic   ",
            style = TextStyle(color = Color.White),
            fontSize = TextUnit(40F, TextUnitType.Sp),
            modifier = Modifier.padding(top=30.dp),
            textAlign = TextAlign.Center,
        )
        Text(
            text = "Image",
            style = TextStyle(color = Color.White),
            fontSize = TextUnit(40F, TextUnitType.Sp),
            modifier = Modifier,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "        Capture",
            style = TextStyle(color = Color.White),
            fontSize = TextUnit(40F, TextUnitType.Sp),
            modifier = Modifier.padding(bottom = 30.dp),
            textAlign = TextAlign.Center,
        )


        
        Row(
            modifier=Modifier.weight(1f).padding(bottom=15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val f: Int = splashActivity.resources.getIdentifier("f_$rnds" , "drawable", splashActivity.getPackageName());
            val d: Int = splashActivity.resources.getIdentifier("d_$rnds" , "drawable", splashActivity.getPackageName());

            Image(modifier= Modifier.padding(start = 20.dp,end=5.dp).weight(1f).border(2.dp,Color.White),painter = painterResource(id = f) , contentDescription ="" )
            Image(modifier= Modifier.padding(start = 5.dp,end=20.dp).weight(1f).border(2.dp,Color.White),painter = painterResource(id = d) , contentDescription ="" )
        }

        val i: Int = splashActivity.resources.getIdentifier("i_$rnds" , "drawable", splashActivity.getPackageName());

        Box(modifier= Modifier.weight(1.4f)){
            Image(
                modifier= Modifier
                    .padding(bottom = 40.dp)
                    .border(2.dp,Color.White)
                ,painter = painterResource(id = i) ,
                contentDescription =""
            )
        }

    }
}