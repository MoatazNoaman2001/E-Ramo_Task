package com.example.eromatask.data.remote

import com.example.eromatask.domain.model.Post

data class RemotePostItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)
fun RemotePostItem.toPost(): Post{
    return Post(id, title, body)
}