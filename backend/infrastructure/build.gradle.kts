import org.gradle.kotlin.dsl.named
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.openapi)
}

description = "Application layer"

dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))

    implementation("jakarta.validation:jakarta.validation-api:3.1.1")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.springframework.boot:spring-boot-starter-liquibase")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.2")
    runtimeOnly("org.postgresql:postgresql")

    implementation(libs.mapstruct)
    annotationProcessor(libs.mapstruct.processor)

    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testImplementation("org.assertj:assertj-core")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<BootBuildImage>("bootBuildImage") {
    val gradleProviders = providers
    imageName.set(gradleProviders.gradleProperty("imageName").orElse("portfolio-backend"))
    publish.set(gradleProviders.gradleProperty("publishImage").map { it.toBoolean() }.orElse(false))
    docker {
        publishRegistry {
            username.set(gradleProviders.environmentVariable("REGISTRY_USERNAME").orElse(""))
            password.set(gradleProviders.environmentVariable("REGISTRY_PASSWORD").orElse(""))
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.named("forkedSpringBootRun") {
    dependsOn(":application:jar", ":domain:jar")
}

openApi {
    apiDocsUrl.set("http://localhost:8080/v3/api-docs.yaml")
    outputFileName.set("openapi.yaml")
}