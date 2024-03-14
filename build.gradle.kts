import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.1"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "com.caminha"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

val resilience4jVersion = "2.0.2"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.data:spring-data-r2dbc:3.2.3")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.r2dbc:r2dbc-spi:1.0.0.RELEASE")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.1")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:1.6.4")
	implementation("io.projectreactor:reactor-core:3.5.4")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
	implementation("org.springframework:spring-jdbc")

	/*Resilience4j*/
	implementation("io.github.resilience4j:resilience4j-kotlin:${resilience4jVersion}")
	implementation("io.github.resilience4j:resilience4j-kotlin:${resilience4jVersion}")
	implementation("io.github.resilience4j:resilience4j-retry:${resilience4jVersion}")
	implementation("io.github.resilience4j:resilience4j-spring-boot3:${resilience4jVersion}")
	implementation("io.github.resilience4j:resilience4j-reactor:${resilience4jVersion}")
	/*Resilience4j*/


	developmentOnly("org.springframework.boot:spring-boot-devtools")



}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
