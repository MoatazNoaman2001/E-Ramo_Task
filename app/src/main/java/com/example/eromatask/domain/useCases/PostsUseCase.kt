package com.example.eromatask.domain.useCases

import com.example.eromatask.domain.model.Post
import com.example.eromatask.domain.repo.FetchResult
import com.example.eromatask.domain.repo.PostRepository
import com.example.eromatask.domain.repo.PostRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsUseCase @Inject constructor(private val repo:PostRepositoryImpl) : FlowUseCase<FetchResult<List<Post>>>() {
    override fun performAction(): Flow<FetchResult<List<Post>>> {
        return repo.loadPosts()
    }

}