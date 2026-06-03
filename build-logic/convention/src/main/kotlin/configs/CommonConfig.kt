package configs

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


internal fun Project.configureAndroidCommon(
    extensions: CommonExtension<*, *, *, *, *, *>,
) {
    extensions.apply {
        compileSdk = libs.findVersion("compileSdk").get().requiredVersion.toInt()

        defaultConfig {
            minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
            // targetSdk is APP-only; set it in AndroidAppConventionPlugin
        }

        compileOptions {
            isCoreLibraryDesugaringEnabled = true
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }

    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("desugar-jdk-libs").get())
    }
}

internal fun Project.configureKotlinAndroid() {
    val extension = extensions.getByType<KotlinAndroidProjectExtension>()
    extension.compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
    applyCommonOptions(extension.compilerOptions)


}

internal fun Project.configureKotlinJvm() {
    val extension = extensions.getByType<KotlinJvmProjectExtension>()
    extension.jvmToolchain(17)
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
    }

    applyCommonOptions(extension.compilerOptions)
}

internal fun DependencyHandlerScope.addBundle(
    catalog: VersionCatalog,
    bundleName: String,
    configuration: String = "implementation",
) {
    catalog.findBundle(bundleName).ifPresent { bundle ->
        bundle.get().forEach { dependency ->
            add(configuration, dependency)
        }
    }
}

private fun Project.applyCommonOptions(
    options: KotlinJvmCompilerOptions,
) {
    val warningsError = providers.gradleProperty("warningsAsErrors").map {
        it.toBoolean()
    }.orElse(false)

    with(options) {
        languageVersion = KotlinVersion.KOTLIN_2_2
        jvmTarget = JvmTarget.JVM_17
        allWarningsAsErrors = warningsError
        freeCompilerArgs = listOf(
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xconsistent-data-class-copy-visibility"
        )
    }
}