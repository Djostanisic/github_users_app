package com.djostanisic.githubusers.data.remote.dto

import com.djostanisic.githubusers.domain.model.GithubUserDetails


data class GithubUserDetailsDto(
    val login: String,
    val id: Int,
    val nodeId: String,
    val avatarUrl: String,
    val gravatarId: String,
    val url: String,
    val htmlUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val gistsUrl: String,
    val starredUrl: String,
    val subscriptionsUrl: String,
    val organizationsUrl: String,
    val reposUrl: String,
    val eventsUrl: String,
    val receivedEventsUrl: String,
    val type: String,
    val siteAdmin: Boolean,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val hireable: Boolean?,
    val bio: String?,
    val twitterUsername: String?,
    val publicRepos: Int,
    val publicGists: Int,
    val followers: Int,
    val following: Int,
    val createdAt: String,
    val updatedAt: String
)

fun GithubUserDetailsDto.toGithubUserDetails(): GithubUserDetails {
    return GithubUserDetails(
        avatarUrl = avatarUrl,
        email = email,
        name = name,
        organizationsUrl = organizationsUrl,
        followersUrl = followersUrl,
        followingUrl = followingUrl,
        createdAt = createdAt
    )
}