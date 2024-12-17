plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("io.realm.kotlin")
}

android {
    namespace = "devandroid.nex.aularealm"
    compileSdk = 35

    defaultConfig {
        applicationId = "devandroid.nex.aularealm"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    //dependencias do banco de dados realm
    implementation ("io.realm.kotlin:library-base:1.16.0")
    // If using Device Sync
    implementation ("io.realm.kotlin:library-sync:1.16.0")
    // If using coroutines with the SDK
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")


    //dependencias padrões
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}