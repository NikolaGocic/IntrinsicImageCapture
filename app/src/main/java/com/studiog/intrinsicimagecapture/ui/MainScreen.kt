package com.studiog.intrinsicimagecapture.ui

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.studiog.intrinsicimagecapture.MainActivity
import com.studiog.intrinsicimagecapture.R
import com.studiog.intrinsicimagecapture.ui.theme.BackgroundDark
import com.studiog.intrinsicimagecapture.ui.theme.BackgroundLight
import com.studiog.intrinsicimagecapture.ui.theme.Grey
import com.studiog.intrinsicimagecapture.ui.theme.LightBlue

@OptIn(ExperimentalUnitApi::class)
@Composable
fun MainScreen(mainActivity: MainActivity, goToTutorial: (()->Unit)){

    Image(modifier= Modifier.fillMaxSize(),painter = painterResource(id = R.drawable.background2) , contentDescription ="", contentScale = ContentScale.FillBounds )

    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(top = 20.dp, bottom = 40.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(
            modifier= Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Intrinsic   ",
                style = TextStyle(color = Color.White),
                fontSize = TextUnit(40F, TextUnitType.Sp),
                modifier = Modifier.padding(top = 30.dp),
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
                modifier = Modifier.padding(bottom = 10.dp),
                textAlign = TextAlign.Center,
            )
            Box(modifier= Modifier
                .fillMaxWidth()
                .padding(start = 25.dp)){
                Button(
                    modifier = Modifier
                        .height(45.dp)
                        .width(120.dp),
                    onClick = { goToTutorial() },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Grey )
                ) {
                    Text(
                        text = "How to...",
                        style = TextStyle(color = Color.White),
                        fontSize = TextUnit(20F, TextUnitType.Sp),
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }




        Column(
            modifier= Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            OutlinedTextField(
                modifier= Modifier.width(300.dp),
                value = mainActivity.imageName,
                textStyle = TextStyle(color = Color.Gray),
                onValueChange =
                {
                    mainActivity.imageName= it
                },
                placeholder = {
                    Text(
                        text = "Enter name", style = TextStyle(color = Color.LightGray),
                        fontSize = TextUnit(20F, TextUnitType.Sp),
                        //modifier = Modifier.padding(bottom = 40.dp),
                        //textAlign = TextAlign.Center,
                    )
                },
                shape = RoundedCornerShape(8.dp),
                trailingIcon = {
                    IconButton(onClick = { mainActivity.recordAudio()}) {
                        Icon(painterResource(id = R.drawable.icon_microphone), "",modifier= Modifier
                            .height(35.dp)
                            .width(35.dp), tint = Color.Gray)
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor =  Color.Transparent, //hide the indicator
                    //unfocusedIndicatorColor = .....
                )
            )
            Button(modifier= Modifier
                .height(65.dp)
                .width(300.dp)
                .padding(top = 10.dp),
                onClick = { mainActivity.camera() },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = LightBlue)) {
                Text(
                    style = TextStyle(color = Color.White),
                    fontSize = TextUnit(20F, TextUnitType.Sp),
                    text= "Capture Images"
                )
            }

        }

        Text(
            text = "Can darkness reveal information? Yes! A simple example is suggested in the question, “Are the stars still in the sky during daylight hours?”",
            style = TextStyle(color = Color.White),
            fontSize = TextUnit(20F, TextUnitType.Sp),
            fontStyle = Italic,
            modifier = Modifier.padding(start= 20.dp, end=20.dp),
            textAlign = TextAlign.Center,
        )


        Button(modifier= Modifier
            .height(55.dp)
            .width(240.dp),
            onClick = { mainActivity.goToImageProcessingActivity() },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = BackgroundDark)) {
            Text(
                style = TextStyle(color = Color.White),
                fontSize = TextUnit(20F, TextUnitType.Sp),
                text= "Image Processing"
            )
        }

    }

    if(mainActivity.showDialog)
        Dialog(onDismissRequest = { mainActivity.dissmissDialog() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Grey
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Text(
                        style = TextStyle(color = Color.White),
                        fontSize = TextUnit(20F, TextUnitType.Sp),
                        text= mainActivity.showText,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = { mainActivity.dissmissDialog() },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = LightBlue),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(
                                style = TextStyle(color = Color.White),
                                fontSize = TextUnit(20F, TextUnitType.Sp),
                                text= "OK",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }

}