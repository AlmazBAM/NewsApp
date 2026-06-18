package com.bagmanovam.network.di

import com.bagmanovam.common.data.ApiConfig
import com.bagmanovam.network.NewsApi
import com.bagmanovam.network.util.KtorUrlBuilder
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        val apiConfig: ApiConfig = get()
        HttpClient(OkHttp.create()) {
            install(DefaultRequest) {
                header("X-Api-Key", apiConfig.apiKey)
            }
            install(Logging) {
                level = LogLevel.HEADERS
                sanitizeHeader { header ->
                    header.equals("X-Api-Key", ignoreCase = true) || header == HttpHeaders.Authorization
                }
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        coerceInputValues = true
                    }
                )
            }
        }
    }

    single {
        KtorUrlBuilder(get())
    }

    single {
        NewsApi(get(), get())
    }
}