plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "com.producttracker"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.apache.commons:commons-math3:3.6.1")
    implementation("org.knowm.xchart:xchart:3.8.7")
}

application {
    mainClass.set("com.producttracker.MainKt")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
