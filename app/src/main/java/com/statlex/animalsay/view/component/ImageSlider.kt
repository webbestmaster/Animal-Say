package com.statlex.animalsay.view.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.statlex.animalsay.card.AnimalCard
import com.statlex.animalsay.card.animalCardDataList
import com.statlex.animalsay.util.SoundType
import com.statlex.animalsay.util.getIsMediaPlayerPlaying
import com.statlex.animalsay.util.playShortSound
import com.statlex.animalsay.util.playSoundByPath
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@Composable
fun ImageSlider(
    cardList: List<AnimalCard>,
) {
    val TAG = "ImageSlider";

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    Log.d(TAG, "ImageSlider: ${animalCardDataList}")

    val pagerState = rememberPagerState(pageCount = { cardList.size })
    val currentPage = pagerState.currentPage;
    val isScrollInProgress = pagerState.isScrollInProgress
    val matrix = ColorMatrix().apply {
        setToSaturation(0.5f)
    }

    LaunchedEffect(currentPage, isScrollInProgress) {
        if (isScrollInProgress) {
            return@LaunchedEffect
        }

        val card = cardList[currentPage]
        val pathPrefix = "animal-card/${card.nameId}/"

        /*
                mp.playFromAssets(
                    "${pathPrefix}name/${card.nameId}-en.mp3",
                    "${pathPrefix}voice/${card.nameId}-1.mp3"
                )
        */


        scope.launch {
            val mp = playSoundByPath(
                context = context,
                assetPath = "${pathPrefix}name/${card.nameId}-en.mp3",
                SoundType.COW
            )

            while (isActive && getIsMediaPlayerPlaying(mp)) {
                delay(100)
            }

            playSoundByPath(
                context = context,
                assetPath = "${pathPrefix}voice/${card.nameId}-1.mp3",
                SoundType.COW
            )
//            mp.playFromAssets("${pathPrefix}name/${card.nameId}-en.mp3")
//            mp.playFromAssets("${pathPrefix}voice/${card.nameId}-1.mp3")

        }

        Log.d(TAG, "ImageSlider, currentPage: ${currentPage}")
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
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(enabled = true, onClick = {
                        playSoundByPath(
                            context = context,
                            assetPath = "${pathPrefix}voice/${card.nameId}-1.mp3",
                            SoundType.COW
                        )

/*
                        playShortSound(
                            context = context, assetPath = "${pathPrefix}voice/${card.nameId}-1.mp3"
                        )
*/
                    }),
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
