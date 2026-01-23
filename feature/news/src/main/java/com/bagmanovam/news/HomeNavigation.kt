package com.bagmanovam.news

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bagmanovam.navigation.AppRoute

fun NavGraphBuilder.homeGraph(navHostController: NavHostController) {
    composable<AppRoute.Home> {
        HomeScreen()
    }
}