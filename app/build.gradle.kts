plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    namespace = "com.panducerdas.id"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.panducerdas.id"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packagingOptions {
        resources {
            resources.excludes.add("META-INF/*")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            buildConfigField("String", "GEMINI_API_KEY", "\"AIzaSyBkoVA71MIUxqnX5hmrvrChWwFuFY_XjSY\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "GEMINI_API_KEY", "\"AIzaSyBkoVA71MIUxqnX5hmrvrChWwFuFY_XjSY\"")
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity:1.8.0")
    implementation("androidx.paging:paging-common-android:3.3.2")
    implementation("androidx.databinding:databinding-runtime:8.5.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    //network
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.google.code.gson:gson:2.9.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //Live Data
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    //Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")
    ksp ("androidx.room:room-compiler:2.6.1")

    //PDF
    implementation("com.tom-roush:pdfbox-android:2.0.21.0")

    //Paaging 3
    implementation("androidx.paging:paging-runtime-ktx:3.3.2")

    //Fragment
    implementation ("androidx.fragment:fragment-ktx:1.6.1")

    //navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.5")

    //ViewPager 2
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    //circle image
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    //Gemini AI
    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")

    //Porcuvoice
    implementation("ai.picovoice:porcupine-android:3.0.0")

    //Text To Speech
    implementation("com.google.cloud:google-cloud-texttospeech:2.52.0")

    //Lottie
    implementation ("com.airbnb.android:lottie:6.0.0")
}
