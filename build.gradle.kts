plugins {
	java
	id("org.springframework.boot") version "2.7.7"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "cz.scarecrows"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

val mapstructVersion = "1.5.0.Final"
val liquibaseVersion = "4.18.0"
val swaggerIntegrationVersion = "3.0.0"
val okHttpClientVersion = "5.0.0-alpha.10"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.liquibase:liquibase-core:$liquibaseVersion")
	implementation("org.mapstruct:mapstruct:$mapstructVersion")
	implementation("io.springfox:springfox-boot-starter:$swaggerIntegrationVersion")
	implementation("com.squareup.okhttp3:okhttp:$okHttpClientVersion")
	compileOnly("org.mapstruct:mapstruct-processor:$mapstructVersion")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	runtimeOnly("com.h2database:h2:2.1.214")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
