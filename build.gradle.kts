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

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("com.google.cloud:spring-cloud-gcp-starter")

	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")

	/* database dependencies */
	implementation("com.google.cloud:cloud-spanner-spring-data-r2dbc:1.2.2")
	implementation("org.apache.commons:commons-dbcp2:2.9.0")
	implementation("io.r2dbc:r2dbc-spi:0.9.0.RELEASE")
	implementation("org.springframework:spring-jdbc")


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
