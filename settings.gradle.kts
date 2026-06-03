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
include(":core")
include(":core:analytics")
include(":core:common")
include(":core:domain")
include(":core:data")
include(":core:database")
include(":core:navigation")
include(":core:notifications")
include(":core:ui")
include(":feature")
include(":feature:news")
include(":feature:setting")
include(":sync")
