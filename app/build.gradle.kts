plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.mizanservicecenter.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mizanservicecenter.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            // These properties can be provided via gradle.properties or environment variables
            // For now, they are empty or point to placeholders so you can fill them in later
            val keystoreFile = project.findProperty("RELEASE_STORE_FILE") as String? ?: ""
            val keystorePassword = project.findProperty("RELEASE_STORE_PASSWORD") as String? ?: ""
            val keyAliasStr = project.findProperty("RELEASE_KEY_ALIAS") as String? ?: ""
            val keyPasswordStr = project.findProperty("RELEASE_KEY_PASSWORD") as String? ?: ""

            if (keystoreFile.isNotEmpty()) {
                storeFile = file(keystoreFile)
                storePassword = keystorePassword
                keyAlias = keyAliasStr
                keyPassword = keyPasswordStr
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.webkit)


    // Navigation & Animations
    implementation(libs.androidx.navigation.compose)
    implementation(libs.lottie.compose)

    // Biometrics
    implementation(libs.androidx.biometric)

    debugImplementation(libs.androidx.ui.tooling)
}
