package com.studiog.intrinsicimagecapture.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.studiog.intrinsicimagecapture.ProcessingGallery
import com.studiog.intrinsicimagecapture.R

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ProcessingGallery(processingGallery: ProcessingGallery){

    Image(modifier= Modifier.fillMaxSize(),painter = painterResource(id = R.drawable.background2) , contentDescription ="", contentScale = ContentScale.FillBounds )
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        var picture = remember { mutableStateOf(1) }

        Text(
            text = "How to go Process Images",
            style = TextStyle(color = Color.White),
            fontSize = TextUnit(25F, TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 30.dp, bottom = 20.dp),
            textAlign = TextAlign.Center,
        )

        Image(
            modifier= Modifier.fillMaxWidth().padding(start=20.dp,end=20.dp).weight(1f).border(2.dp,Color.White),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = processingGallery.resources.getIdentifier("gallery_${picture.value}" , "drawable", processingGallery.getPackageName())) ,
            contentDescription =""
        )

        Row(
            modifier=Modifier.fillMaxWidth().padding(top=20.dp,bottom=20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val left: Int = processingGallery.resources.getIdentifier("arrow_left" , "drawable", processingGallery.getPackageName());
            val right: Int = processingGallery.resources.getIdentifier("arrow_right" , "drawable", processingGallery.getPackageName());

            IconButton(onClick = {if(picture.value>1) picture.value= picture.value - 1 }) {
                Image(modifier= Modifier.height(50.dp).width(50.dp),painter = painterResource(id = left) , contentDescription ="", contentScale = ContentScale.Fit )
            }
            IconButton(onClick = {if(picture.value<6) picture.value= picture.value + 1}) {
                Image(modifier= Modifier.height(50.dp).width(50.dp),painter = painterResource(id = right) , contentDescription ="", contentScale = ContentScale.Fit )
            }
        }


    }
}