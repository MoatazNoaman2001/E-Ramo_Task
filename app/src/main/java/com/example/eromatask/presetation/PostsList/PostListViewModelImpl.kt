package com.example.eromatask.presetation.PostsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.eromatask.domain.model.Post
import com.example.eromatask.domain.repo.FetchResult
import com.example.eromatask.domain.repo.PostRepositoryImpl
import com.example.eromatask.domain.useCases.PostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModelImpl @Inject constructor(private val postsUseCase: PostsUseCase) :
    PostListViewModel, ViewModel() {

    val uiPosts: LiveData<FetchResult<List<Post>>> = postsUseCase.resultFlow.asLiveData()

    fun launchPosts(){
        viewModelScope.launch {
            postsUseCase.launch()
        }
    }
}