package com.example.githubchallenge.service.repository

import com.example.githubchallenge.service.NetworkStatus
import com.example.githubchallenge.service.Resource
import com.example.githubchallenge.service.model.Repositories
import retrofit2.HttpException
import javax.inject.Inject

class ProjectsRepository @Inject constructor(private val api: GitHubService) {

    suspend fun getProjects(): Resource<Repositories> {
        val response = try {
            api.getRepositories("kotlin", 10, 1)
        } catch (e: Exception) {
            return Resource(NetworkStatus.ERROR, null, e.message)
        } catch (t: HttpException) {
            return Resource(NetworkStatus.ERROR, null, t.message)
        }
        return Resource(NetworkStatus.SUCCESS, response, null)
    }
}