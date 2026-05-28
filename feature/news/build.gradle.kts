plugins {
    alias(libs.plugins.news.android.feature.api)
    alias(libs.plugins.news.android.feature.impl)
    alias(libs.plugins.news.koin)
}

android {
    namespace = "com.bagmanovam.news"
}

dependencies {
    implementation(project(":core:domain"))
}