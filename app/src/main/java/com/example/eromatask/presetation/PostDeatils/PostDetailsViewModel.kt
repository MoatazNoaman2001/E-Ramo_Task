package com.example.eromatask.presetation.PostDeatils

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.eromatask.domain.model.Post
import com.example.eromatask.domain.repo.FetchResult
import com.example.eromatask.domain.repo.PostRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(val postRepositoryImpl: PostRepositoryImpl) :
    ViewModel() {
    suspend fun getPostDetails(id: Int) = postRepositoryImpl.loadPostDetails(id)

    suspend fun getPostDetailsGG(id:Int) = postRepositoryImpl.loadPostDetailsGG(id)
}