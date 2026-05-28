plugins {
    alias(libs.plugins.news.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.bagmanovam.core_navigation"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.serialization.json)
}