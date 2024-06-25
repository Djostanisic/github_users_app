package com.djostanisic.githubusers.domain.repository

import com.djostanisic.githubusers.data.remote.dto.GithubUserDetailsDto
import com.djostanisic.githubusers.data.remote.dto.GithubUserDto
import com.djostanisic.githubusers.data.remote.dto.OrganizationDto

interface GithubUsersRepository {

    suspend fun getUsers(): List<GithubUserDto>

    suspend fun getUserDetails(userName: String): GithubUserDetailsDto

    suspend fun getOrganizations(userName: String): List<OrganizationDto?>

}