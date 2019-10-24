// tag::gradle-npm-plugin[]
import com.moowork.gradle.node.yarn.YarnTask
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version Versions.org_jetbrains_kotlin_jvm_gradle_plugin
  kotlin("kapt") version Versions.org_jetbrains_kotlin_jvm_gradle_plugin
  application
  id("org.jlleitschuh.gradle.ktlint") version "9.0.0"
  id("com.moowork.node") version Versions.com_moowork_node_gradle_plugin
  id("de.fayard.refreshVersions") version Versions.de_fayard_refreshversions_gradle_plugin
}
// end::gradle-npm-plugin[]

repositories {
  mavenCentral()
}

// tag::dependencies[]
dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation(kotlin("reflect"))
  implementation(Libs.slf4j_api)
  implementation(Libs.logback_classic)
  implementation(Libs.kotlin_logging)
  implementation(Libs.vertx_core)
  implementation(Libs.vertx_lang_kotlin)
  implementation(Libs.vertx_lang_kotlin_coroutines)
  implementation(Libs.vertx_web)
  implementation(Libs.vertx_web_api_contract)
//  implementation("io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}")
//  implementation("io.reactivex.rxjava2:rxjava:${Versions.rxjava}")
//  implementation("io.vertx:vertx-rx-java2:${Versions.io_vertx}")

  testImplementation(Libs.vertx_unit)
  testImplementation(Libs.vertx_junit5)
  testImplementation(kotlin("test"))
  testImplementation(kotlin("test-junit"))
  testRuntimeOnly(Libs.junit_jupiter_engine)
  testImplementation(Libs.junit_jupiter_api)
}
// end::dependencies[]

java {
  sourceCompatibility = Versions.java_version
  targetCompatibility = Versions.java_version
}

// tag::application-main[]
application {
  mainClassName = "io.vertx.howtos.react.BackendVerticle"
}
// end::application-main[]

// tag::gradle-frontend-build[]
node { // <1>
  version = "10.15.3"
  npmVersion = "6.4.1"
  yarnVersion = "1.9.4"
  download = true
  nodeModulesDir = File("src/main/frontend")
}

tasks {
  withType<KotlinCompile> {
    kotlinOptions {
      allWarningsAsErrors = true
      jvmTarget = Versions.jdk_version
    }
  }

  val buildFrontend by creating(YarnTask::class) { // <3>
    args = listOf("build")
    dependsOn("yarn") // <2>
  }

  val copyToWebRoot by creating(Copy::class) { // <4>
    from("src/main/frontend/build")
    destinationDir = File("${buildDir}/classes/kotlin/main/webroot")
    dependsOn(buildFrontend)
  }

  "processResources"(ProcessResources::class) {
    dependsOn(copyToWebRoot)
  }
// end::gradle-frontend-build[]

  val jest by creating(YarnTask::class) {
    setEnvironment(mapOf("CI" to "true"))
    args = listOf("test")
    dependsOn("yarn")
  }

  test {
    useJUnitPlatform()
    testLogging {
      events.addAll(listOf(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED))
    }
    dependsOn(jest)
  }
}
