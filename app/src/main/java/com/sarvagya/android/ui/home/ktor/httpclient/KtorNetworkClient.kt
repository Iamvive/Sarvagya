package com.sarvagya.android.ui.home.ktor.httpclient

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Inject

class KtorNetworkClient
@Inject
constructor()
{
    fun createClient() = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
}