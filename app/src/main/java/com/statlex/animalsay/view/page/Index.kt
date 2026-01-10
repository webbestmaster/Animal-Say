package com.statlex.animalsay.view.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.statlex.animalsay.view.LocalNavController
import com.statlex.animalsay.view.Route

@Composable
fun Index() {
    val TAG = "Index"

    val navHostController = LocalNavController.current

    val buttonStyle = Modifier
        .padding(4.dp)
        .height(48.dp)
        .width(200.dp)

    val buttonTextStyle = Modifier.padding(0.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF676767)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = {}, modifier = buttonStyle) {
            Text("Africa", modifier = buttonTextStyle)
        }
        Button(onClick = {
            navHostController.navigate(Route.ContinentAsia.route)
        }, modifier = buttonStyle) {
            Text("Asia", modifier = buttonTextStyle)
        }
        Button(onClick = {}, modifier = buttonStyle) {
            Text("Europe", modifier = buttonTextStyle)
        }

//        Text("some text")
    }
}
