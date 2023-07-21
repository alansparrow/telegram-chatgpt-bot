plugins {
    id("java")
}

group = "com.fuzzyrock"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.telegram:telegrambots:6.7.0")
    implementation("org.json:json:20230618")
}

tasks.test {
    useJUnitPlatform()
}