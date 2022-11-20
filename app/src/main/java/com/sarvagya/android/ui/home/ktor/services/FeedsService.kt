package com.sarvagya.android.ui.home.ktor.services

import com.sarvagya.android.ui.home.ktor.data.PostResponse
import com.sarvagya.android.ui.home.ktor.httpclient.KtorNetworkClient
import io.ktor.client.request.*

interface PostsService {
    suspend fun getPosts(): List<PostResponse>
}

class PostsServiceImpl : PostsService {
    override suspend fun getPosts(): List<PostResponse> {
        return try {
            KtorNetworkClient.createClient().get { url(HttpRoutes.POSTS) }
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }
}