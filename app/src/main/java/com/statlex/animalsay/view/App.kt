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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.statlex.animalsay.R
import com.statlex.animalsay.ui.theme.AnimalSayTheme
import com.statlex.animalsay.util.LocalizedContent
import com.statlex.animalsay.view.component.Header
import com.statlex.animalsay.view.page.Continent
import com.statlex.animalsay.view.page.Index
import com.statlex.animalsay.view.page.Settings

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
                LocalizedContent {
                    CompositionLocalProvider(
                        LocalNavController provides navController
                    ) {
                        NavHost(
                            navController = navController, startDestination = Route.Index.route
                        ) {
                            composable(route = Route.Index.route) {
                                Column(modifier = Modifier.fillMaxSize()) {
                                    Header(text = R.string.app_name)
                                    Index()
                                }
                            }

                            composable(route = Route.Settings.route) {
                                Column(modifier = Modifier.fillMaxSize()) {
                                    Header(text = R.string.settings)
                                    Settings()
                                }
                            }

                            composable(
                                route = Route.Continent.route,
                                arguments = listOf(
                                    navArgument("index") { type = NavType.IntType }
                                )) { backStackEntry ->
                                val index: Int = backStackEntry.arguments?.getInt("index") ?: 0

                                Column(modifier = Modifier.fillMaxSize()) {
                                    Continent(index = index)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}