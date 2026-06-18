package com.bagmanovam.network.util

import com.bagmanovam.common.data.Endpoint

class  NewsEverythingEndpoint(
    private val query: String,
    private val language: String = "en"
): Endpoint {
    override val path: String
        get() = "v2/everything"
    override val queryParams: Map<String, String?>
        get() = mapOf(
            "q" to query,
            "language" to language
        )
}