package com.statlex.animalsay.util

import androidx.datastore.preferences.core.stringPreferencesKey
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import android.content.res.Configuration
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Locale

val TAG = "LanguageManager"

val supportedLanguages = listOf("ru", "en", "sv")

private val Context.dataStore by preferencesDataStore(name = "settings_language_name")

private val LANGUAGE_KEY = stringPreferencesKey("language_name_2")/*

fun Context.updateLocale(language: String): Context {
    val locale = Locale.forLanguageTag(language)
    Locale.setDefault(locale)

    val config = Configuration(resources.configuration)
    config.setLocale(locale)

    return createConfigurationContext(config)
}
*/

/*
fun setNewLocale(language: String) {
    // обновляем DataStore
    CoroutineScope(Dispatchers.IO).launch {
        changeLanguage(this@MainActivity, language)
    }

    // пересоздаём Activity для применения локали
    val ctx = updateLocale(language)
    applyOverrideConfiguration(ctx.resources.configuration)

    recreate() // пересоздаёт UI
}
*/

/*
private suspend fun saveLanguageInner(context: Context, language: String) {
    context.dataStore.edit {
        it[LANGUAGE_KEY] = language
    }
}
*/

private fun getDeviceLanguage(context: Context): String {
    return context.resources.configuration.locales[0].language
}

private fun chooseSupportedLanguage(
    deviceLanguage: String,
): String {
    return if (deviceLanguage in supportedLanguages) {
        deviceLanguage
    } else {
        supportedLanguages.first()
    }
}


@Composable
fun rememberAppLanguage(context: Context): State<String> {
    val languageFlow = context.getLanguageFlow()
    return languageFlow.collectAsState(initial = supportedLanguages.first())
}

private fun Context.getLanguageFlow(): Flow<String> =
    dataStore.data.map { it[LANGUAGE_KEY] ?: supportedLanguages.first() }


suspend fun Context.saveLanguage(language: String) {
    dataStore.edit { it[LANGUAGE_KEY] = language }
}

fun applyLanguage(context: Context, language: String): Context {
    Log.d(TAG, "applyLanguage: $language")
    val locale = Locale.forLanguageTag(language)
    Locale.setDefault(locale)

    val config = Configuration(context.resources.configuration)
    config.setLocale(locale)

    return context.createConfigurationContext(config)
}

private fun getSavedLanguageOrNull(context: Context): String? {
    return runBlocking {
        context.dataStore.data.first()[LANGUAGE_KEY]
    }
}

fun initAppLanguage(context: Context): Context {
    val savedLanguage = getSavedLanguageOrNull(context)

    Log.d(TAG, "initAppLanguage: $savedLanguage")

    val finalLanguage: String = if (savedLanguage != null && savedLanguage in supportedLanguages) {
        savedLanguage
    } else {
        val deviceLanguage = getDeviceLanguage(context)

        chooseSupportedLanguage(deviceLanguage)
    }

//    applyLanguage(context, finalLanguage)
    return applyLanguage(context, finalLanguage)
}

