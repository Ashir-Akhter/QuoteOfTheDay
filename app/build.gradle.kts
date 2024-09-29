plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.quoteoftheday"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.quoteoftheday"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // Hardcoded API Key for Release Build
            buildConfigField("String", "OPENAI_API_KEY", "\"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\"")
        }
        debug {
            // Hardcoded API Key for Debug Build
            buildConfigField("String", "OPENAI_API_KEY", "\"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\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
        buildConfig = true // Enable BuildConfig
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.7.2"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    // Retrofit dependencies
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
