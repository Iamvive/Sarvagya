package com.sarvagya.android.root.ktor.httpclient

import android.content.Context
import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import com.chuckerteam.chucker.api.ChuckerInterceptor
import javax.inject.Inject

class KtorNetworkClient
@Inject
constructor(private val context : Context)
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

        engine {
            addInterceptor(ChuckerInterceptor.Builder(context).build())
        }
    }


}