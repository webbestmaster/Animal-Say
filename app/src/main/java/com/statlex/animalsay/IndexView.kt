package com.statlex.animalsay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun IndexView() {
    Column(
        modifier = Modifier
            .fillMaxSize(0.5f)
            .background(Color(0xFF00CC00))

    ) {
        Text("some text")
    }
}