import org.gradle.api.JavaVersion

object AndroidSdk {
    const val min = 27
    const val compile = 35
    const val target = compile
    const val versionCode = 8
    const val versionName = "1.0"
    val jvmVersion = JavaVersion.VERSION_17
}