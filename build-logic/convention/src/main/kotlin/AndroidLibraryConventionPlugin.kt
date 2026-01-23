import com.android.build.api.dsl.LibraryExtension
import configs.configureAndroidCommon
import configs.configureKotlinAndroid
//import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply(plugin = "com.android.library")
        apply(plugin = "org.jetbrains.kotlin.android")

        extensions.configure<LibraryExtension> {
            configureAndroidCommon(this)
            defaultConfig {
                consumerProguardFiles("consumer-rules.pro")
            }
            packaging {
                resources.excludes += setOf(
                    "/META-INF/{AL2.0,LGPL2.1}",
                    "META-INF/LICENSE.md",
                    "META-INF/LICENSE-notice.md",
                )
            }
        }

        configureKotlinAndroid()
    }
}
