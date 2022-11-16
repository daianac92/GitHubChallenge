package com.example.githubchallenge.service.model

data class Repositories(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)