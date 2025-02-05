plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kapt)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.composerandomusers"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.composerandomusers"
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
    buildFeatures {
        compose = true
    }
    sourceSets{
        this.getByName("test"){
            res.srcDir("src/test/res")
            assets.srcDir("src/test/assets")
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
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

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Arch Components
    implementation(libs.androidx.viewmodel)
    implementation(libs.androidx.lifecycle)

    //Navigation
    implementation(libs.androidx.navigation)

    //Coil
    implementation(libs.io.coil.kt.coil3)
    implementation(libs.io.coil.kt.coil3.network)

    //Retrofit
    implementation(libs.com.square)
    implementation(libs.com.gson)
    implementation(libs.squareup.okhttp)
    implementation(libs.squareup.okhttp.logging.interceptor)

    //Unit Test
    testImplementation(libs.coroutine.test)
    testImplementation(libs.mockk)
    testImplementation(libs.squareup.okhttp.mockwebserver)
    testImplementation(libs.org.robolectric)
    testImplementation(libs.turbine.test)
}