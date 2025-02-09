package com.example.books.data

import com.example.books.network.BooksApiService
import com.example.books.network.Response

interface BooksRepository {
    suspend fun getBooks(): Response
}

class NetworkBooksRepository(private val booksApiService: BooksApiService) : BooksRepository {
    override suspend fun getBooks(): Response = booksApiService.getPhotos()
}