plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.chetile"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.chetile"
        minSdk = 26 // Минимальная версия SDK
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
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1" // Обновите до актуальной версии компилятора
    }

    packagingOptions {
        resources.excludes.add("META-INF/LICENSE")
        resources.excludes.add("META-INF/NOTICE")
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/INDEX.LIST")
        resources.excludes.add("META-INF/versions/9/module-info.class") // Исключить конфликтующие файлы
    }
}

dependencies {
    // Основные библиотеки Android
    implementation("androidx.core:core-ktx:1.9.0") // Core библиотеки Android
    implementation("androidx.appcompat:appcompat:1.6.1") // Библиотека для совместимости
    implementation("androidx.activity:activity-ktx:1.7.2") // Для работы с активностями
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0") // Жизненный цикл

    // Jetpack Compose
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0")
    implementation("androidx.activity:activity-compose:1.4.0")
    // Для работы с Activity и Compose
    implementation("androidx.activity:activity-compose:1.4.0")

    // Для работы с ViewModel и состояния
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0") // ViewModel для Compose
    implementation("androidx.navigation:navigation-compose:2.6.0") // Навигация для Compose

    // Для работы с Excel (Apache POI)
    implementation("org.apache.poi:poi-ooxml:5.2.3") // Для работы с Excel

    // Прочие зависимости для работы с Compose
    implementation("androidx.compose.runtime:runtime-livedata:1.4.3") // Для работы с LiveData и Compose

    // Тестирование (JUnit, Espresso, Compose тесты)
    testImplementation("junit:junit:4.13.2") // JUnit для юнит-тестов
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // JUnit для Android тестов
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") // Espresso для UI тестов
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3") // Compose тесты
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.3") // Инструменты для отладки Compose
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.3") // Инструменты для тестирования в Compose
}

