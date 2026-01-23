plugins {
    alias(libs.plugins.news.android.application)
    alias(libs.plugins.news.android.application.compose)
}

android {
    namespace = "com.bagmanovam.newsapp"

    defaultConfig {
        applicationId = "com.bagmanovam.newsapp"
        versionCode = 1
        versionName = "1.0"

    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":feature:news"))
    implementation(project(":feature:setting"))
}