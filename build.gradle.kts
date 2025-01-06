buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.springframework.boot") version "2.5.3" apply false
    id("io.spring.dependency-management") version "1.0.8.RELEASE" apply false

    kotlin("jvm") version "1.5.30-RC" apply false
    kotlin("plugin.spring") version "1.5.30-RC" apply false
    kotlin("kapt") version "1.4.32" apply false
}

allprojects {
    group = "com.services-status"
    version = "1.0.0"

    tasks.withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        jvmArgs = listOf("--enable-preview")
    }
}

subprojects {
    repositories {
        mavenCentral()
    }

    apply {
        plugin("io.spring.dependency-management")
    }
}
