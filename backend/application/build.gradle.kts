plugins {
    id("org.springframework.boot") version "4.0.4"
    id("io.spring.dependency-management") version "1.1.7"
}

description = "Application layer"

dependencies {
    implementation(project(":domain"))

    implementation("org.springframework.boot:spring-boot-starter")
}

tasks.bootJar {
    enabled = false
}

tasks.bootRun {
    enabled = false
}
