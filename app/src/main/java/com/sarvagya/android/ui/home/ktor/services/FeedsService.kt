package com.sarvagya.android.ui.home.ktor.services

import com.sarvagya.android.ui.home.ktor.data.FeedsRequest
import com.sarvagya.android.ui.home.ktor.data.FeedsResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

interface PostsService {

    suspend fun getPosts(): List<FeedsResponse>

    suspend fun createPost(PostsRequest: FeedsRequest): FeedsResponse?
}

class PostsServiceImpl(private val client: HttpClient) : PostsService {
    override suspend fun getPosts(): List<FeedsResponse> {
        return try {
            client.get { url(HttpRoutes.POSTS) }
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun createPost(PostsRequest: FeedsRequest): FeedsResponse? {
        return try {
            client.post<FeedsResponse> {
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = PostsRequest
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

}