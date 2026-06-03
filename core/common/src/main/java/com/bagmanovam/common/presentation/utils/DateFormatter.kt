package com.bagmanovam.common.presentation.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateFormatter {
    private const val DEFAULT_DATE_PATTERN = "E, d MMMM"


    fun formatDate(
        timeStamp: Long,
        pattern: String = DEFAULT_DATE_PATTERN,
        locale: Locale = Locale.getDefault(),
        zoneId: ZoneId = ZoneId.systemDefault(),
    ): String {

        val formatter = DateTimeFormatter.ofPattern(pattern, locale)
        val localDateTime = Instant.ofEpochMilli(timeStamp).atZone(zoneId)
        return localDateTime.format(formatter)
    }
}