package dependencies

object Build {
    val build_tools = "com.android.tools.build:gradle:${Versions.gradle}"
    val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val navigation_safe_args = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav_components}"
    val junit5 = "de.mannodermaus.gradle.plugins:android-junit5:1.3.2.0"
}