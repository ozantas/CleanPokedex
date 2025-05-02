plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.hiltAndroid) apply false
    kotlin("android").version(libs.versions.kotlin.asProvider().get()).apply(false)
    alias(libs.plugins.serialization) apply false
    alias(libs.plugins.androidTest) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.compose.compiler) apply false
}

buildscript {
    dependencies {
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}