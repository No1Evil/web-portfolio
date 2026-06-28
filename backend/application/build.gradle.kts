plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

description = "Application layer"

dependencies {
    implementation(project(":domain"))
    implementation("io.github.robsonkades:uuidv7:1.0.1")

    implementation("org.springframework.boot:spring-boot-starter")
}

tasks.bootJar {
    enabled = false
}

tasks.bootRun {
    enabled = false
}
