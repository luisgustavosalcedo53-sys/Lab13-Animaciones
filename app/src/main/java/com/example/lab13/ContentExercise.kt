package com.example.lab13

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
private enum class DemoState(val title: String, val description: String) {
    Loading("Cargando", "Preparando la información…"),
    Ready("Contenido", "Los datos están disponibles."),
    Error("Error", "La carga falló. Selecciona Cargando para reintentar."),
}

@Composable
internal fun ContentExercise() {
    var status by remember { mutableStateOf(DemoState.Loading) }
    Text("Selecciona un estado para ver la transición.")
    Box(Modifier.fillMaxWidth().height(228.dp).background(Panel, RoundedCornerShape(20.dp)), contentAlignment = Alignment.Center) {
        AnimatedContent(status, transitionSpec = {
            fadeIn(tween(460, delayMillis = 160)) togetherWith fadeOut(tween(220))
        }, label = "Estado de la pantalla") { state ->
            Column(Modifier.padding(22.dp), horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)) {
                if (state == DemoState.Loading) CircularProgressIndicator(Modifier.size(38.dp))
                Text(state.title, color = if (state == DemoState.Error) MaterialTheme.colorScheme.error else Mint,
                    style = MaterialTheme.typography.headlineSmall)
                Text(state.description)
            }
        }
    }
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        DemoState.entries.forEach { state ->
            FilterChip(selected = status == state, onClick = { status = state }, label = { Text(state.title) })
        }
    }
}
