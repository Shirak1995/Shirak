package com.example.books.network

import retrofit2.http.GET

interface BooksApiService {
    @GET ("volumes?q=chess")
    suspend fun getPhotos(): Response
}
