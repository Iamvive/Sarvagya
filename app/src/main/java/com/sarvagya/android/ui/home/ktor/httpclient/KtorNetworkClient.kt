package com.sarvagya.android.ui.home.ktor.httpclient

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class KtorNetworkClient
@Inject
constructor()
{
    fun createClient() = HttpClient(OkHttp) {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("Ktor", message)
                }
            }
            level = LogLevel.ALL
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    fun createRetrofitClient() = Retrofit.Builder()
        .baseUrl("http://3.109.222.122:8083")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}