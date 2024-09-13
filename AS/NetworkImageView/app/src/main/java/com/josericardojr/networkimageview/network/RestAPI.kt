package com.josericardojr.networkimageview.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.josericardojr.networkimageview.datamodel.PixabayResult
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType


private val BASE_URL = "https://pixabay.com"

private val retrofit = Retrofit.Builder()
    //.addConverterFactory(ScalarsConverterFactory.create()) // Adicionando o conversor (string, nesse caso)
     .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())) // Adicionando serialização JSON
     .baseUrl(BASE_URL)
     .build()

/*
Interface usada para acessar o servidor através do Retrofit
 */
interface PixabayApiService {
    @GET("/api/")
    suspend fun getPhotos(
        @Query("key") apiKey: String,
        @Query("q") query: String = "flowers",
        @Query("image_type") imageType : String = "photo"
    ) : PixabayResult
}

/*
 Objeto Singleton utilizado para armazenar apenas uma instância da classe PixabayApiService
 Estamos realizando uma inicialização tardia
 */
object PixabayApi {
    val retrofitService : PixabayApiService by lazy {
        retrofit.create(PixabayApiService::class.java)
    }
}