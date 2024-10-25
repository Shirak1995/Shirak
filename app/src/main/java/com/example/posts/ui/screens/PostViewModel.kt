package com.example.posts.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posts.model.Post
import com.example.posts.network.PostApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface PostUiState {
    data class Success(val posts: List<Post>) : PostUiState
    object Error : PostUiState
    object Loading : PostUiState
}

class PostViewModel : ViewModel() {
    var postUiState: PostUiState by mutableStateOf(PostUiState.Loading)
        private set

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            postUiState = PostUiState.Loading
            postUiState = try {
                val listResult = PostApi.retrofitService.getPosts()
                PostUiState.Success(
                    listResult
                )
            } catch (e: IOException) {
                PostUiState.Error
            } catch (e: HttpException) {
                PostUiState.Error
            }
        }
    }
}
