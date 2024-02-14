package com.example.eromatask.commons.networks

import com.example.eromatask.data.remote.RemoteComment
import com.example.eromatask.data.remote.RemotePostItem
import com.example.eromatask.data.remote.RemotePosts
import com.example.eromatask.data.remote.RemoteUser
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostsApi {


    @GET("posts")
    suspend fun getPosts(): RemotePosts

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id:Int):  RemotePostItem

    @GET("users/{id}")
    suspend fun getUserFromPost(@Path("id") id: Int): RemoteUser

    @GET("comments")
    suspend fun getCommentsFromPost(@Query("postId") id:Int): List<RemoteComment>
}