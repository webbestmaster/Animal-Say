package com.statlex.animalsay.view

sealed class Route(val route: String) {

    object Index : Route("Index")
    object Settings : Route("Settings")

    /*
        object Profile : Route("profile/{userId}") {
            fun createRoute(userId: Int): String {
                return "profile/$userId"
            }
        }
    */
}
