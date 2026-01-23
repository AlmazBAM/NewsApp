package com.bagmanovam.newsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bagmanovam.navigation.AppRoute
import com.bagmanovam.news.homeGraph
import com.bagmanovam.setting.settingGraph


@Composable
fun AppNavHost(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = AppRoute.Home
    ) {
        settingGraph(navHostController)
        homeGraph(navHostController)
    }
}