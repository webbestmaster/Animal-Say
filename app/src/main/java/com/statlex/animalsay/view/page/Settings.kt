package com.statlex.animalsay.view.page

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Settings() {
    val TAG = "Settings"

    Column() {
        Text("Settings")

        Button(onClick = {}) {
            Text("ru")
        }

        Button(onClick = {}) {
            Text("en")
        }
    }
}