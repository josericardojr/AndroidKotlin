package com.josericardo_jr.basicktor

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Post(val userId: Int, val id: Int, val title: String, val body: String)

object KtorClient {
    private val client by lazy {
        HttpClient(){
            install(ContentNegotiation){
                json(Json {
                    prettyPrint = true
                })
            }
        }
    }

    suspend fun getPost(id: Int): Post {
        val response : Post = client.get("https://jsonplaceholder.typicode.com/posts/$id").body()
        return response
    }
}