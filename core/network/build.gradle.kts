plugins {
    alias(libs.plugins.news.android.library)
    alias(libs.plugins.news.koin)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.bagmanovam.network"
}

dependencies {
    implementation(project(":core:common"))
    implementation(libs.bundles.ktor)
}