// tag::gradle-npm-plugin[]
import com.github.gradle.node.npm.task.NpmTask

plugins {
  java
  application
  id("com.github.node-gradle.node") version "7.0.2"
}
// end::gradle-npm-plugin[]

repositories {
  mavenCentral()
}

// tag::dependencies[]
dependencies {
  val vertxVersion = "5.0.0.CR2"
  implementation("io.vertx:vertx-web:${vertxVersion}")
}
// end::dependencies[]

// tag::application-main[]
application {
  mainClass = "io.vertx.howtos.react.BackendVerticle"
}
// end::application-main[]

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(11))
  }
}

// tag::gradle-frontend-build[]
node {
  version = "22.12.0"
  npmVersion = "10.9.0"
  download = true
  nodeProjectDir = File("src/main/frontend")
}

val buildFrontend by tasks.creating(NpmTask::class) {
  args = listOf("run", "build")
  dependsOn("npm_install")
}

val copyToWebRoot by tasks.creating(Copy::class) {
  from("src/main/frontend/build")
  destinationDir = File("build/classes/java/main/webroot")
  dependsOn(buildFrontend)
}

val processResources by tasks.getting(ProcessResources::class) {
  dependsOn(copyToWebRoot)
}
// end::gradle-frontend-build[]
