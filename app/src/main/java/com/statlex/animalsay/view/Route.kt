package com.statlex.animalsay.view

sealed class Route(val route: String) {

    object Index : Route("Index")
    object Settings : Route("Settings")

    object ContinentAsia : Route("Continent Asia")
    object ContinentAfrica : Route("Continent Africa")
    object ContinentNorthAmerica : Route("Continent North America")
    object ContinentSouthAmerica : Route("Continent South America")
    object ContinentAntarctica : Route("Continent Antarctica")
    object ContinentEurope : Route("Continent Europe")
    object ContinentAustralia : Route("Continent Australia")


    /*
        object Profile : Route("profile/{userId}") {
            fun createRoute(userId: Int): String {
                return "profile/$userId"
            }
        }
    */
}
