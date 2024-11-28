package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import io.ktor.client.*
import io.ktor.client.engine.ios.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

actual fun createRetrofit(): Any {
    // Ktor Client 설정
    val client = HttpClient(Ios) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    return client
}

fun MainViewController() = ComposeUIViewController { AnimatedScreenTransition() }