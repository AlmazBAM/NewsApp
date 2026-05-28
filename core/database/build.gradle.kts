plugins {
    alias(libs.plugins.news.android.library)
    alias(libs.plugins.news.room)
    alias(libs.plugins.news.koin)
}

android {
    namespace = "com.bagmanovam.news.core.database"
}
