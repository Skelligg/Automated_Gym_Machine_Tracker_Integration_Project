plugins {
    java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "be.kdg.integration3"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.webjars:bootstrap:5.3.2")
    runtimeOnly("org.postgresql:postgresql")
    //JSONObject dependencies
    implementation ("org.json:json:20210307")
    // TensorFlow dependencies
    implementation("org.tensorflow:tensorflow-core-platform:0.5.0")

}


tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName("bootRun", JavaExec::class) {
    standardInput = System.`in`
}

