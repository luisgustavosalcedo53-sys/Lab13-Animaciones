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
@Composable
internal fun ColorExercise() {
    var green by remember { mutableStateOf(false) }
    var useSpring by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (green) Color(0xFF188453) else Color(0xFF2466C4),
        animationSpec = if (useSpring) spring(stiffness = Spring.StiffnessLow) else tween(900),
        label = "Fondo del cuadro",
    )
    Text("Cambia entre azul y verde con una duración fija o con un resorte.")
    Box(Modifier.fillMaxWidth().height(228.dp).background(color, RoundedCornerShape(20.dp)), contentAlignment = Alignment.Center) {
        Text(if (green) "Verde" else "Azul", color = Color.White, style = MaterialTheme.typography.titleLarge)
    }
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(onClick = { green = !green }) { Text("Cambiar color") }
        FilterChip(selected = useSpring, onClick = { useSpring = !useSpring }, label = {
            Text(if (useSpring) "Spring" else "Tween")
        })
    }
}
