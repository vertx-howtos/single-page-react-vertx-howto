// tag::gradle-npm-plugin[]
import com.moowork.gradle.node.npm.NpmTask

plugins {
  java
  application
  id("com.moowork.node") version "1.3.1"
}
// end::gradle-npm-plugin[]

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

// tag::gradle-frontend-build[]
node { // <1>
  version = "10.15.3"
  npmVersion = "6.4.1"
  download = true
  nodeModulesDir = File("src/main/frontend")
}

val buildFrontend by tasks.creating(NpmTask::class) { // <3>
  setArgs(listOf("run", "build"))
  dependsOn("npmInstall") // <2>
}

val copyToWebRoot by tasks.creating(Copy::class) { // <4>
  from("src/main/frontend/build")
  destinationDir = File("${buildDir}/classes/java/main/webroot")
  dependsOn(buildFrontend)
}

val processResources by tasks.getting(ProcessResources::class) {
  dependsOn(copyToWebRoot)
}
// end::gradle-frontend-build[]

tasks.wrapper {
  gradleVersion = "5.2"
}
