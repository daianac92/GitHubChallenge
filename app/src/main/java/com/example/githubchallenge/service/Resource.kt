package com.example.githubchallenge.service

data class Resource<out T>(
    val status: NetworkStatus,
    val data: T? = null,
    val message: String? = null
)