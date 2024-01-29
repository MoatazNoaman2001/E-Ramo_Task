package com.example.eromatask.presetation.PostDeatils

import androidx.lifecycle.ViewModel
import com.example.eromatask.domain.model.Post
import com.example.eromatask.domain.repo.PostRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(val postRepositoryImpl: PostRepositoryImpl) :
    ViewModel() {
    suspend fun getPostDetails(id: Int) = postRepositoryImpl.loadPostDetails(id)
}