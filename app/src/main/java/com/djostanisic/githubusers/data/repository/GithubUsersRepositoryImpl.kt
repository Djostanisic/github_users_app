package com.djostanisic.githubusers.data.repository

import com.djostanisic.githubusers.data.remote.GithubUsersApi
import com.djostanisic.githubusers.data.remote.dto.GithubUserDetailsDto
import com.djostanisic.githubusers.data.remote.dto.GithubUserDto
import com.djostanisic.githubusers.domain.repository.GithubUsersRepository
import javax.inject.Inject

class GithubUsersRepositoryImpl @Inject constructor(
    private val api: GithubUsersApi
) : GithubUsersRepository {

    override suspend fun getUsers(): List<GithubUserDto> {
        return api.getUsers()
    }

    override suspend fun getUserDetails(userName: String): GithubUserDetailsDto {
        return api.getUserDetails(userName)
    }
}