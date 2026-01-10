package com.statlex.animalsay.view.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.statlex.animalsay.view.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(navHostController: NavHostController) {
    val TAG = "Header"

    val backStackEntry by navHostController.currentBackStackEntryAsState()

    val currentRoute = backStackEntry?.destination?.route

    Log.d(TAG, "Header: $currentRoute")

    val isShowSettingsButton = currentRoute != Route.Settings.route;

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {
            navHostController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back"
            )
        }
        Text(
            "Header", fontSize = 24.sp
        )

        IconButton(
            enabled = isShowSettingsButton,
            onClick = { navHostController.navigate(Route.Settings.route) }
        ) {
            Icon(
                imageVector = Icons.Filled.Settings, contentDescription = "Settings"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Header(navHostController = rememberNavController())
}