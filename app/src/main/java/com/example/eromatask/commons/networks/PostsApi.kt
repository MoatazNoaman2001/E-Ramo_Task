package com.example.eromatask.commons.networks

import com.example.eromatask.data.remote.RemotePostItem
import com.example.eromatask.data.remote.RemotePosts
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApi {


    @GET("posts")
    suspend fun getPosts(): RemotePosts

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id:Int):  RemotePostItem
}