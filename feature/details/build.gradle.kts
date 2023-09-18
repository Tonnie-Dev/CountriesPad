@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.devtools.ksp)
}


android {
    namespace = "com.uxstate.details"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    ksp {
        arg("compose-destinations.moduleName", "details")
        arg("compose-destinations.mode", "destinations")
    }
}

dependencies {

    //Libs
    implementation(libs.compose.destinations.core)
    ksp (libs.compose.destinations.ksp)

    implementation(libs.compose.runtime)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.coil)
    implementation(libs.compose.maps)
    implementation(libs.play.services.maps)
    implementation(libs.material3)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    androidTestImplementation(libs.junit4.ui.test)
    //modules
    implementation(project(":core:ui"))
    implementation(project(":core:util"))

}