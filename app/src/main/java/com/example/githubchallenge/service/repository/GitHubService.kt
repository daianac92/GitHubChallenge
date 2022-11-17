package com.example.githubchallenge.service.repository

import com.example.githubchallenge.service.model.Repositories
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {

    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") language: String,
        @Query("per_page") limit: Int,
        @Query("page") page: Int
    ): Repositories

}