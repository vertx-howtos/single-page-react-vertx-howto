import org.gradle.api.JavaVersion

val Versions.java_version: JavaVersion
  get() = JavaVersion.VERSION_1_8

val Versions.jdk_version: String
  get() = Versions.java_version.toString()
