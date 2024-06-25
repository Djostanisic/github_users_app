package com.djostanisic.githubusers.domain.use_case.get_user

import com.djostanisic.githubusers.common.Resource
import com.djostanisic.githubusers.data.remote.dto.toOrganization
import com.djostanisic.githubusers.domain.model.Organization
import com.djostanisic.githubusers.domain.repository.GithubUsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetOrganizationsUseCase @Inject constructor(
    private val repository: GithubUsersRepository
) {
    operator fun invoke(userName: String): Flow<Resource<List<Organization?>>> = flow {
        try {
            emit(Resource.Loading())
            val organizations = repository.getOrganizations(userName).map { it?.toOrganization() }
            emit(Resource.Success(organizations))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}