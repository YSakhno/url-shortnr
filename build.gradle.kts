plugins {
    java
    alias(libs.plugins.spring.boot)
}

apply(from = "$rootDir/gradle/eclipse.gradle.kts")
apply(from = "$rootDir/gradle/idea.gradle.kts")

dependencies {
    implementation(platform(libs.spring.boot.bom))
    testImplementation(platform(libs.testing.junit.bom))

    implementation(libs.bundles.spring.boot)
    runtimeOnly(libs.database.h2)

    testImplementation(libs.testing.spring.boot)
    testRuntimeOnly(libs.testing.junit.platform)
}

java {
    val javaVersion = libs.versions.javaLanguageCompatibility.get()

    toolchain {
        languageVersion = JavaLanguageVersion.of(javaVersion)
    }

    sourceCompatibility = JavaVersion.toVersion(javaVersion)
    targetCompatibility = JavaVersion.toVersion(javaVersion)
}

tasks.test.configure {
    useJUnitPlatform()
}
