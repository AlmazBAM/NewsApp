plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.bagmanovam.newsapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.bagmanovam.newsapp"
        minSdk = 34
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.koin)

    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    implementation(libs.bundles.compose)

    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:navigation"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":feature:news"))
    implementation(project(":feature:setting"))
}