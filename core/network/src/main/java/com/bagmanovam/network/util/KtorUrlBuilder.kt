package com.bagmanovam.network.util

import com.bagmanovam.common.data.ApiConfig
import com.bagmanovam.common.data.Endpoint
import com.bagmanovam.common.data.UrlBuilder
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath

class KtorUrlBuilder(
    private val apiConfig: ApiConfig
): UrlBuilder {

    override fun build(endpoint: Endpoint): String {
        return URLBuilder().apply {
            protocol = URLProtocol.Companion.createOrDefault(apiConfig.schema)
            host = apiConfig.host
            apiConfig.port?.let { port = it }

            encodedPath = if (endpoint.path.startsWith("/")) endpoint.path else "/${endpoint.path}"
            endpoint.queryParams.forEach { (key, value) ->
                if (value != null) {
                    parameters.append(key, value)
                }
            }
        }.buildString()
    }
}