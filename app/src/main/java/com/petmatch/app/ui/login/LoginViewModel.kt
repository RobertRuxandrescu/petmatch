package com.petmatch.app.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.petmatch.app.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    init {
        checkLoginStatus()
    }

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.GoogleSignInClicked -> handleGoogleSignInClick()
            is LoginIntent.CheckLoginStatus -> checkLoginStatus()
            is LoginIntent.ErrorDismissed -> dismissError()
        }
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            if (authRepository.isUserLoggedIn()) {
                // For now, since we're using temporary Firebase bypass,
                // we'll just set a default user state
                val user = com.petmatch.app.data.model.User(
                    id = "temp_user_id",
                    email = "temp@example.com",
                    displayName = "Temp User",
                    photoUrl = ""
                )
                _state.value = _state.value.copy(user = user, shouldNavigateToDashboard = true)
            }
        }
    }

    private fun handleGoogleSignInClick() {
        _state.value = _state.value.copy(shouldLaunchGoogleSignIn = true)
    }

    fun handleGoogleSignInResult(account: GoogleSignInAccount) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            
            val result = authRepository.signInWithGoogle(account)
            
            when {
                result.isSuccess -> {
                    val user = result.getOrNull()
                    _state.value = _state.value.copy(
                        isLoading = false,
                        user = user,
                        shouldNavigateToDashboard = true
                    )
                }
                result.isFailure -> {
                    val errorMessage = result.exceptionOrNull()?.message ?: "Login failed"
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = errorMessage
                    )
                }
            }
        }
    }

    private fun dismissError() {
        _state.value = _state.value.copy(error = null)
    }
}

data class LoginState(
    val isLoading: Boolean = false,
    val user: com.petmatch.app.data.model.User? = null,
    val error: String? = null,
    val shouldNavigateToDashboard: Boolean = false,
    val shouldLaunchGoogleSignIn: Boolean = false
)

sealed class LoginIntent {
    object GoogleSignInClicked : LoginIntent()
    object CheckLoginStatus : LoginIntent()
    object ErrorDismissed : LoginIntent()
}
