package com.bagmanovam.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute {
    @Serializable
    data object Home: AppRoute

    @Serializable
    data object Settings: AppRoute
}