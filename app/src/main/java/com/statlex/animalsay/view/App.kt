package com.statlex.animalsay.view

import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.statlex.animalsay.ui.theme.AnimalSayTheme
//import com.statlex.animalsay.util.getSavedLanguageOrNull
import com.statlex.animalsay.util.supportedLanguages
import com.statlex.animalsay.view.component.Header
import com.statlex.animalsay.view.page.Index
import com.statlex.animalsay.view.page.Settings
import com.statlex.animalsay.view.page.continent.ContinentAsia
import androidx.activity.compose.setContent
//import com.statlex.animalsay.util.updateLocale


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