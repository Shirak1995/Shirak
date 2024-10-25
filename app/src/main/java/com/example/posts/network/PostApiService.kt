package com.example.posts.network

import com.example.posts.model.Post
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    interface PostApiService {
        @GET("/posts")
        suspend fun getPosts(): List<Post>
    }

    object PostApi {
        val retrofitService: PostApiService by lazy {
            retrofit.create(PostApiService::class.java)
        }
}
