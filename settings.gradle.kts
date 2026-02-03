pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
    }
}

rootProject.name = "newsApp"
includeBuild("build-logic")
include(":app")
include(":feature")
include(":feature:news")
include(":feature:setting")
include(":core")
include(":core:domain")
include(":core:data")
include(":core:navigation")
include(":core:ui")
include(":core:common")
include(":sync")
include(":core:notifications")
