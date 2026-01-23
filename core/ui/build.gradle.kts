plugins {
    alias(libs.plugins.news.android.library)
    alias(libs.plugins.news.android.library.compose)
}

android {
    namespace = "com.bagmanovam.core_ui"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    api(libs.bundles.coil)
}