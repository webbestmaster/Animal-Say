package com.statlex.animalsay.view.page.continent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.statlex.animalsay.view.component.ImageSlider

@Composable
fun ContinentAsia() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Asia")

        ImageSlider(
            images = listOf(
                "images/number-3.png",
                "images/number-3.png",
                "images/number-3.png",
            )
        )
    }
}