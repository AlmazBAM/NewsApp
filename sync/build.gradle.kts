plugins {
    alias(libs.plugins.news.android.library)
}


android {
    namespace = "com.bagmanovam.sync"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:notifications"))
    implementation(libs.androidx.work.runtime.ktx)
    api(libs.koin.androidx.workmanager)

}