package com.example.eromatask.presetation.PostsList

import androidx.lifecycle.ViewModel
import com.example.eromatask.domain.model.Post
import com.example.eromatask.domain.repo.FetchResult
import com.example.eromatask.domain.repo.PostRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PostListViewModelImpl @Inject constructor(private val postRepositoryImpl: PostRepositoryImpl): PostListViewModel, ViewModel() {
    suspend fun postList() : FetchResult<List<Post>> = postRepositoryImpl.loadPosts()
}