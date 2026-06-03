plugins {
    alias(libs.plugins.news.android.data)
    alias(libs.plugins.news.koin)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.bagmanovam.data"
}

dependencies {
    implementation(libs.bundles.ktor)
}