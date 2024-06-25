package com.djostanisic.githubusers.domain.model

data class GithubUserDetails(
    val avatarUrl: String,
    val email: String?,
    val name: String?,
    val organizationsUrl: String?,
    val followers: Int,
    val following: Int,
    val createdAt: String,
)
