package com.djostanisic.githubusers.presentation.user_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djostanisic.githubusers.common.Resource
import com.djostanisic.githubusers.domain.use_case.get_users.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _state = mutableStateOf(UserListState())
    val state: State<UserListState> = _state

    init {
        getUsers()
    }

    fun getUsers() {
        getUsersUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = UserListState(users = result.data ?: emptyList())

                }
                is Resource.Loading -> {
                    _state.value = UserListState(isLoading = true)

                }
                is Resource.Error -> {
                    _state.value = UserListState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}