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
import androidx.compose.ui.unit.dp

@Composable
fun ImageSlider(
    images: List<String>,
) {
    val TAG = "ImageSlider";

    val pagerState = rememberPagerState(pageCount = { images.size })

    val matrix = ColorMatrix().apply {
        setToSaturation(0.5f)
    }

    var lastPage by remember { mutableStateOf(-1) }

    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage != lastPage) {
            Log.d(TAG, "ImageSlider: ${pagerState.currentPage}")

            /*
                        soundPool.play(
                            soundId,
                            1f, // left volume
                            1f, // right volume
                            1,
                            0,
                            1f
                        )
            */
            lastPage = pagerState.currentPage
        }
    }

    HorizontalPager(
        state = pagerState, modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) { page ->
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            AssetImage(
                filePath = images[page],
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(8.dp),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.colorMatrix(matrix)
            )
            AssetImage(
                filePath = images[page],
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
