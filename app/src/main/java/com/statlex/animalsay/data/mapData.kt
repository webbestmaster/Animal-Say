package com.statlex.animalsay.data

import com.statlex.animalsay.R

data class MapData(val image: Int, val nameSrc: Int)

enum class NameEnum {
    NorthAmerica,
    SouthAmerica,
    Europe,
    Asia,
    Africa,
    Australia,
    Antarctica,
}



val mapDataList: List<MapData> = listOf(
    MapData(image = 1, nameSrc = R.string.europe),
    MapData(image = 2, nameSrc = R.string.asia),
    MapData(image = 3, nameSrc = R.string.africa),
    MapData(image = 4, nameSrc = R.string.australia),
    MapData(image = 5, nameSrc = R.string.north_america),
    MapData(image = 6, nameSrc = R.string.south_america),
    MapData(image = 7, nameSrc = R.string.antarctica),
)