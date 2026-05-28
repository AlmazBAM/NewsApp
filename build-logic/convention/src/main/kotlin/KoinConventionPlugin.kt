import configs.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KoinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("api", libs.findLibrary("koin-core").get())
            }
            pluginManager.withPlugin("com.android.base") {
                dependencies {
                    add("api", libs.findLibrary("koin-android").get())
                }
            }

            pluginManager.withPlugin("org.jetbrains.kotlin.plugin.compose") {
                dependencies {
                    add("api", libs.findLibrary("koin-androidx-compose").get())
                }
            }
        }
    }
}
