package com.example.books.network

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val kind: String,
    val items: List<Book>,
    val totalItems: Int
)

@Serializable
data class Book(
    val id: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val title: String,
    val imageLinks: ImageLinks? = null
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String?
)
