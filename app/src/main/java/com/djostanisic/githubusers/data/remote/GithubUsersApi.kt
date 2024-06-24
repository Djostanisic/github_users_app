package com.djostanisic.githubusers.data.remote

import com.djostanisic.githubusers.data.remote.dto.GithubUserDetailsDto
import com.djostanisic.githubusers.data.remote.dto.GithubUserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubUsersApi {
    @GET ("/users")
    suspend fun getUsers(): List<GithubUserDto>

    @GET ("/users/{userName}")
    suspend fun getUserDetails(@Path("userName") userName: String): GithubUserDetailsDto
}