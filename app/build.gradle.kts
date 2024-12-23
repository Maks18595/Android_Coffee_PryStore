plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt) // Для Hilt та Room
    alias(libs.plugins.ksp) // Для Kotlin Symbol Processing
    alias(libs.plugins.hilt) // Для плагіну Hilt
}

android {
    namespace = "com.first.android_coffee_prystore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.first.android_coffee_prystore"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        buildConfig = true
    }


    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
            freeCompilerArgs += "-opt-in=kotlin.Experimental"
        }
    }
}

dependencies {
    implementation(libs.gradle.v800)  // Встановіть актуальну версію


    // Основні залежності
    implementation(libs.androidx.annotation)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.timber)
    implementation(libs.androidx.test.espresso.idling.resources)

    implementation(libs.accompanist.insets)

    // Архітектурні компоненти
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.preference)
    implementation(libs.androidx.storage)
    implementation(libs.androidx.storage)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.material.android)
    implementation(libs.androidx.material.android)
    implementation(libs.androidx.ui.test.android)
    ksp(libs.room.compiler)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)

    // Hilt
    implementation(libs.hilt.android.core)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.compiler)

    // Jetpack Compose
    val composeBom = platform(libs.androidx.compose.bom)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.compiler)
    implementation(composeBom)
    implementation(libs.androidx.compose.foundation.core)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.material.core)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.accompanist.appcompat.theme)
    implementation(libs.pager.indicator)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)

    debugImplementation(composeBom)
    debugImplementation(libs.androidx.compose.ui.tooling.core)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Залежності для локальних юніт-тестів
    testImplementation(composeBom)
    testImplementation(libs.junit4)
    testImplementation(libs.androidx.archcore.testing)
    testImplementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.navigation.testing)
    testImplementation(libs.androidx.test.espresso.core)
    testImplementation(libs.androidx.test.espresso.contrib)
    testImplementation(libs.androidx.test.espresso.intents)
    testImplementation(libs.google.truth)
    testImplementation(libs.androidx.compose.ui.test.junit)

    // Юніт-тести для Hilt
    testImplementation(libs.hilt.android.testing)
    kaptTest(libs.hilt.compiler)

    // Залежності для Android тестів
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.junit4)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.compose.ui.test.junit)

    // AndroidX тестування - JVM
    testImplementation(libs.androidx.test.core.ktx)
    testImplementation(libs.androidx.test.ext)
    testImplementation(libs.androidx.test.rules)

    // AndroidX тестування - інструментовані тести
    androidTestImplementation(libs.androidx.test.core.ktx)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.room.testing)
    androidTestImplementation(libs.androidx.archcore.testing)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.espresso.contrib)
    androidTestImplementation(libs.androidx.test.espresso.intents)
    androidTestImplementation(libs.androidx.test.espresso.idling.resources)
    androidTestImplementation(libs.androidx.test.espresso.idling.concurrent)

    // AndroidX тестування - Hilt
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.compiler)

    // Додаткові залежності
    implementation(libs.androidx.benchmark.common)
    implementation(libs.play.services.analytics.impl)
}
