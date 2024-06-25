package com.djostanisic.githubusers.data.remote.dto

import com.djostanisic.githubusers.domain.model.GithubUserDetails


data class GithubUserDetailsDto(
    val login: String,
    val id: Int,
    val nodeId: String,
    val avatar_url: String,
    val gravatarId: String,
    val url: String,
    val htmlUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val gistsUrl: String,
    val starredUrl: String,
    val subscriptionsUrl: String,
    val organizations_url: String?,
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
    val created_at: String,
    val updatedAt: String
)

fun GithubUserDetailsDto.toGithubUserDetails(): GithubUserDetails {
    val formattedDate = created_at.split("T")[0].let { date ->
        val parts = date.split("-")
        "${parts[2]}.${parts[1]}.${parts[0]}"
    }

    return GithubUserDetails(
        avatarUrl = avatar_url,
        email = email,
        name = name,
        organizationsUrl = organizations_url,
        followers = followers,
        following = following,
        createdAt = formattedDate
    )
}