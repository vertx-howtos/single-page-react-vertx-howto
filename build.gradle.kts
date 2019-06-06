import com.moowork.gradle.node.npm.NpmTask

plugins {
  java
  application
  id("com.moowork.node") version "1.3.1"
}

repositories {
  mavenCentral()
}

// tag::dependencies[]
dependencies {
  val vertxVersion = "3.7.0"
  implementation("io.vertx:vertx-web:${vertxVersion}")
}
// end::dependencies[]

// tag::application-main[]
application {
  mainClassName = "io.vertx.howtos.react.BackendVerticle"
}
// end::application-main[]

node {
  version = "10.15.3"
  npmVersion = "6.4.1"
  download = true
  nodeModulesDir = File("src/main/frontend")
}

val buildFrontend by tasks.creating(NpmTask::class) {
  setArgs(listOf("run", "build"))
  dependsOn("npmInstall")
}

val copyToWebRoot by tasks.creating(Copy::class) {
  from("src/main/frontend/build")
  destinationDir = File("${buildDir}/classes/java/main/webroot")
  dependsOn(buildFrontend)
}

val processResources by tasks.getting(ProcessResources::class) {
  dependsOn(copyToWebRoot)
}

tasks.wrapper {
  gradleVersion = "5.2"
}
