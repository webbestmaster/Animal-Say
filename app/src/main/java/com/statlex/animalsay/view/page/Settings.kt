package com.statlex.animalsay.view.page

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.statlex.animalsay.util.LocalizedContent
import com.statlex.animalsay.util.rememberAppLanguage
import com.statlex.animalsay.util.saveLanguage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Settings() {
    val TAG = "Settings"

    val context = LocalContext.current
    val language by rememberAppLanguage(context)

    Column {
        Text("Settings")

        Text("Current language is: $language")

        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                context.saveLanguage("ru")
            }
        }) {
            Text("ru")
        }

        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                context.saveLanguage("en")
            }
        }) {
            Text("en")
        }
    }
}