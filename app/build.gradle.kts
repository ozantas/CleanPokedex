plugins {
    alias(libs.plugins.androidApplication)
    kotlin("android")
    alias(libs.plugins.ksp)
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    alias(libs.plugins.serialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.room)
}

android {
    compileSdk = AndroidSdk.compile
    defaultConfig {
        applicationId = "com.ozan.cleanpokedex"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName

        testInstrumentationRunner = "com.ozan.cleanpokedex.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = AndroidSdk.jvmVersion
        targetCompatibility = AndroidSdk.jvmVersion
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    kotlin {
        jvmToolchain(17)
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
        }
    }
    namespace = "com.ozan.cleanpokedex"
}

dependencies {
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.icons.extended)
    implementation(libs.compose.foundation)

    implementation(libs.material3)
    implementation(libs.material3.compose)
    implementation(libs.material3.window)
    implementation(libs.activity.compose)
    implementation(libs.viewModel.compose)
    implementation(libs.viewModel)
    implementation(libs.lifecycle.compose)
    implementation(libs.savedstate)

    implementation(libs.coil)
    implementation(libs.coil.network)

    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    ksp(libs.dagger.compiler)

    implementation(libs.kotlinx.serialization)

    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit.serialization.converter)

    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    implementation(libs.navigation)

    debugImplementation(libs.leakcanary)

    androidTestImplementation(composeBom)
    androidTestImplementation(libs.compose.test)
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.room.runtime)
    androidTestImplementation(libs.turbine)
    androidTestImplementation(libs.mockk.android)
    debugImplementation(libs.compose.ui.test.manifest)

    kspAndroidTest(libs.hilt.compiler)
    kspAndroidTest(libs.dagger.compiler)

    testImplementation(libs.robolectric)
    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.rules)
    testImplementation(libs.mockk)
}

room {
    schemaDirectory("$projectDir/schemas")
    generateKotlin = true
}
