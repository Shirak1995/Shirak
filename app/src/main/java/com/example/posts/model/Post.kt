package com.example.posts.model

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int,
    val title: String,
    val userId: Int,
    val body: String
)
