package com.statlex.animalsay.data

import com.statlex.animalsay.R

data class MapData(val imageSrc: Int, val nameSrc: Int)

val mapDataList: List<MapData> = listOf(
    MapData(imageSrc = R.drawable.europe, nameSrc = R.string.europe),
    MapData(imageSrc = R.drawable.asia, nameSrc = R.string.asia),
    MapData(imageSrc = R.drawable.africa, nameSrc = R.string.africa),
    MapData(imageSrc = R.drawable.australia, nameSrc = R.string.australia),
    MapData(imageSrc = R.drawable.north_america, nameSrc = R.string.north_america),
    MapData(imageSrc = R.drawable.south_america, nameSrc = R.string.south_america),
    MapData(imageSrc = R.drawable.antarctica, nameSrc = R.string.antarctica),
)