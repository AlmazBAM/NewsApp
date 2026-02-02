import configs.addBundle
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import configs.libs

class AndroidDataConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply(plugin = "news.android.library")
        apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
        apply(plugin = "com.google.devtools.ksp")


        dependencies {
            add("implementation", project(":core:domain"))
            add("implementation", project(":core:common"))
            add("implementation", libs.findLibrary("androidx-core-ktx").get())

            addBundle(libs, "room")
            add("ksp", libs.findLibrary("room-compiler").get())

            addBundle(libs, "ktor")
            addBundle(libs, "koin", configuration = "api")

            add("implementation", libs.findLibrary("kotlinx-serialization-json").get())
            add("implementation", libs.findLibrary("androidx-datastore").get())

            add("testImplementation", libs.findLibrary("junit").get())
            add("androidTestImplementation", libs.findLibrary("androidx-junit").get())
            add("androidTestImplementation", libs.findLibrary("androidx-espresso-core").get())
        }
    }
}
