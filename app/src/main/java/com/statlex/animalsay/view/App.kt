package com.statlex.animalsay.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.statlex.animalsay.ui.theme.AnimalSayTheme
import com.statlex.animalsay.view.component.Header
import com.statlex.animalsay.view.page.Index
import com.statlex.animalsay.view.page.Settings
import com.statlex.animalsay.view.page.continent.ContinentAsia

import androidx.datastore.preferences.core.stringPreferencesKey
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import android.content.res.Configuration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

val Context.dataStore by preferencesDataStore(name = "settings")

val LANGUAGE_KEY = stringPreferencesKey("language")

fun getDeviceLanguage(context: Context): String {
    return context.resources.configuration.locales[0].language
}

val supportedLanguages = listOf("en", "sv", "ru")

fun chooseAppLanguage(
    deviceLanguage: String,
    supportedLanguages: List<String>,
): String {
    return if (supportedLanguages.contains(deviceLanguage)) deviceLanguage else supportedLanguages.first()
}

/*
suspend fun saveLanguage(
    context: Context,
    language: String
) {
    context.dataStore.edit { prefs ->
        prefs[LANGUAGE_KEY] = language
    }
}
*/

fun chooseSupportedLanguage(
    deviceLanguage: String,
    supportedLanguages: List<String>,
): String {
    return if (deviceLanguage in supportedLanguages) {
        deviceLanguage
    } else {
        supportedLanguages.first()
    }
}

object LanguageManager {

    fun applyLanguage(context: Context, language: String): Context {
        val locale = Locale.forLanguageTag(language)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        return context.createConfigurationContext(config)
    }
}

suspend fun saveLanguage(context: Context, language: String) {
    context.dataStore.edit {
        it[LANGUAGE_KEY] = language
    }
}

fun initAppLanguage(context: Context) {
    val supportedLanguages = listOf("en", "ru", "de")

    val savedLanguage = getSavedLanguageOrNull(context)

    val finalLanguage = if (savedLanguage != null) {
        savedLanguage
    } else {
        val deviceLanguage = getDeviceLanguage(context)
        chooseSupportedLanguage(deviceLanguage, supportedLanguages).also {
            CoroutineScope(Dispatchers.IO).launch {
                saveLanguage(context, it)
            }
        }
    }

    LanguageManager.applyLanguage(context, finalLanguage)
}


fun getSavedLanguageOrNull(context: Context): String? {
    return runBlocking {
        context.dataStore.data.first()[LANGUAGE_KEY]
    }
}

/*
CoroutineScope(Dispatchers.IO).launch {
    initAppLanguage(this@App)
}
*/

val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("NavHostController not provided")
}

@Composable
fun App() {


    AnimalSayTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFCC0000))
                    .padding(innerPadding)
            ) {
                val navController = rememberNavController()

                CompositionLocalProvider(
                    LocalNavController provides navController
                ) {
                    Header()

                    NavHost(
                        navController = navController, startDestination = Route.Index.route
                    ) {
                        composable(Route.Index.route) {
                            Index()
                        }

                        composable(Route.Settings.route) {
                            Settings()
                        }

                        composable(Route.ContinentAsia.route) {
                            ContinentAsia()
                        }
                    }
                }
            }
        }
    }
}