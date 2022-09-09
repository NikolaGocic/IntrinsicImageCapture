package com.studiog.intrinsicimagecapture.ui

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.studiog.intrinsicimagecapture.MainActivity
import com.studiog.intrinsicimagecapture.R
import com.studiog.intrinsicimagecapture.ui.theme.BackgroundDark
import com.studiog.intrinsicimagecapture.ui.theme.BackgroundLight

@OptIn(ExperimentalUnitApi::class)
@Composable
fun MainScreen(mainActivity: MainActivity, goToTutorial: (()->Unit)){

    Image(modifier= Modifier.fillMaxSize(),painter = painterResource(id = R.drawable.background2) , contentDescription ="", contentScale = ContentScale.FillBounds )

    Column(
        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Intrinsic Image Capture",
            style = TextStyle(color = Color.White),
            fontSize = TextUnit(40F, TextUnitType.Sp),
            modifier = Modifier.padding(bottom = 40.dp),
            textAlign = TextAlign.Center,
        )

        OutlinedTextField(
            value = mainActivity.imageName,
            textStyle = TextStyle(color = Color.Gray),
            onValueChange =
            {
                mainActivity.imageName= it
            },
            placeholder = {
                Text(
                    text = "Enter name", style = TextStyle(color = Color.LightGray),
                    //fontSize = TextUnit(40F, TextUnitType.Sp),
                    //modifier = Modifier.padding(bottom = 40.dp),
                    //textAlign = TextAlign.Center,
                )
            },
            shape = RoundedCornerShape(8.dp),
            trailingIcon = {
                IconButton(onClick = { mainActivity.recordAudio()}) {
                    Icon(painterResource(id = R.drawable.icon_microphone), "", tint = Color.Gray)
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor =  Color.Transparent, //hide the indicator
                //unfocusedIndicatorColor = .....
            )
        )

        Button(onClick = { mainActivity.camera() }) {
            Text("CAPTURE IMAGE")
        }

        Button(onClick = { goToTutorial() }) {
            Text("HOW TO...")
        }

        Button(onClick = { mainActivity.goToImageProcessingActivity() }) {
            Text("IMAGE PROCESSING")
        }

    }
}