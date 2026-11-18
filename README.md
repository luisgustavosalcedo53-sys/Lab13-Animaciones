# Laboratorio 13 · Animaciones en Jetpack Compose

Proyecto de Kotlin con una pantalla para cada ejercicio de Programación en Móviles.

| Pantalla | Demostración |
| --- | --- |
| Visibilidad | Muestra y oculta un cuadro con `AnimatedVisibility`, `fadeIn` y `fadeOut`. |
| Cambio de color | Alterna entre azul y verde con `animateColorAsState` y permite elegir `tween` o `spring`. |
| Tamaño y posición | Anima tamaño y coordenadas con `animateDpAsState` y permite comparar el orden de `offset` y `background`. |
| Cambio de contenido | Alterna entre Cargando, Contenido y Error con `AnimatedContent` y tiempos distintos para entrada y salida. |
| Órbita de rescate | Mueve la nave entre tres sectores, recoge tres cápsulas y muestra el resultado de la misión con animaciones combinadas. |
| Codelab de animaciones | Aplica las seis técnicas del codelab oficial en una agenda con pestañas, acción animada, tarjeta expandible, pulso y descarte por gesto. |

## Ejecutar

Abre la carpeta en Android Studio con el SDK de Android 37, sincroniza Gradle y ejecuta la configuración `app` en un emulador o dispositivo con API 24 o superior. Desde PowerShell también puedes compilar con `.\gradlew.bat :app:assembleDebug`.

Para ganar en **Órbita de rescate**, inicia la misión y alinea la nave con la cápsula antes de pulsar **Rescatar**. El sector objetivo aparece debajo del tablero. Selecciona **Nueva misión** para volver a jugar.

En el **Codelab de animaciones**, cambia de pestaña, alterna la acción de edición, expande la tarjeta y desliza el recordatorio a la izquierda. La pulsación del indicador de sincronización se repite mientras la pantalla está abierta.

[Referencia del codelab de Android Developers](https://developer.android.com/codelabs/jetpack-compose-animation?hl=es-419)
