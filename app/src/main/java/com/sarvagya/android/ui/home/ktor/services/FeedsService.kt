package com.sarvagya.android.ui.home.ktor.services

import com.sarvagya.android.ui.home.ktor.data.PostResponse
import com.sarvagya.android.ui.home.ktor.httpclient.KtorNetworkClient
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

interface PostsService {
    suspend fun getPosts(): List<PostResponse>
}

class PostsServiceImpl
@Inject
constructor(
    private val client: HttpClient
) : PostsService {
    override suspend fun getPosts(): List<PostResponse> {
        return try {
            client.get {
                url(HttpRoutes.POSTS)
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }
}