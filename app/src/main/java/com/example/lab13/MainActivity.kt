package com.example.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

internal val Mint = Color(0xFF82F5CE)
internal val Navy = Color(0xFF10172A)
internal val Panel = Color(0xFF1B2640)

private val exercises = listOf(
    "Visibilidad" to "AnimatedVisibility",
    "Cambio de color" to "animateColorAsState",
    "Tamaño y posición" to "animateDpAsState",
    "Cambio de contenido" to "AnimatedContent",
    "Órbita de rescate" to "Prototipo de videojuego",
    "Codelab de animaciones" to "Puntos adicionales",
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT))
        setContent {
            MaterialTheme(colorScheme = darkColorScheme(
                primary = Mint,
                onPrimary = Navy,
                background = Navy,
                surface = Panel,
            )) {
                Laboratory()
            }
        }
    }
}

@Composable
private fun Laboratory() {
    var screen by rememberSaveable { mutableIntStateOf(-1) }
    BackHandler(screen >= 0) { screen = -1 }
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        key(screen) {
            Column(
                modifier = Modifier.fillMaxSize().safeDrawingPadding()
                    .verticalScroll(rememberScrollState()).padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                if (screen < 0) {
                    Text("PROGRAMACIÓN EN MÓVILES", color = Mint, style = MaterialTheme.typography.labelLarge)
                    Text("Laboratorio 13", style = MaterialTheme.typography.displaySmall)
                    Text("Animaciones en Jetpack Compose", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))
                    exercises.forEachIndexed { index, (title, subtitle) ->
                        Card(onClick = { screen = index }, modifier = Modifier.fillMaxWidth()) {
                            Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text((index + 1).toString().padStart(2, '0'), color = Mint)
                                Spacer(Modifier.width(16.dp))
                                Column(Modifier.weight(1f)) {
                                    Text(title, style = MaterialTheme.typography.titleMedium)
                                    Text(subtitle, style = MaterialTheme.typography.bodySmall)
                                }
                                Text("›", color = Mint)
                            }
                        }
                    }
                } else {
                    TextButton(onClick = { screen = -1 }) { Text("‹  Ejercicios") }
                    Text(exercises[screen].first, style = MaterialTheme.typography.headlineLarge)
                    Text(exercises[screen].second, color = Mint, style = MaterialTheme.typography.labelLarge)
                    Spacer(Modifier.height(4.dp))
                    when (screen) {
                        0 -> VisibilityExercise()
                        1 -> ColorExercise()
                        2 -> MotionExercise()
                        3 -> ContentExercise()
                        4 -> RescueGame()
                        5 -> AnimationCodelab()
                    }
                }
            }
        }
    }
}
