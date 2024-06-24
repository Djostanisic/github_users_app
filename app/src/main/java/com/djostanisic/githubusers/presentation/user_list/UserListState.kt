package com.djostanisic.githubusers.presentation.user_list

import com.djostanisic.githubusers.domain.model.GithubUser

data class UserListState(
    val isLoading: Boolean = false,
    val users: List<GithubUser> = emptyList(),
    val error: String = ""
)
