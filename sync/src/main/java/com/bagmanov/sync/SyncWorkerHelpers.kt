package com.bagmanov.sync

import androidx.work.Constraints
import androidx.work.NetworkType

fun syncConstraints(wifiOnly: Boolean): Constraints {
    return Constraints.Builder()
        .setRequiredNetworkType(
            if (wifiOnly)
                NetworkType.UNMETERED
            else
                NetworkType.CONNECTED
        )
        .build()
}