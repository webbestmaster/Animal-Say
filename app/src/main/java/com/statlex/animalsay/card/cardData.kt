package com.statlex.animalsay.card

import com.statlex.animalsay.R

val animalCardDataList: List<AnimalCard> = listOf(
    AnimalCard(
        nameId = "rabbit",
        name = R.string.app_name,
        imageList = listOf("rabbit-1.webp", "rabbit-2.jpg", "rabbit-3.jpg"),
        voiceList = listOf("rabbit-1.mp3", "rabbit-2.mp3")
    ),
    AnimalCard(
        nameId = "fox",
        name = R.string.app_name,
        imageList = listOf("fox-1.webp", "fox-2.jpg", "fox-3.avif"),
        voiceList = listOf("fox-1.mp3", "fox-2.mp3", "fox-3.mp3")
    ),
    AnimalCard(
        nameId = "wolf",
        name = R.string.app_name,
        imageList = listOf("wolf-1.jpg", "wolf-2.jpg", "wolf-3.webp"),
        voiceList = listOf("wolf-1.mp3", "wolf-2.mp3", "wolf-3.mp3")
    ),
)
