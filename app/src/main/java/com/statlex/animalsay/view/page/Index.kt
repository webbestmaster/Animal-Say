package com.statlex.animalsay.view.page

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.statlex.animalsay.R
import com.statlex.animalsay.data.mapDataList
import com.statlex.animalsay.view.LocalNavController
import com.statlex.animalsay.view.Route

@Composable
fun Index() {
    val TAG = "Index"

    val navHostController = LocalNavController.current
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val buttonStyle = if (isLandscape)
        Modifier
            .fillMaxWidth(0.24f)
            .fillMaxHeight(0.49f)
            .padding(4.dp)
    else
        Modifier
            .fillMaxWidth(0.49f)
            .fillMaxHeight(0.24f)
            .padding(4.dp)

    val buttonTextStyle = Modifier
        .padding(4.dp, 0.dp)
        .fillMaxWidth()

    FlowRow(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF676767)),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Center,
    ) {
        mapDataList.mapIndexed { index, mapData ->
            Button(
                onClick = {
                    navHostController.navigate(
                        Route.Continent.route.replace("{index}", index.toString())
                    )
                },
                modifier = buttonStyle,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 4.dp)
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.europe),
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(mapData.nameSrc),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        lineHeight = 20.sp,
                        modifier = buttonTextStyle
                    )
                }
            }
        }


        /*
                Button(onClick = {
                    navHostController.navigate(Route.ContinentAsia.route)
                }, modifier = buttonStyle) {
                    Text(text = stringResource(R.string.asia), modifier = buttonTextStyle)
                }
                Button(onClick = {
                    navHostController.navigate(Route.ContinentAsia.route)
                }, modifier = buttonStyle) {
                    Text(text = stringResource(R.string.africa), modifier = buttonTextStyle)
                }
                Button(onClick = {
                    navHostController.navigate(Route.ContinentAsia.route)
                }, modifier = buttonStyle) {
                    Text(text = stringResource(R.string.australia), modifier = buttonTextStyle)
                }
                Button(onClick = {
                    navHostController.navigate(Route.ContinentAsia.route)
                }, modifier = buttonStyle) {
                    Text(text = stringResource(R.string.north_america), modifier = buttonTextStyle)
                }
                Button(onClick = {
                    navHostController.navigate(Route.ContinentAsia.route)
                }, modifier = buttonStyle) {
                    Text(text = stringResource(R.string.south_america), modifier = buttonTextStyle)
                }
                Button(onClick = {
                    navHostController.navigate(Route.ContinentAsia.route)
                }, modifier = buttonStyle) {
                    Text(text = stringResource(R.string.antarctica), modifier = buttonTextStyle)
                }
        */
//        Text("some text")
    }
}
