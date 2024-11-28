package org.example.project

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

actual fun createRetrofit(): Any {
    return Retrofit.Builder()
        .baseUrl("http://127.0.0.1:8000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KotlinProject",
    ) {
        AnimatedScreenTransition()
    }
}