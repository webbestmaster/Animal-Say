package com.statlex.animalsay

//import com.statlex.animalsay.util.applyLanguage
//import com.statlex.animalsay.util.getSavedLanguageOrNull
//import com.statlex.animalsay.util.initAppLanguage
//import com.statlex.animalsay.util.updateLocale
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.statlex.animalsay.util.initAppLanguage
import com.statlex.animalsay.view.App

class MainActivity : ComponentActivity() {

/*
    override fun attachBaseContext(newBase: Context) {
        val localizedContext = initAppLanguage(newBase)
        super.attachBaseContext(localizedContext)
    }
*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this

        initAppLanguage(context)

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