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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
internal fun AnimationCodelab() {
    var schedule by remember { mutableStateOf(false) }
    var showEdit by remember { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(false) }
    var showReminder by remember { mutableStateOf(true) }
    val panelColor by animateColorAsState(
        if (schedule) Color(0xFF284353) else Panel,
        tween(500),
        label = "Fondo de la pestaña",
    )
    val tabTransition = updateTransition(schedule, label = "Selección de pestaña")
    val indicatorOffset by tabTransition.animateDp(label = "Posición del indicador") {
        if (it) 102.dp else 0.dp
    }
    val indicatorWidth by tabTransition.animateDp(label = "Ancho del indicador") {
        if (it) 90.dp else 70.dp
    }
    val pulse = rememberInfiniteTransition(label = "Pulso de sincronización")
    val pulseAlpha by pulse.animateFloat(
        initialValue = 0.45f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(900), RepeatMode.Reverse),
        label = "Opacidad del pulso",
    )
    Text("Agenda de la misión", style = MaterialTheme.typography.titleLarge)
    Column(Modifier.fillMaxWidth().background(panelColor, RoundedCornerShape(20.dp)).padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            TextButton(onClick = { schedule = false }, modifier = Modifier.width(90.dp)) { Text("Inicio") }
            TextButton(onClick = { schedule = true }, modifier = Modifier.width(90.dp)) { Text("Agenda") }
        }
        Box(Modifier.offset(x = indicatorOffset).width(indicatorWidth).height(3.dp).background(Mint, RoundedCornerShape(50)))
        Spacer(Modifier.height(18.dp))
        Text(if (schedule) "Próximas actividades" else "Resumen de hoy", style = MaterialTheme.typography.titleMedium)
        Text("● Sincronizando", color = Mint, modifier = Modifier.graphicsLayer { alpha = pulseAlpha })
        Spacer(Modifier.height(12.dp))
        OutlinedButton(onClick = { showEdit = !showEdit }) { Text(if (showEdit) "Ocultar acción" else "Mostrar acción") }
        AnimatedVisibility(showEdit, enter = expandVertically() + fadeIn(), exit = shrinkVertically() + fadeOut()) {
            TextButton(onClick = { expanded = !expanded }) { Text("Editar vista") }
        }
    }
    Card(onClick = { expanded = !expanded }, modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.animateContentSize(tween(450)).padding(16.dp)) {
            Text("Detalles de la misión", style = MaterialTheme.typography.titleMedium)
            if (expanded) {
                Spacer(Modifier.height(8.dp))
                Text("Revisa la telemetría, confirma el enlace con la estación y prepara el siguiente lanzamiento.")
            }
            Spacer(Modifier.height(4.dp))
            Text(if (expanded) "Contraer" else "Expandir", color = Mint)
        }
    }
    if (showReminder) {
        key(showReminder) {
            val dismissState = rememberSwipeToDismissBoxState()
            SwipeToDismissBox(
                state = dismissState,
                enableDismissFromStartToEnd = false,
                onDismiss = { showReminder = false },
                backgroundContent = {
                    Box(
                        Modifier.fillMaxSize().background(Color(0xFF562C43), RoundedCornerShape(14.dp)).padding(end = 16.dp),
                        contentAlignment = Alignment.CenterEnd,
                    ) { Text("Descartar") }
                },
            ) {
                Surface(shape = RoundedCornerShape(14.dp), color = Panel, modifier = Modifier.fillMaxWidth()) {
                    Text("Recordatorio · Desliza a la izquierda", Modifier.padding(16.dp))
                }
            }
        }
    } else {
        OutlinedButton(onClick = { showReminder = true }) { Text("Restaurar recordatorio") }
    }
}
