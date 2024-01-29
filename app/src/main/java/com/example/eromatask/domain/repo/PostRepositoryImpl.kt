package com.example.eromatask.domain.repo

import com.example.eromatask.commons.networks.PostsApi
import com.example.eromatask.data.remote.toPost
import com.example.eromatask.domain.model.Post
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val postsApi: PostsApi): PostRepository {
    override suspend fun loadPosts(): FetchResult<List<Post>> {
        return try {
            FetchResult.Success(postsApi.getPosts().map { it.toPost() }.toList())
        }catch (e:Exception){
            FetchResult.Failed(message = e.message!!)
        }
    }

    override suspend fun loadPostDetails(id:Int): FetchResult<Post> {
        return try {
            FetchResult.Success(postsApi.getPost(id).toPost())
        }catch (e:Exception){
            FetchResult.Failed(message = e.message!!)
        }
    }
}