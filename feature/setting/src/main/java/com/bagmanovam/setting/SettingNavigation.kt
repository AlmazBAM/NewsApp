package com.bagmanovam.setting

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bagmanovam.navigation.AppRoute

fun NavGraphBuilder.settingGraph(navHostController: NavHostController) {
    composable<AppRoute.Settings> {
        SettingScreen()
    }
}