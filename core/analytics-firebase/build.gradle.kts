plugins {
    alias(libs.plugins.news.android.library)
}

android {
    namespace = "com.bagmanovam.analytics_firebase"
}

dependencies {
    implementation(project(":core:analytics"))
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}