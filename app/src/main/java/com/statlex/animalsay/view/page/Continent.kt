package com.statlex.animalsay.view.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.statlex.animalsay.card.animalCardDataList
import com.statlex.animalsay.data.MapData
import com.statlex.animalsay.data.mapDataList
import com.statlex.animalsay.view.component.Header
import com.statlex.animalsay.view.component.ImageSlider

@Composable
fun Continent(index: Int) {
    val mapData: MapData = mapDataList.getOrNull(index) ?: mapDataList[0]
    val (_, nameSrc) = mapData

    Column(modifier = Modifier.fillMaxSize()) {
        Header(text = nameSrc)

        Text("Name: ${stringResource(nameSrc)}")

        ImageSlider(
            cardList = listOf(
                animalCardDataList[0],
                animalCardDataList[1],
                animalCardDataList[2],
            )
        )
    }
}