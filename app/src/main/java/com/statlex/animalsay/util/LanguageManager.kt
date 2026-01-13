package com.statlex.animalsay.util

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.util.Locale

val TAG = "LanguageManager"

private val supportedLanguages = listOf("en", "ru")

private val Context.dataStore by preferencesDataStore(name = "settings_language")

private val LANGUAGE_KEY = stringPreferencesKey("language_name")

@Composable
fun rememberAppLanguage(context: Context): State<String> {
    val languageFlow = context.getLanguageFlow()
    return languageFlow.collectAsState(initial = supportedLanguages.first())
}

@Composable
fun LocalizedContent(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val language by rememberAppLanguage(context)
    val localizedContext = remember(language) { applyLanguage(context, language) }

    CompositionLocalProvider(LocalContext provides localizedContext) {
        content()
    }
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

fun initAppLanguage(context: Context): Context {
    val savedLanguage = getSavedLanguageOrNull(context)

    Log.d(TAG, "initAppLanguage, savedLanguage: $savedLanguage")

    val finalLanguage: String = if (savedLanguage != null && savedLanguage in supportedLanguages) {
        savedLanguage
    } else {
        val deviceLanguage = getDeviceLanguage(context)

        chooseSupportedLanguage(deviceLanguage)
    }

    return applyLanguage(context, finalLanguage)
}

