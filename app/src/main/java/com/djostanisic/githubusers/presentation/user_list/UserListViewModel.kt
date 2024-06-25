package com.djostanisic.githubusers.presentation.user_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djostanisic.githubusers.common.Resource
import com.djostanisic.githubusers.domain.model.GithubUser
import com.djostanisic.githubusers.domain.use_case.get_users.GetUsersUseCase
import com.djostanisic.githubusers.presentation.user_list.paginator.DefaultPaginator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    var state by mutableStateOf(ScreenState())
    private var itemsPerPage = 20

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            try {
                val result = getUsersAsResult( if (state.users.isNotEmpty()){
                    state.page + state.users.last().id} else state.page, itemsPerPage)
                result
            } catch (e: Exception) {
                Result.failure(e)
            }
        },
        getNextKey = {
            if (state.users.isNotEmpty()){
                state.users.last().id} else state.page
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)},
        onSuccess = { users, newKey ->
            state = state.copy(
                users = state.users + users,
                page = newKey,
                endReached = users.isEmpty()
            )
        }
    )

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun reset() {
        viewModelScope.launch {
            state.users = emptyList()
            state.page = 0
            paginator.reset()
            loadNextItems()
        }
    }


    init {
        loadNextItems()
    }

    private suspend fun getUsersAsResult(page: Int, perPage: Int): Result<List<GithubUser>> {
        return try {
            val response = getUsersUseCase(page, perPage).toList()
            when (val resource = response.lastOrNull()) {
                is Resource.Success -> Result.success(resource.data ?: emptyList())
                is Resource.Error -> Result.failure(Throwable(resource.message))
                else -> Result.failure(Throwable("Unexpected state"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

data class ScreenState(
    val isLoading: Boolean = false,
    var users: List<GithubUser> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    var page: Int = 0
)