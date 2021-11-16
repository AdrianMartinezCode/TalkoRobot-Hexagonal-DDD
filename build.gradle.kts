import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlin_version = "1.3.72"

plugins {
	id("org.springframework.boot") version "2.4.12"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.32"
	kotlin("plugin.spring") version "1.4.32"
	application
	java
}

group = "com.adrianmartinezcode"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
val arrowVersion by extra { "0.10.5" }

repositories {
	mavenCentral()
}

dependencies {
	testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	runtimeOnly("com.h2database:h2")
//	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("io.arrow-kt:arrow-core:$arrowVersion")
	implementation("io.arrow-kt:arrow-fx:$arrowVersion")
	implementation("io.arrow-kt:arrow-optics:$arrowVersion")
	implementation("io.arrow-kt:arrow-syntax:$arrowVersion")
//	testImplementation(kotlin("test"))
}

tasks.withType<KotlinCompile>() {
	kotlinOptions.jvmTarget = "1.8"
}
tasks.test {
	useJUnit()
	useJUnitPlatform()
}
//tasks.withType<Test> {
//	useJUnitPlatform()
//}

tasks.jar {
	manifest {
		attributes["Main-Class"] = "com.adrianmartinezcode.talkorobot.TalkoRobotApplicationKt"
	}
	configurations["compileClasspath"].forEach { file: File ->
		from(zipTree(file.absoluteFile))
	}

	duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

application {
	mainClassName = "com.adrianmartinezcode.talkorobot.TalkoRobotApplicationKt"
	mainClass.set("com.adrianmartinezcode.talkorobot.TalkoRobotApplicationKt")
}

