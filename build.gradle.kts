plugins {
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "io.intellij.examples"
version = "1.0.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

repositories {
    maven { url = uri("https://maven.aliyun.com/repository/public/") }
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.16.0")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.springframework.boot:spring-boot-starter-integration")
    implementation("org.springframework.integration:spring-integration-stream")
    implementation("org.springframework.integration:spring-integration-mqtt") {
        exclude("org.eclipse.paho", "org.eclipse.paho.client.mqttv3")
    }
    implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5")

    implementation("com.baomidou:mybatis-plus-boot-starter:3.5.7") {
        exclude("org.mybatis", "mybatis-spring")
    }
    implementation("org.mybatis:mybatis-spring:3.0.4")

    // liquibase
    implementation("org.liquibase:liquibase-core:4.29.1")

    implementation("com.mysql:mysql-connector-j")

    // kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${findProperty("kotlinx.coroutines.version")}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${findProperty("kotlinx.coroutines.version")}")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    // implementation("org.jetbrains.kotlin:kotlin-reflect")
    // implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk17")

    // redis https://www.cnblogs.com/cicada-smile/p/17630594.html
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.integration:spring-integration-redis")

    implementation("com.alibaba.fastjson2:fastjson2:2.0.52")

    // 加密 3.0 的还有一定问题
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:2.1.2")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


tasks.withType<Test> {
    useJUnitPlatform()
}
