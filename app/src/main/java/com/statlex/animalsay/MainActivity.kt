package com.statlex.animalsay

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import com.statlex.animalsay.util.applyLanguage
//import com.statlex.animalsay.util.getSavedLanguageOrNull
import com.statlex.animalsay.util.initAppLanguage
import com.statlex.animalsay.util.supportedLanguages
//import com.statlex.animalsay.util.updateLocale
import com.statlex.animalsay.view.App
import com.statlex.animalsay.view.LocalNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun attachBaseContext(newBase: Context) {
        val localizedContext = initAppLanguage(newBase)
        super.attachBaseContext(localizedContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
/*
            val setLocRu = fun() {
                this@MainActivity.setNewLocale("ru")
            }
*/

            App()
        }
    }
}