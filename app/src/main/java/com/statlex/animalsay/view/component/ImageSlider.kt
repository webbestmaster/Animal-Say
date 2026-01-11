package com.statlex.animalsay.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.ColorFilter

@Composable
fun ImageSlider(
    images: List<Int>,
) {
    val pagerState = rememberPagerState(pageCount = { images.size })

    val matrix = ColorMatrix().apply {
        setToSaturation(0.5f)
    }

    HorizontalPager(
        state = pagerState, modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) { page ->
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(8.dp),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.colorMatrix(matrix)
            )
            Image(
                painter = painterResource(id = images[page]),
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
