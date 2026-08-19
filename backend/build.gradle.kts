import org.gradle.testing.jacoco.tasks.JacocoReport

plugins {
    id("java-library")

}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "jacoco")

    repositories {
        mavenCentral()
    }

    dependencies {
        val libs = rootProject.extensions.getByType<VersionCatalogsExtension>().named("libs")
        val lombok = libs.findLibrary("lombok").get()

        compileOnly(lombok)
        annotationProcessor(lombok)
        testCompileOnly(lombok)
        testAnnotationProcessor(lombok)
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

    tasks.withType<JacocoReport> {
        reports {
            csv.required.set(true)
        }
    }
}