import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kotlin")
}

group = "com.graytsar"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-scripting-common:1.8.10")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm:1.8.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    implementation("org.jsoup:jsoup:1.15.3")
    implementation("org.apache.commons:commons-text:1.9")
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")
    implementation("com.squareup.okhttp3:okhttp-brotli:5.0.0-alpha.11")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:5.0.0-alpha.11")

    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter", version = "5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}