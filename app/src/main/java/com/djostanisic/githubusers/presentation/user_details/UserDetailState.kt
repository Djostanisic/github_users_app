package com.djostanisic.githubusers.presentation.user_details

import com.djostanisic.githubusers.domain.model.GithubUserDetails

data class UserDetailState(
    val isLoading: Boolean = false,
    val userDetails: GithubUserDetails? = null,
    val error: String = ""
)
