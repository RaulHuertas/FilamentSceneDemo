package com.rhuertas.filamentscenedemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource

import filamentscenedemo.app.shared.generated.resources.Res
import filamentscenedemo.app.shared.generated.resources.compose_multiplatform
import io.github.erkko68.filament.compose.FilamentSceneView
import io.github.erkko68.filament.compose.scene.Bloom
import io.github.erkko68.filament.compose.scene.DirectionalLight
import io.github.erkko68.filament.compose.scene.Direction
import io.github.erkko68.filament.compose.scene.GltfInstance
import io.github.erkko68.filament.compose.scene.LightIntensity
import io.github.erkko68.filament.compose.scene.LinearColor
import io.github.erkko68.filament.compose.scene.Position
import io.github.erkko68.filament.compose.scene.PostProcessing
import io.github.erkko68.filament.compose.scene.Rotation
import io.github.erkko68.filament.compose.scene.SkyboxSource
import io.github.erkko68.filament.compose.scene.rememberCameraState
import io.github.erkko68.filament.compose.scene.rememberGltfAsset
import io.github.erkko68.filament.compose.scene.rememberSkyboxState

@Composable
@Preview
fun App() {
    val rotation by rememberInfiniteTransition(label = "duck-spin").animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 6000, easing = LinearEasing),
        ),
        label = "duck-spin",
    )

    MaterialTheme {
        FilamentSceneView(
            modifier       = Modifier.fillMaxSize(),
            cameraState    = rememberCameraState(initialEye = Position(0f, 1f, 4f)),
            skyboxState    = rememberSkyboxState(SkyboxSource.Color(LinearColor(0.1f, 0.12f, 0.15f))),
            postProcessing = PostProcessing(bloom = Bloom(strength = 0.2f)),
        ) {
            DirectionalLight(direction = Direction(0.3f, -1f, -0.5f), intensity = LightIntensity.LuminousPower(100_000f))
            GltfInstance(
                asset = rememberGltfAsset { Res.readBytes("files/Duck.glb") },
                rotation = Rotation.axisAngle(Direction.Up, degrees = rotation),
            )
        }
    }
}