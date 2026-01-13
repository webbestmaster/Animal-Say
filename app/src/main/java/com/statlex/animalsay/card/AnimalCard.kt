package com.statlex.animalsay.card

data class AnimalCard(
    // eg: rabbit, fox, wolf
    val nameId: String,
    // localizable name id
    val name: Int,
    val imageList: List<String>,
    val voiceList: List<String>,
)
