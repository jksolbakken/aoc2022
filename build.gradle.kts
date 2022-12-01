val junitJupiterVersion = "5.9.1"

val mainClassName = "io.nais.MainKt"

plugins {
   kotlin("jvm") version "1.7.21"
}

java {
   sourceCompatibility = JavaVersion.VERSION_17
   targetCompatibility = JavaVersion.VERSION_17
}

repositories {
   mavenCentral()
}

dependencies {
   implementation(kotlin("stdlib"))
   testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

tasks {

   withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
      kotlinOptions {
         jvmTarget = "17"
      }
   }

   withType<Test> {
      useJUnitPlatform()
      testLogging {
         showExceptions = true
      }
   }

   withType<Wrapper> {
      gradleVersion = "7.6"
   }

}
