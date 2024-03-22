import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.0"
	id("org.graalvm.buildtools.native").version("0.9.24")
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	jacoco
}

group = "com.caminha"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

/*graalvmNative {
	binaries {
		named("main") {
			buildArgs.add("--enable-preview")
			buildArgs.add("--verbose")
			buildArgs.add("--add-opens=java.base/java.nio=ALL-UNNAMED")
			buildArgs.add("--add-opens=java.base/jdk.internal.misc=ALL-UNNAMED")
			buildArgs.add("--add-opens=java.base/jdk.internal.ref=ALL-UNNAMED")
			buildArgs.add("--initialize-at-build-time=org.slf4j.LoggerFactory,ch.qos.logback")
			buildArgs.add("--initialize-at-run-time=io.netty")
			buildArgs("-H:ReflectionConfigurationFiles=../../../src/main/resources/reflection-config.json")
		}
		all {
			buildArgs.add("--verbose")
			resources.autodetect()
		}
	}
}*/

repositories {
	mavenCentral()
	gradlePluginPortal()
}

extra["springCloudGcpVersion"] = "5.0.4"
extra["springCloudVersion"] = "2022.0.4"
extra["testcontainersVersion"] = "1.17.6"

val resilience4jVersion = "2.0.2"

dependencies {


	/*R2DBC Dependencies*/
	implementation("io.r2dbc:r2dbc-spi:1.0.0.RELEASE")
	implementation("org.springframework.data:spring-data-r2dbc:3.2.3")
	implementation("com.google.cloud:spring-cloud-gcp-starter-sql-postgres-r2dbc")
	/*R2DBC Dependencies*/

	/*GCP Common Dependencies*/
	implementation("com.google.cloud:spring-cloud-gcp-starter")
	implementation("com.google.cloud:spring-cloud-gcp-starter-storage")
	implementation(platform("com.google.cloud:libraries-bom:26.2.0"))
	/*GCP Common Dependencies*/

	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
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

dependencyManagement {
	imports {
		mavenBom("com.google.cloud:spring-cloud-gcp-dependencies:${property("springCloudGcpVersion")}")
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
		mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
		mavenBom("io.micrometer:micrometer-bom:1.10.4")
	}
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
