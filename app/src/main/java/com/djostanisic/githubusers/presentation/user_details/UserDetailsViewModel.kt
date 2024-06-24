package com.djostanisic.githubusers.presentation.user_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djostanisic.githubusers.common.Constants
import com.djostanisic.githubusers.common.Resource
import com.djostanisic.githubusers.domain.use_case.get_user.GetUserDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(UserDetailState())
    val state: State<UserDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_USER_NAME)?.let { userName ->
            getUserDetails(userName)
        }
    }

    private fun getUserDetails(userName: String) {
        getUserDetailsUseCase(userName).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = UserDetailState(userDetails = result.data)

                }
                is Resource.Loading -> {
                    _state.value = UserDetailState(isLoading = true)

                }
                is Resource.Error -> {
                    _state.value = UserDetailState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}