plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.kamabd.exchangeratesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kamabd.exchangeratesapp"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "BaseApiUrl", "\"https://api.apilayer.com/\"")
            buildConfigField("String", "ApiKey", "\"JQQEprD4ziu8EcpKrDEkqnQ4pgsVgSZR\"")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        release {
            isMinifyEnabled = false
            buildConfigField("String", "BaseApiUrl", "\"https://api.apilayer.com/\"")
            buildConfigField("String", "ApiKey", "\"JQQEprD4ziu8EcpKrDEkqnQ4pgsVgSZR\"")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":base"))
    implementation(project(":network"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":uikit"))
    implementation(project(":logger"))
    implementation(project(":i_currencies"))

    implementation("androidx.core:core-ktx:${Version.ktx_version}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.compose.ui:ui:${Version.compose_version}")
    implementation("androidx.compose.material:material:${Version.compose_version}")
    implementation("androidx.compose.material:material-icons-extended:${Version.compose_version}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Version.compose_version}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.4")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.32.0")

    // compose navigation
    implementation("androidx.navigation:navigation-compose:2.7.4")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.32.0")
    // multidex
    implementation("androidx.multidex:multidex:2.0.1")

    // hilt
    implementation("com.google.dagger:hilt-android:2.48")
    ksp("com.google.dagger:hilt-android-compiler:2.48")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // test-utils
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

object Version {
    const val ktx_version = "1.12.0"
    const val compose_version = "1.5.3"
    const val compose_material = "1.1.2"
}