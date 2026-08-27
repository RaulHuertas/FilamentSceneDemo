import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    jvmToolchain(22)
}

dependencies {
    implementation(project(":app:shared"))

    implementation(compose.desktop.currentOs)
    runtimeOnly(libs.skiko.awt.runtime.windows.x64)
    runtimeOnly(libs.skiko.awt.runtime.linux.x64)
    runtimeOnly(libs.skiko.awt.runtime.linux.arm64)
    runtimeOnly(libs.skiko.awt.runtime.macos.x64)
    runtimeOnly(libs.skiko.awt.runtime.macos.arm64)
    implementation(libs.kotlinx.coroutinesSwing)

    implementation(libs.compose.uiToolingPreview)
}

compose.desktop {
    application {
        mainClass = "com.rhuertas.filamentscenedemo.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.rhuertas.filamentscenedemo"
            packageVersion = "1.0.0"
        }
    }
}