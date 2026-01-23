package com.bagmanovam.common.domain

enum class NetworkError: Error {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    UNAUTHORIZED,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN,
}