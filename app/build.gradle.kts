plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    kotlin("plugin.serialization").version("1.7.10")
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        applicationId = "com.profile.cardswipe"
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = Dependencies.Test.INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(":lib"))
    implementation(Dependencies.AndroidX.CORE, Versions.X_CORE)
    implementation(Dependencies.AndroidX.COMPAT, Versions.X_COMPAT)
    implementation(Dependencies.MATERIAL, Versions.MATERIAL)
    implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT, Versions.X_CONSTRAINT_LAYOUT)
    testImplementation(Dependencies.Test.JUNIT, Versions.JUNIT)
    androidTestImplementation(Dependencies.Test.EXT_JUNIT, Versions.EXT_JUNIT)
    androidTestImplementation(Dependencies.Test.ESPRESSO_CORE, Versions.ESPRESSO_CORE)

    implementation(Dependencies.Ktor.CORE, Versions.KTOR)
    implementation(Dependencies.Ktor.SERIALIZATION, Versions.KTOR)
    implementation(Dependencies.Ktor.LOGGING, Versions.KTOR)
    implementation(Dependencies.Ktor.ANDROID, Versions.KTOR)
    implementation(Dependencies.Ktor.CONTENT_NEGOTIATION, Versions.KTOR)
    implementation(Dependencies.Ktor.JSON, Versions.KTOR)

    implementation(Dependencies.AndroidX.Lifecycle.RUN_TIME, Versions.LIFECYCLE)
    implementation(Dependencies.AndroidX.Lifecycle.LIVE_DATA, Versions.LIFECYCLE)
    implementation(Dependencies.AndroidX.Lifecycle.VIEW_MODEL, Versions.LIFECYCLE)
    implementation(Dependencies.KOIN_ANDROID, Versions.KOIN)
    implementation(Dependencies.KOIN_CORE, Versions.KOIN)

    implementation(Dependencies.AndroidX.FRAGMENT_KTX, Versions.X_FRAGMENT_KTX)
}