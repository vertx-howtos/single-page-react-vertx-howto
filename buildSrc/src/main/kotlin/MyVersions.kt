import org.gradle.api.JavaVersion

val Versions.java_version: JavaVersion
  get() = JavaVersion.VERSION_1_8

val Versions.jdk_version: String
  get() = Versions.java_version.toString()

val Versions.node_version: String
  get() = "10.15.3"

val Versions.npm_version: String
  get() = "6.4.1"

val Versions.yarn_version: String
  get() = "1.9.4"
