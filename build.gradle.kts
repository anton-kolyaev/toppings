import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.4"
	id("io.spring.dependency-management") version "1.0.14.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "com.toppings"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.3")
	implementation("org.springframework.boot:spring-boot-starter-web:2.7.3")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.4")
	implementation("org.springframework.boot:spring-boot-starter-security:2.7.3")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springdoc:springdoc-openapi-data-rest:1.6.11")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.11")
	implementation("org.springdoc:springdoc-openapi-kotlin:1.6.11")
	runtimeOnly("com.h2database:h2:2.1.214")
	testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.3")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
