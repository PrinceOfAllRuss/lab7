/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    id("multilib.kotlin-application-conventions")
}

dependencies {
    implementation("org.apache.commons:commons-text")
    implementation("io.insert-koin:koin-core:3.3.3")
    implementation("com.thoughtworks.xstream:xstream:1.4.20")
    testImplementation(kotlin("test"))
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.11.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.+")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation(project(":utilities"))
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

application {
    // Define the main class for the application.
    mainClass.set("multilib.client.ClientKt")
}
