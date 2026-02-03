plugins {
    alias(libs.plugins.news.android.library)
}


android {
    namespace = "com.bagmanovam.notifications"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.koin)
}