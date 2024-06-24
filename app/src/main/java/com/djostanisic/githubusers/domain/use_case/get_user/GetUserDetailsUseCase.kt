package com.djostanisic.githubusers.domain.use_case.get_user

import com.djostanisic.githubusers.common.Resource
import com.djostanisic.githubusers.data.remote.dto.toGithubUserDetails
import com.djostanisic.githubusers.domain.model.GithubUserDetails
import com.djostanisic.githubusers.domain.repository.GithubUsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val repository: GithubUsersRepository
) {
    operator fun invoke(userName: String): Flow<Resource<GithubUserDetails>> = flow {
        try {
            emit(Resource.Loading())
            val user = repository.getUserDetails(userName).toGithubUserDetails()
            emit(Resource.Success(user))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}