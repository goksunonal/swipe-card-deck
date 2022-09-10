plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk =  21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(Dependencies.AndroidX.CORE, Versions.X_CORE)
    implementation(Dependencies.AndroidX.COMPAT, Versions.X_COMPAT)
    implementation(Dependencies.MATERIAL, Versions.MATERIAL)
    implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT, Versions.X_CONSTRAINT_LAYOUT)
    testImplementation(Dependencies.Test.JUNIT, Versions.JUNIT)
    androidTestImplementation(Dependencies.Test.EXT_JUNIT, Versions.EXT_JUNIT)
    androidTestImplementation(Dependencies.Test.ESPRESSO_CORE, Versions.ESPRESSO_CORE)
}