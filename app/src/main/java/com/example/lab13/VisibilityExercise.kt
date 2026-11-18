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
internal fun VisibilityExercise() {
    var visible by remember { mutableStateOf(true) }
    Text("Alterna el cuadro y observa cómo cambia su opacidad.")
    Box(Modifier.fillMaxWidth().height(228.dp).background(Panel, RoundedCornerShape(20.dp)), contentAlignment = Alignment.Center) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(tween(650)),
            exit = fadeOut(tween(500)),
        ) {
            Box(Modifier.size(124.dp).background(Mint, RoundedCornerShape(18.dp)), contentAlignment = Alignment.Center) {
                Text("Visible", color = Navy)
            }
        }
    }
    Button(onClick = { visible = !visible }) { Text(if (visible) "Ocultar cuadro" else "Mostrar cuadro") }
}
