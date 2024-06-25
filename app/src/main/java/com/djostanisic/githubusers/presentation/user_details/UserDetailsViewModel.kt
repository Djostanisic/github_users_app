package com.djostanisic.githubusers.presentation.user_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djostanisic.githubusers.common.Constants
import com.djostanisic.githubusers.common.Resource
import com.djostanisic.githubusers.domain.use_case.get_user.GetOrganizationsUseCase
import com.djostanisic.githubusers.domain.use_case.get_user.GetUserDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getOrganizationsUseCase: GetOrganizationsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(UserDetailState())
    val state: State<UserDetailState> = _state

    private val _organizationState = mutableStateOf(OrganizationState())
    val organizationState: State<OrganizationState> = _organizationState

    init {
        savedStateHandle.get<String>(Constants.PARAM_USER_NAME)?.let { userName ->
            getUserDetails(userName)
        }
    }

    private fun getOrganizations(userName: String) {
        getOrganizationsUseCase(userName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _organizationState.value = OrganizationState(organizations = result.data)
                }

                is Resource.Loading -> {
                    _organizationState.value = OrganizationState(isLoading = true)
                }

                is Resource.Error -> {
                    _organizationState.value =
                        OrganizationState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUserDetails(userName: String) {
        getUserDetailsUseCase(userName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val userDetails = result.data
                    _state.value = UserDetailState(userDetails = userDetails)
                    userDetails?.organizationsUrl?.let { organizationUrl ->
                        getOrganizations(userName)
                    }
                }

                is Resource.Loading -> {
                    _state.value = UserDetailState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value =
                        UserDetailState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}