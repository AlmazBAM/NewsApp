plugins {
    alias(libs.plugins.news.android.feature.api)
    alias(libs.plugins.news.android.feature.impl)
}

android {
    namespace = "com.bagmanovam.setting"
}

dependencies {
    implementation(project(":core:domain"))
}