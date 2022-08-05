package com.example.nikeapp.network.models

data class Comment(
    val author: Author,
    val content: String,
    val date: String,
    val id: Int,
    val title: String
)


data class Author(
    val email: String
)