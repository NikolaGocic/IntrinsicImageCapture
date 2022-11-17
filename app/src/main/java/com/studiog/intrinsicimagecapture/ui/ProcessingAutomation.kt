package com.studiog.intrinsicimagecapture.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
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
import com.studiog.intrinsicimagecapture.ui.theme.Grey
import com.studiog.intrinsicimagecapture.ui.theme.LightBlue
import com.studiog.intrinsicimagecapture.ui.theme.Yellow

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ProcessingAutomation(mainActivity: MainActivity) {
    Image(modifier= Modifier.fillMaxSize(),painter = painterResource(id = R.drawable.background2) , contentDescription ="", contentScale = ContentScale.FillBounds )

    if(mainActivity.showPreview)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier= Modifier.weight(3f).padding(top=10.dp,bottom=5.dp,start=40.dp,end=40.dp),
                bitmap = mainActivity.fBitmap.asImageBitmap(),
                contentDescription ="",
                contentScale = ContentScale.FillBounds
            )
            Image(
                modifier= Modifier.weight(3f).padding(top=5.dp,bottom=10.dp,start=40.dp,end=40.dp),
                bitmap = mainActivity.dBitmap.asImageBitmap(),
                contentDescription ="",
                contentScale = ContentScale.FillBounds
            )
            Row(
                modifier= Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Button(
                    modifier = Modifier.weight(1f).height(100.dp).padding(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = LightBlue ),
                    onClick = { mainActivity.processImages()}) {
                    Text(
                        text = "Process",
                        style = TextStyle(color = Color.White),
                        fontSize = TextUnit(20F, TextUnitType.Sp),
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                    )
                }
                Button(
                    modifier = Modifier.weight(1f).height(100.dp).padding(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Grey ),
                    onClick = { mainActivity.showImage=false }) {
                    Text(
                        text = "Cancel",
                        style = TextStyle(color = Color.White),
                        fontSize = TextUnit(20F, TextUnitType.Sp),
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                    )
                }
            }

        }

    if(mainActivity.loading) Loading()

    if(mainActivity.loading && (!mainActivity.fDone || !mainActivity.dDone)) Loading()
    else Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(mainActivity.responded)
            Image(
                modifier= Modifier
                    .weight(6f)
                    .padding(50.dp),
                bitmap = mainActivity.responseBitmap.asImageBitmap(),
                contentDescription ="",
                contentScale = ContentScale.FillBounds
            )
    }

}