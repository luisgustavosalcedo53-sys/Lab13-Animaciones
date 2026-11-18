package com.example.lab13

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

private enum class Mission { Briefing, Flying, Won, Lost }

@Composable
internal fun RescueGame() {
    var mission by remember { mutableStateOf(Mission.Briefing) }
    var lane by remember { mutableIntStateOf(1) }
    var round by remember { mutableIntStateOf(0) }
    val targets = listOf(0, 2, 1)
    val target = targets[round.coerceAtMost(targets.lastIndex)]
    val active = mission == Mission.Flying
    val habitat by animateColorAsState(
        when (mission) {
            Mission.Won -> Color(0xFF16483F)
            Mission.Lost -> Color(0xFF55253F)
            else -> Panel
        },
        label = "Color del sector",
    )
    val shipSize by animateDpAsState(if (active) 48.dp else 62.dp, label = "Tamaño de la nave")
    Text("Alinea la nave con la cápsula y recógela. Rescata las tres para completar la misión.")
    BoxWithConstraints(Modifier.fillMaxWidth().height(235.dp).background(habitat, RoundedCornerShape(22.dp))) {
        val laneWidth = maxWidth / 3
        val x by animateDpAsState(laneWidth * lane + (laneWidth - shipSize) / 2, tween(410), label = "Carril de la nave")
        Row(Modifier.fillMaxWidth().padding(top = 28.dp)) {
            repeat(3) { index ->
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Sector ${index + 1}", style = MaterialTheme.typography.labelSmall)
                    Spacer(Modifier.height(13.dp))
                    AnimatedVisibility(active && index == target, enter = fadeIn(tween(500)), exit = fadeOut()) {
                        Box(Modifier.size(34.dp).background(Color(0xFFFFCC84), CircleShape), contentAlignment = Alignment.Center) {
                            Text("✦", color = Navy)
                        }
                    }
                }
            }
        }
        Box(Modifier.offset { IntOffset(x.roundToPx(), 139.dp.roundToPx()) }.size(shipSize).background(Mint, CircleShape), contentAlignment = Alignment.Center) {
            Text("▲", color = Navy, style = MaterialTheme.typography.headlineSmall)
        }
    }
    AnimatedContent(mission, transitionSpec = { fadeIn(tween(420)) togetherWith fadeOut(tween(180)) },
        label = "Resultado de la misión") { stage ->
        Text(when (stage) {
            Mission.Briefing -> "En espera de lanzamiento"
            Mission.Flying -> "Cápsula en sector ${target + 1} · Rescatadas ${round}/3"
            Mission.Won -> "¡Misión cumplida! · Rescatadas 3/3"
            Mission.Lost -> "Sector incorrecto · Rescatadas ${round}/3"
        }, style = MaterialTheme.typography.titleMedium)
    }
    if (active) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = { lane-- }, enabled = lane > 0, contentPadding = PaddingValues(horizontal = 10.dp)) { Text("Izquierda") }
            Button(onClick = {
                if (lane != target) {
                    mission = Mission.Lost
                } else {
                    round++
                    if (round == targets.size) mission = Mission.Won
                }
            }, contentPadding = PaddingValues(horizontal = 10.dp)) { Text("Rescatar") }
            OutlinedButton(onClick = { lane++ }, enabled = lane < 2, contentPadding = PaddingValues(horizontal = 10.dp)) { Text("Derecha") }
        }
    } else {
        Button(onClick = {
            lane = 1
            round = 0
            mission = Mission.Flying
        }) { Text(if (mission == Mission.Briefing) "Iniciar misión" else "Nueva misión") }
    }
}
