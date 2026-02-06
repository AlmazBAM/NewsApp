package com.bagmanovam.setting

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bagmanovam.navigation.AppRoute
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.settingGraph(navHostController: NavHostController) {


    composable<AppRoute.Settings> {
        val settingViewModel = koinViewModel<SettingViewModel>()
        val state by settingViewModel.state.collectAsStateWithLifecycle()
        SettingScreen(
            state = state,
            onSettingAction = settingViewModel::onAction
        )
    }
}