package ru.netology.nmedia

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likes: Int,
    val reposts: Int,
    val views: Int,
    var likedByMe: Boolean = false
)