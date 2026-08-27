package com.rhuertas.filamentscenedemo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    val osName = System.getProperty("os.name") ?: ""
    val osArch = System.getProperty("os.arch") ?: ""
    val isLinuxArm64 = osName.contains("Linux", ignoreCase = true) &&
        (osArch == "arm64" || osArch == "aarch64")

    if (isLinuxArm64) {
        System.setProperty("skiko.renderApi", "OPENGL")
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "FilamentSceneDemo",
    ) {
        App()
    }
}