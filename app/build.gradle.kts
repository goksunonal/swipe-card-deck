plugins {
    id("com.android.application")
    kotlin("android")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "11"
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
}