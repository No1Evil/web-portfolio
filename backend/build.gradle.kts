plugins {
    id("java-library")
}

subprojects {
    apply(plugin = "java-library")

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.46")
        annotationProcessor("org.projectlombok:lombok:1.18.46")

        testCompileOnly("org.projectlombok:lombok:1.18.46")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.46")
    }

    tasks.withType<JavaCompile> {
        options.release.set(21)
    }

    java {
        toolchain {
            JavaLanguageVersion.of(21)
        }
    }

    javaToolchains {
        version = JavaVersion.VERSION_21
    }
}