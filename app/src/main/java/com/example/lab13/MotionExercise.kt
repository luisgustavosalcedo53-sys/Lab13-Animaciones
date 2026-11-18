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
internal fun MotionExercise() {
    var moved by remember { mutableStateOf(false) }
    var backgroundMoves by remember { mutableStateOf(true) }
    val size by animateDpAsState(if (moved) 112.dp else 62.dp, tween(850), label = "Tamaño")
    val y by animateDpAsState(if (moved) 84.dp else 18.dp, tween(850), label = "Desplazamiento Y")
    BoxWithConstraints(Modifier.fillMaxWidth().height(228.dp).clip(RoundedCornerShape(20.dp)).background(Panel)) {
        val x by animateDpAsState(if (moved) (maxWidth - 126.dp).coerceAtLeast(14.dp) else 14.dp, tween(850), label = "Desplazamiento X")
        val shape = RoundedCornerShape(18.dp)
        val base = Modifier.size(size)
        val order = if (backgroundMoves) base.offset(x, y).background(Mint, shape)
            else base.background(Mint, shape).offset(x, y)
        Box(order, contentAlignment = Alignment.Center) { Text("✦", color = if (backgroundMoves) Navy else Mint) }
    }
    Button(onClick = { moved = !moved }) { Text(if (moved) "Volver al inicio" else "Mover y ampliar") }
    FilterChip(selected = backgroundMoves, onClick = { backgroundMoves = !backgroundMoves }, label = {
        Text(if (backgroundMoves) "offset → background" else "background → offset")
    })
    Text("Cambia el orden para observar si el fondo acompaña al contenido.")
}
