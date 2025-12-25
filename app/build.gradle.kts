plugins {
    alias(libs.plugins.android.application)
<<<<<<< HEAD

    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.maigrainetracker"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.maigrainetracker"
=======
}

android {
    namespace = "com.example.yourapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.yourapp"
>>>>>>> c8ac07a5eb2790780bf427f3d3bcd94edd1d6412
        minSdk = 24
        targetSdk = 36
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
}

dependencies {
<<<<<<< HEAD
    implementation(platform("com.google.firebase:firebase-bom:34.7.0"))
    implementation("com.google.firebase:firebase-analytics")
=======

>>>>>>> c8ac07a5eb2790780bf427f3d3bcd94edd1d6412
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
<<<<<<< HEAD
    implementation(libs.firebase.firestore)
=======
>>>>>>> c8ac07a5eb2790780bf427f3d3bcd94edd1d6412
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}