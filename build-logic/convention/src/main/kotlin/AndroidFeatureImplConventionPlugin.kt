//import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import configs.libs
class AndroidFeatureImplConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply(plugin = "news.android.library")
        apply(plugin = "news.compose")

        dependencies {
            add("implementation", libs.findLibrary("androidx-core-ktx").get())
            add("implementation", project(":core:ui"))
            add("implementation", project(":core:navigation"))

            add("testImplementation", libs.findLibrary("junit").get())
            add("androidTestImplementation", libs.findLibrary("androidx-junit").get())
            add("androidTestImplementation", libs.findLibrary("androidx-espresso-core").get())
        }
    }
}
