package com.statlex.animalsay.view.component

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import com.statlex.animalsay.card.AnimalCard
import com.statlex.animalsay.card.animalCardDataList
import com.statlex.animalsay.util.SoundTrackEnum
import com.statlex.animalsay.util.playSoundByTrack
import com.statlex.animalsay.util.playSoundListByTrack
import com.statlex.animalsay.util.rememberAppLanguage

@Composable
fun ImageSlider(
    cardList: List<AnimalCard>,
) {
    val TAG = "ImageSlider";

    val context = LocalContext.current

    Log.d(TAG, "ImageSlider: ${animalCardDataList}")

    val language by rememberAppLanguage(context)
    val pagerState = rememberPagerState(pageCount = { cardList.size })
    val currentPage = pagerState.currentPage
    val isScrollInProgress = pagerState.isScrollInProgress

    LaunchedEffect(currentPage, isScrollInProgress) {
        if (isScrollInProgress) {
            return@LaunchedEffect
        }

        val card = cardList[currentPage]
        val nameId = card.nameId
        val pathPrefix = "animal-card/${nameId}/"

        playSoundListByTrack(
            context = context, assetPathList = mutableListOf(
                "${pathPrefix}name/${nameId}-${language}.mp3", "${pathPrefix}voice/${nameId}-1.mp3"
            ), trackEnum = SoundTrackEnum.main
        )

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
            val card = cardList[index]
            val imageList = card.imageList
            val nameId = card.nameId
            val pathPrefix = "animal-card/${nameId}/"
            val imagePathPrefix = "${pathPrefix}image/"
            var imageIndex by remember { mutableStateOf(0) }

            AnimatedContent(
                targetState = imageIndex, transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                }, label = "image animation"
            ) { index ->
                AssetImage(
                    filePath = imagePathPrefix + imageList[index],
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(8.dp),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                        setToSaturation(0.5f)
                    })
                )
            }

            AnimatedContent(
                targetState = imageIndex, transitionSpec = {
                    fadeIn() + scaleIn() togetherWith fadeOut() + scaleOut()
                }, label = "image animation"
            ) { index ->
                AssetImage(
                    filePath = imagePathPrefix + imageList[index],
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            enabled = true,
                            onClick = {
                                imageIndex = (imageIndex + 1) % imageList.size
                                playSoundByTrack(
                                    context = context,
                                    assetPath = "${pathPrefix}voice/${nameId}-1.mp3",
                                    SoundTrackEnum.main
                                )
                            },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ),
                    contentScale = ContentScale.Fit
                )
            }

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Blue)
                    .align(Alignment.BottomEnd)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        // пусто — просто блокируем клик
                    }
            ) {
                Text("some text")
            }
        }
    }
}
