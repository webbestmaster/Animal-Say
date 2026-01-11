package com.statlex.animalsay.view.page.continent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.statlex.animalsay.R
import com.statlex.animalsay.view.component.ImageSlider

@Composable
fun ContinentAsia() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Asia")

        ImageSlider(
            images = listOf(
                R.drawable.number_1,
                R.drawable.number_2,
                R.drawable.number_3,
            )
        )
    }

}