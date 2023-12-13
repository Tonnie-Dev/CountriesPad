pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    
}

plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.60.3"



}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
           setUrl("https://jitpack.io")
        }
    }
}

rootProject.name = "CountriesPad"
include(":app")



include(":core:ui")
include(":core:util")
include(":feature:overview")
include(":data:source")
include(":feature:validator")
include(":feature:stats")
