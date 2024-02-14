package com.example.eromatask.domain.repo

import com.example.eromatask.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun loadPosts(): Flow<FetchResult<List<Post>>>
    suspend fun loadPostDetails(id:Int) : FetchResult<Post>
}


sealed class FetchResult<T>(val data: T? = null,val  Error: String? = null){
    class Success<T>(data: T) : FetchResult<T>(data)
    class Failed<T>(message:String) : FetchResult<T>(Error = message)

    class Loading<T>() : FetchResult<T>(data = null)
}