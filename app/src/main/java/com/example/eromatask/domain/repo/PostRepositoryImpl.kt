package com.example.eromatask.domain.repo

import com.example.eromatask.commons.networks.PostsApi
import com.example.eromatask.data.remote.toPost
import com.example.eromatask.domain.model.Post
import com.example.eromatask.domain.model.PostDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val postsApi: PostsApi) : PostRepository {
    override fun loadPosts() = flow<FetchResult<List<Post>>> {
        emit(FetchResult.Loading())
        emit(FetchResult.Success(postsApi.getPosts().map { it.toPost() }.toList()))
    }
        .catch { emit(FetchResult.Failed(it.message!!))}
        .flowOn(Dispatchers.IO)

    override suspend fun loadPostDetails(id: Int): FetchResult<Post> {
        return try {
            FetchResult.Success(postsApi.getPost(id).toPost())
        } catch (e: Exception) {
            FetchResult.Failed(message = e.message!!)
        }
    }

    suspend fun loadPostDetailsGG(id: Int): FetchResult<PostDetails> {
        return try {
            val remotePost = postsApi.getPost(id)
            FetchResult.Success(
                PostDetails(
                    remotePost.toPost(),
                    user = postsApi.getUserFromPost(remotePost.userId),
                    comments = postsApi.getCommentsFromPost(id)
                )
            )
        } catch (e: Exception) {
            FetchResult.Failed(message = e.message!!)
        }
    }
}