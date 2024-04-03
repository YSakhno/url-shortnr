@Suppress("UnstableApiUsage") // dependencyResolutionManagement is still 'incubating' in Gradle 8.7
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "url-shortnr"
