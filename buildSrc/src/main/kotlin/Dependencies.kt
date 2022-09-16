object Dependencies {
    const val MATERIAL = "com.google.android.material:material"
    const val PICASSO = "com.squareup.picasso:picasso"
    const val KOIN_ANDROID = "io.insert-koin:koin-android"
    const val KOIN_CORE = "io.insert-koin:koin-core"

    object AndroidX {
        const val CORE = "androidx.core:core-ktx"
        const val COMPAT = "androidx.appcompat:appcompat"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout"
        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx"

        object Lifecycle {
            const val LIVE_DATA = "androidx.lifecycle:lifecycle-livedata-ktx"
            const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx"
            const val RUN_TIME = "androidx.lifecycle:lifecycle-runtime-ktx"
        }
    }

    object Ktor {
        const val CORE = "io.ktor:ktor-client-core"
        const val SERIALIZATION = "io.ktor:ktor-client-serialization"
        const val LOGGING = "io.ktor:ktor-client-logging"
        const val ANDROID = "io.ktor:ktor-client-android"
        const val CONTENT_NEGOTIATION = "io.ktor:ktor-client-content-negotiation"
        const val JSON = "io.ktor:ktor-serialization-kotlinx-json"
    }

    object Test {
        const val JUNIT = "junit:junit"
        const val EXT_JUNIT = "androidx.test.ext:junit"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core"
        const val INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    }
}