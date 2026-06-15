import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.news.android.application)
    alias(libs.plugins.news.android.application.compose)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.bagmanovam.newsapp"

    defaultConfig {
        applicationId = "com.bagmanovam.newsapp"
        versionCode = 1
        versionName = "1.0"

    }

    signingConfigs {
        create("release") {
            storeFile = rootProject.file(gradleLocalProperties(rootDir, providers).getProperty("SIGNING_KEYSTORE_FILE"))
            keyPassword = gradleLocalProperties(rootDir, providers).getProperty("SIGNING_KEY_PASSWORD")
            keyAlias = gradleLocalProperties(rootDir, providers).getProperty("SIGNING_KEY_ALIAS")
            storePassword = gradleLocalProperties(rootDir, providers).getProperty("SIGNING_KEYSTORE_PASSWORD")
        }
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
            signingConfig = signingConfigs.getByName("release")
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
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    implementation(libs.androidx.core.ktx)
    implementation(project(":core:analytics"))
    implementation(project(":core:analytics-firebase"))
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:database"))
    implementation(project(":core:domain"))
    implementation(project(":core:navigation"))
    implementation(project(":core:notifications"))
    implementation(project(":core:ui"))
    implementation(project(":sync"))
    implementation(project(":feature:news"))
    implementation(project(":feature:setting"))
}