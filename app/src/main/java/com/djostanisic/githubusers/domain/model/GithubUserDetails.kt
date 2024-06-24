package com.djostanisic.githubusers.domain.model

data class GithubUserDetails(
    val avatarUrl: String,
    val email: String?,
    val name: String?,
    val organizationsUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val createdAt: String,
)
