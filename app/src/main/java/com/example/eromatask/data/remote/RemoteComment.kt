package com.example.eromatask.data.remote

data class RemoteComment(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)