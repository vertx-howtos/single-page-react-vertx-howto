@file:Suppress("UnstableApiUsage")

import com.moowork.gradle.node.yarn.YarnTask
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version Versions.org_jetbrains_kotlin_jvm_gradle_plugin
  kotlin("kapt") version Versions.org_jetbrains_kotlin_jvm_gradle_plugin
  application
  id("org.jlleitschuh.gradle.ktlint") version Versions.org_jlleitschuh_gradle_ktlint_gradle_plugin
  id("com.moowork.node") version Versions.com_moowork_node_gradle_plugin
  id("de.fayard.refreshVersions") version Versions.de_fayard_refreshversions_gradle_plugin
  jacoco
  id("com.github.johnrengelman.shadow") version Versions.com_github_johnrengelman_shadow_gradle_plugin
  id("io.vertx.vertx-plugin") version Versions.io_vertx_vertx_plugin_gradle_plugin
}

repositories {
  mavenCentral()
}

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

java {
  sourceCompatibility = Versions.java_version
  targetCompatibility = Versions.java_version
}

application {
  mainClassName = "io.vertx.core.Launcher"
}

node {
  version = Versions.node_version
  npmVersion = Versions.npm_version
  yarnVersion = Versions.yarn_version
  download = true
  nodeModulesDir = File("src/main/frontend")
}

vertx {
  mainVerticle = "io.vertx.howtos.MainVerticle"
  vertxVersion = Versions.io_vertx
}

tasks {
  withType(KotlinCompile::class).all {
    kotlinOptions {
      allWarningsAsErrors = true
      jvmTarget = Versions.jdk_version
    }
  }

  val buildFrontend by creating(YarnTask::class) {
    args = listOf("build")
    dependsOn("yarn")
  }

  val copyToWebRoot by creating(Copy::class) {
    from("src/main/frontend/build")
    destinationDir = File("$buildDir/classes/kotlin/main/webroot")
    dependsOn(buildFrontend)
  }

  "processResources"(ProcessResources::class) {
    dependsOn(copyToWebRoot)
  }

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

  jacocoTestReport {
    reports {
      xml.isEnabled = false
      csv.isEnabled = false
      html.isEnabled = true
      html.destination = file("$buildDir/reports/coverage")
    }
  }

  jacocoTestCoverageVerification {
    violationRules {
      rule {
        limit {
          minimum = "0.5".toBigDecimal()
        }
      }
    }
  }
}
