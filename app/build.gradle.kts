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
    implementation("androidx.paging:paging-runtime:3.1.1")
    implementation("io.ktor:ktor-client-core:2.1.1")
    implementation("io.ktor:ktor-client-serialization:2.1.1")
    implementation("io.ktor:ktor-client-logging:2.1.1")
    implementation("io.ktor:ktor-client-android:2.1.1")
    implementation("io.ktor:ktor-client-content-negotiation:2.1.1")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.1.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.fragment:fragment-ktx:1.5.2")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
}