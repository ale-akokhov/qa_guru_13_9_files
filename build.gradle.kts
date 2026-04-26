plugins {
    id("java")
}

group = "guru.qa"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.codeborne:selenide:7.16.0")
    testImplementation("com.codeborne:pdf-test:2.1.0",
        "com.codeborne:xls-test:1.7.2")
    testImplementation("org.assertj:assertj-core:4.0.0-M1")
    testImplementation("org.slf4j:slf4j-simple:2.0.16")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.16")
}

tasks.test {
    useJUnitPlatform()
}