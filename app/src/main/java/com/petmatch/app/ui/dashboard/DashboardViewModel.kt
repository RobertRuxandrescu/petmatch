package com.petmatch.app.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petmatch.app.data.repository.PetPostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val petPostRepository: PetPostRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state.asStateFlow()

    init {
        loadPosts()
    }

    fun onIntent(intent: DashboardIntent) {
        when (intent) {
            is DashboardIntent.LoadPosts -> loadPosts()
            is DashboardIntent.RefreshPosts -> refreshPosts()
            is DashboardIntent.PostClicked -> handlePostClick(intent.post)
            is DashboardIntent.AddPostClicked -> handleAddPostClick()
            is DashboardIntent.ErrorDismissed -> dismissError()
        }
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            
            val result = petPostRepository.getPetPosts()
            
            when {
                result.isSuccess -> {
                    val posts = result.getOrNull() ?: emptyList()
                    _state.value = _state.value.copy(
                        isLoading = false,
                        posts = posts,
                        isEmpty = posts.isEmpty()
                    )
                }
                result.isFailure -> {
                    val errorMessage = result.exceptionOrNull()?.message ?: "Failed to load posts"
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = errorMessage
                    )
                }
            }
        }
    }

    private fun refreshPosts() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            
            val result = petPostRepository.getPetPosts()
            
            when {
                result.isSuccess -> {
                    val posts = result.getOrNull() ?: emptyList()
                    _state.value = _state.value.copy(
                        isLoading = false,
                        posts = posts,
                        isEmpty = posts.isEmpty()
                    )
                }
                result.isFailure -> {
                    val errorMessage = result.exceptionOrNull()?.message ?: "Failed to refresh posts"
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = errorMessage
                    )
                }
            }
        }
    }

    private fun handlePostClick(post: com.petmatch.app.data.model.PetPost) {
        _state.value = _state.value.copy(selectedPost = post, shouldNavigateToPostDetail = true)
    }

    private fun handleAddPostClick() {
        _state.value = _state.value.copy(shouldNavigateToAddPost = true)
    }

    private fun dismissError() {
        _state.value = _state.value.copy(error = null)
    }
}

data class DashboardState(
    val posts: List<com.petmatch.app.data.model.PetPost> = emptyList(),
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val error: String? = null,
    val selectedPost: com.petmatch.app.data.model.PetPost? = null,
    val shouldNavigateToPostDetail: Boolean = false,
    val shouldNavigateToAddPost: Boolean = false
)

sealed class DashboardIntent {
    object LoadPosts : DashboardIntent()
    object RefreshPosts : DashboardIntent()
    data class PostClicked(val post: com.petmatch.app.data.model.PetPost) : DashboardIntent()
    object AddPostClicked : DashboardIntent()
    object ErrorDismissed : DashboardIntent()
}
