package com.djostanisic.githubusers.domain.use_case.get_users

import com.djostanisic.githubusers.common.Resource
import com.djostanisic.githubusers.data.remote.dto.toGithubUser
import com.djostanisic.githubusers.domain.model.GithubUser
import com.djostanisic.githubusers.domain.repository.GithubUsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: GithubUsersRepository
) {
    operator fun invoke(): Flow<Resource<List<GithubUser>>> = flow {
        try {
            emit(Resource.Loading())
            val users = repository.getUsers().map { it.toGithubUser() }
            emit(Resource.Success(users))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}