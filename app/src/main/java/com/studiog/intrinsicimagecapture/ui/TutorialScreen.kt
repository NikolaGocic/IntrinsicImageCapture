package com.studiog.intrinsicimagecapture.ui

import android.widget.ImageButton
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.studiog.intrinsicimagecapture.R
import com.studiog.intrinsicimagecapture.TutorialActivity

@OptIn(ExperimentalUnitApi::class)
@Composable
fun TutorialScreen(tutorialActivity:TutorialActivity){

    val player1 = remember{ mutableStateOf(SimpleExoPlayer.Builder(tutorialActivity).build()) }
    val player2 = remember{ mutableStateOf(SimpleExoPlayer.Builder(tutorialActivity).build()) }

    BackHandler(enabled = true){
        player1.value.stop()
        player2.value.stop()
        tutorialActivity.goToMain()
    }

    val s = rememberScrollState()
    Image(modifier= Modifier.fillMaxSize(),painter = painterResource(id = R.drawable.background2) , contentDescription ="", contentScale = ContentScale.FillBounds )

    Column(
        modifier=Modifier.verticalScroll(state = s),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Intrinsic Imaging",
            style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
            fontSize = TextUnit(30F, TextUnitType.Sp),
            modifier = Modifier.padding(top = 10.dp),
            textAlign = TextAlign.Center,
        )
        Text(
            text = stringResource(id = R.string.paragraf1),
            style = TextStyle(color = Color.White),
            fontSize = TextUnit(20F, TextUnitType.Sp),
            modifier = Modifier.padding(top = 10.dp, start=10.dp, end = 10.dp),
            textAlign = TextAlign.Justify,
        )
        Text(
            text = stringResource(id = R.string.paragraf2),
            style = TextStyle(color = Color.White),
            fontSize = TextUnit(20F, TextUnitType.Sp),
            modifier = Modifier.padding(top = 10.dp, start=10.dp, end = 10.dp),
            textAlign = TextAlign.Justify,
        )

        Text(
            text = "How to Attach a Diffuser",
            style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
            fontSize = TextUnit(25F, TextUnitType.Sp),
            modifier = Modifier.padding(top = 10.dp),
            textAlign = TextAlign.Center,
        )
        VideoPlayer(tutorialActivity,player1,1)

        Text(
            text = "How to Use a Diffuser",
            style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
            fontSize = TextUnit(25F, TextUnitType.Sp),
            modifier = Modifier.padding(top = 10.dp),
            textAlign = TextAlign.Center,
        )
        VideoPlayer(tutorialActivity,player2,2)

        Text(
            text = "How to Process an Image",
            style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
            fontSize = TextUnit(25F, TextUnitType.Sp),
            modifier = Modifier.padding(top = 10.dp),
            textAlign = TextAlign.Center,
        )
        IconButton(onClick = {
            tutorialActivity.goToGallery()
            player1.value.stop()
            player2.value.stop()
        }) {
            Image(
                modifier= Modifier.fillMaxWidth().height(230.dp).padding(start = 10.dp, end = 10.dp,bottom=10.dp),
                painter = painterResource(id = R.drawable.gallery_0) ,
                contentDescription ="",
                contentScale = ContentScale.FillBounds
            )
        }


    }

}

@Composable
fun VideoPlayer(tutorialActivity: TutorialActivity, player: MutableState<SimpleExoPlayer>, video: Int){
    var videoURI: String =""
    if(video==1) videoURI = "android.resource://${tutorialActivity.packageName}/${R.raw.video1}"
    else videoURI = "android.resource://${tutorialActivity.packageName}/${R.raw.video2}"

    val mediaItem = MediaItem.fromUri(videoURI)

    val view = PlayerView(tutorialActivity)

    player.value.setMediaItem(mediaItem)
    view.player = player.value
    player.value.prepare()
    player.value.seekTo(2000)

    AndroidView(modifier= Modifier
        .fillMaxWidth()
        .height(220.dp)
        .padding(start = 10.dp, end = 10.dp),factory = {
        view
    })
}