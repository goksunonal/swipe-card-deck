plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-kapt")
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
    implementation(Dependencies.AndroidX.CORE, Versions.X_CORE)
    implementation(Dependencies.AndroidX.COMPAT, Versions.X_COMPAT)
    implementation(Dependencies.MATERIAL, Versions.MATERIAL)
    implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT, Versions.X_CONSTRAINT_LAYOUT)
    implementation("com.squareup.picasso:picasso:2.8")
    testImplementation(Dependencies.Test.JUNIT, Versions.JUNIT)
    androidTestImplementation(Dependencies.Test.EXT_JUNIT, Versions.EXT_JUNIT)
    androidTestImplementation(Dependencies.Test.ESPRESSO_CORE, Versions.ESPRESSO_CORE)
}