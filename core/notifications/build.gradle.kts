plugins {
    alias(libs.plugins.news.android.library)
    alias(libs.plugins.news.koin)
}


android {
    namespace = "com.bagmanovam.notifications"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(libs.androidx.core.ktx)
}