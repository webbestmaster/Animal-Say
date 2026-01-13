package com.statlex.animalsay.view.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.statlex.animalsay.R
import com.statlex.animalsay.card.AnimalCard
import com.statlex.animalsay.card.animalCardDataList
import com.statlex.animalsay.helper.rememberSoundPool
import com.statlex.animalsay.util.playShortSound

@Composable
fun ImageSlider(
    cardList: List<AnimalCard>,
) {
    val TAG = "ImageSlider";

    val context = LocalContext.current

    Log.d(TAG, "ImageSlider: ${animalCardDataList}")

//    val (soundPool, soundId) = rememberSoundPool(R.raw.dark_engine_logo_141942)

    val pagerState = rememberPagerState(pageCount = { cardList.size })
    val currentPage = pagerState.currentPage;

    val matrix = ColorMatrix().apply {
        setToSaturation(0.5f)
    }

//    var lastPage by remember { mutableStateOf(currentPage) }

    LaunchedEffect(currentPage) {
//        if (currentPage != lastPage && currentPage >= 0) {
        val card = cardList[currentPage];

        val pathPrefix = "animal-card/${card.nameId}/"

        playShortSound(context = context, assetPath = "${pathPrefix}name/${card.nameId}-en.mp3")

        Log.d(TAG, "ImageSlider, currentPage: ${currentPage}")

        /*
                    soundPool.setOnLoadCompleteListener { _, _, status ->
                        if (status == 0) {
                            soundPool.play(
                                soundId, 1f, // left volume
                                1f, // right volume
                                1, 0, 1f
                            )
                        }
                    }
        */

//            lastPage = currentPage
//        }
    }

    HorizontalPager(
        state = pagerState, modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) { index ->
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            val card = cardList[index];
            val pathPrefix = "animal-card/${card.nameId}/"
            val imagePathPrefix = "${pathPrefix}image/"

            AssetImage(
                filePath = imagePathPrefix + card.imageList[0],
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(8.dp),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.colorMatrix(matrix)
            )
            AssetImage(
                filePath = imagePathPrefix + card.imageList[0],
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Blue)
                    .align(Alignment.BottomEnd)
            ) {
                Text("some text")
            }
        }
    }
}
