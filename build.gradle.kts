plugins {
  java
  application
}

repositories {
  mavenCentral()
}

dependencies {
  val vertxVersion = "3.7.0"
  implementation("io.vertx:vertx-web:${vertxVersion}")
}

application {
  mainClassName = "io.vertx.howtos.react.BackendVerticle"
}

tasks.wrapper {
  gradleVersion = "5.2"
}
