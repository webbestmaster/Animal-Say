package com.statlex.animalsay.util

import androidx.datastore.preferences.core.stringPreferencesKey
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import android.content.res.Configuration
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

val TAG = "LanguageManager"

private val supportedLanguages = listOf("en", "ru", "sv")

private val Context.dataStore by preferencesDataStore(name = "settings_language_name")

private val LANGUAGE_KEY = stringPreferencesKey("language_name_1")

private suspend fun saveLanguage(context: Context, language: String) {
    context.dataStore.edit {
        it[LANGUAGE_KEY] = language
    }
}

private fun getSavedLanguageOrNull(context: Context): String? {
    return runBlocking {
        context.dataStore.data.first()[LANGUAGE_KEY]
    }
}

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

fun applyLanguage(context: Context, language: String): Context {
    Log.d(TAG, "applyLanguage: $language")
    val locale = Locale.forLanguageTag(language)
    Locale.setDefault(locale)

    val config = Configuration(context.resources.configuration)
    config.setLocale(locale)

    return context.createConfigurationContext(config)
}

fun initAppLanguage(context: Context): Context {
    val savedLanguage = getSavedLanguageOrNull(context)

    Log.d(TAG, "initAppLanguage: $savedLanguage")

    val finalLanguage = if (savedLanguage != null) {
        savedLanguage
    } else {
        val deviceLanguage = getDeviceLanguage(context)

        chooseSupportedLanguage(deviceLanguage).also {
            runBlocking {
//            CoroutineScope(Dispatchers.IO).launch {
                saveLanguage(context, it)
            }
        }
    }

//    applyLanguage(context, finalLanguage)
    return applyLanguage(context, finalLanguage)
}

