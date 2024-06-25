package com.djostanisic.githubusers.data.remote.dto

import com.djostanisic.githubusers.domain.model.Organization

data class OrganizationDto(
    val login: String,
    val id: Int,
    val node_id: String,
    val url: String,
    val repos_url: String,
    val events_url: String,
    val hooks_url: String,
    val issues_url: String,
    val members_url: String,
    val public_members_url: String,
    val avatar_url: String,
    val description: String?
)

fun OrganizationDto.toOrganization(): Organization {
    return Organization(
        avatarUrl = avatar_url,
        organizationName = login,
        description = description ?: ""
    )
}
