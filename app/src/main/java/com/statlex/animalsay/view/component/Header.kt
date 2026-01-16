package com.statlex.animalsay.view.component

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.statlex.animalsay.R
import com.statlex.animalsay.view.LocalNavController
import com.statlex.animalsay.view.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(text: Int) {
    val TAG = "Header"

    val navHostController = LocalNavController.current
    val backStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val isShowSettingsButton = currentRoute != Route.Settings.route;
    val isShowBackButton = currentRoute != Route.Index.route;

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            enabled = isShowBackButton, onClick = { navHostController.popBackStack() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back"
            )
        }

        Text(
            stringResource(text), fontSize = 24.sp
        )

        IconButton(
            enabled = isShowSettingsButton,
            onClick = { navHostController.navigate(Route.Settings.route) }) {
            Icon(
                imageVector = Icons.Filled.Settings, contentDescription = "Settings"
            )
        }
    }
}
