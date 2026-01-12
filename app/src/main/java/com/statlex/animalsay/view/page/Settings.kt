package com.statlex.animalsay.view.page

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.intl.Locale
import com.statlex.animalsay.SetRuNewLocale
import com.statlex.animalsay.util.applyLanguage
import com.statlex.animalsay.util.rememberAppLanguage
import com.statlex.animalsay.util.saveLanguage
import com.statlex.animalsay.util.updateLocale
import com.statlex.animalsay.view.component.Header
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LocalizedContent(language: String, content: @Composable () -> Unit) {
    val context = LocalContext.current
    val localizedContext = remember(language) { applyLanguage(context, language) }
    CompositionLocalProvider(LocalContext provides localizedContext) {
        content()
    }
}

@Composable
fun Settings() {
    val TAG = "Settings"

    val context = LocalContext.current

    val language by rememberAppLanguage(context)

    LocalizedContent(language = language) {

        Column() {
            Text("Settings")

            Header()

            Button(onClick = {

                CoroutineScope(Dispatchers.IO).launch {
                    context.saveLanguage("ru")
                }

//            applyLanguage(context, "ru")
                /*
                            context.updateLocale("ru")
                            CoroutineScope(Dispatchers.IO).launch {
                                applyLanguage(context, "ru")
                            }
                */

            }) {
                Text("ru")
            }

            Button(onClick = {
//            applyLanguage(context, "en")
//            context.updateLocale("en")
            }) {
                Text("en")
            }
        }
    }
}