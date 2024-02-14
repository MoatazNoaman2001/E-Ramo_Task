package com.example.eromatask.domain.model

import com.example.eromatask.data.remote.RemoteComment
import com.example.eromatask.data.remote.RemoteUser

data class PostDetails(
    val post:Post,
    val user:RemoteUser,
    val comments:List<RemoteComment>
) {
}