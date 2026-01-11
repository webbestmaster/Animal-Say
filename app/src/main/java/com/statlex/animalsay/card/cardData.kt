package com.statlex.animalsay.card

import androidx.compose.material3.Card
import com.statlex.animalsay.R

val animalCardDataList: List<AnimalCard> = listOf(
    Card(id = "1", name = R.string.app_name),
    Card(id = "2", name = R.string.app_name),
)

/*
sealed class cardData {
    companion object {
    }
}*/
