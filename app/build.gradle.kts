import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

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
            buildConfigField(
                "String",
                "NEWS_API_KEY",
                gradleLocalProperties(rootDir, providers).getProperty("NEWS_API_KEY")
            )
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
            buildConfigField(
                "String",
                "NEWS_API_KEY",
                gradleLocalProperties(rootDir, providers).getProperty("NEWS_API_KEY")
            )
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:navigation"))
    implementation(project(":core:notifications"))
    implementation(project(":core:ui"))
    implementation(project(":sync"))
    implementation(project(":feature:news"))
    implementation(project(":feature:setting"))
}