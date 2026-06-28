description = "Application layer"

dependencies {
    implementation(project(":domain"))
    implementation("io.github.robsonkades:uuidv7:1.0.1")


tasks.bootJar {
    enabled = false
}

tasks.bootRun {
    enabled = false
}
