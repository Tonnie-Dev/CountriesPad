import org.jetbrains.kotlin.gradle.utils.API
import java.util.Properties
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")

}

android {
    compileSdk = ProjectConfig.compileSdk
    defaultConfig {
        applicationId ="com.uxstate.countriespad"
        minSdk = ProjectConfig.minSdk
        targetSdk =ProjectConfig.targetSdk
        versionCode  =2
        versionName ="2.0"
        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        //load the values from .properties file
        val mapsKeyFile = project.rootProject.file("mapskey.properties")
        val properties = Properties()
        properties.load(mapsKeyFile.inputStream())

        //fetch the map key
        val apiKey = properties.getProperty("MAPS_API_KEY") ?: ""


        manifestPlaceholders["google"] = apiKey


    }

 /*   signingConfigs {

       create("release") {

            val tmpFilePath = System.getProperty("user.home") + "/work/_temp/keystore/"
            val allFilesFromDir = File(tmpFilePath).listFiles()

            if (allFilesFromDir != null) {
                val keystoreFile = allFilesFromDir.first()
                keystoreFile.renameTo(File("keystore/your_keystore.jks"))
            }

            storeFile = file("keystore/your_keystore.jks")
            storePassword = System.getenv("SIGNING_STORE_PASSWORD")
            keyAlias = System.getenv("SIGNING_KEY_ALIAS")
            keyPassword = System.getenv("SIGNING_KEY_PASSWORD")


        }
    }*/


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
        sourceCompatibility =JavaVersion.VERSION_18
        targetCompatibility= JavaVersion.VERSION_18

    }
    kotlinOptions {
        jvmTarget = "18"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ProjectConfig.kotlinCompilerExtensionVersion
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


    namespace = "com.uxstate.countriespad"
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.runtime)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.compose.activity)
    testImplementation(libs.junit.core)
    androidTestImplementation(libs.junit.androidx)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.junit4.ui.test)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    //Material 3
    implementation(libs.material3)

    // Coil
    implementation(libs.compose.coil)

    // Dagger Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.scalars)

    //Moshi Library Dependencies - Core Moshi JSON Library and Moshi")s Kotlin support and converter factory
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)

    // Room components
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.room.ktx)

    //Timber
    implementation(libs.timber)

    //Flow Layout
    implementation(libs.accompanist.flowlayout)

    //Lottie Animation
    implementation(libs.lottie.compose)

    // Compose Destinations and KSP Libs
    implementation(libs.compose.destinations.core)
    ksp(libs.compose.destinations.core)

    //AndroidX Compose Navigation Lib
    implementation(libs.compose.navigation)

    //AndroidX Compose Animation Lib
    implementation (libs.compose.animation)


    //Accompanist Animation
    //implementation libs.accompanist.nav.animation

    //Maps Compose library
    implementation(libs.compose.maps)
    implementation(libs.play.services.maps)

    //Kompose Country Code Picker
    implementation(libs.komposecountrycodepicker)

    //Modules
    implementation(project(":core:ui"))
    implementation(project(":core:util"))
    implementation(project(":feature:overview"))
    implementation(project(":feature:validator"))
    implementation(project(":feature:stats"))
    implementation(project(":data:source"))

}



