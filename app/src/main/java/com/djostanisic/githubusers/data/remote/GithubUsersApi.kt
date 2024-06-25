package com.djostanisic.githubusers.data.remote

import com.djostanisic.githubusers.data.remote.dto.GithubUserDetailsDto
import com.djostanisic.githubusers.data.remote.dto.GithubUserDto
import com.djostanisic.githubusers.data.remote.dto.OrganizationDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUsersApi {
    @GET("users")
    suspend fun getUsers(
        @Query("since") page: Int,
        @Query("per_page") pageSize: Int
    ): List<GithubUserDto>

    @GET ("/users/{userName}")
    suspend fun getUserDetails(@Path("userName") userName: String): GithubUserDetailsDto

    @GET ("/users/{userName}/orgs")
    suspend fun getOrganizations(
        @Path("userName") userName: String): List<OrganizationDto?>

}