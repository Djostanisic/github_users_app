package com.djostanisic.githubusers.data.remote.dto

import com.djostanisic.githubusers.domain.model.GithubUser

data class GithubUserDto(
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
    val siteAdmin: Boolean
)

fun GithubUserDto.toGithubUser(): GithubUser {
    return GithubUser(
        login = login,
        id = id,
        avatarUrl = avatarUrl
    )
}
