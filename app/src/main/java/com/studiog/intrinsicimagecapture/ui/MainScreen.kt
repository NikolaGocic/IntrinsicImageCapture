package com.studiog.intrinsicimagecapture.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.studiog.intrinsicimagecapture.MainActivity
import com.studiog.intrinsicimagecapture.ui.theme.BackgroundDark
import com.studiog.intrinsicimagecapture.ui.theme.BackgroundLight

@OptIn(ExperimentalUnitApi::class)
@Composable
fun MainScreen(mainActivity: MainActivity, goToTutorial: (()->Unit)){
    var text by remember { mutableStateOf("") }

    Column(
        modifier= Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        BackgroundDark,
                        BackgroundLight
                    )
                )
            ),
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
            value = text,
            textStyle = TextStyle(color = Color.Gray),
            onValueChange =
            {
                text = it
                mainActivity.setName(text)
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
                //Icon(Icons.Sharp.Call, "", tint = Color.Gray)
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