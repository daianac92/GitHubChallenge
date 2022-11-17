package com.example.githubchallenge.service.model

import java.io.Serializable

data class Item(
    val created_at: String,
    val updated_at: String,
    val clone_url: String,
    val owner: Owner,
    val full_name: String,
    val name: String,
    val description: String,
    val watchers_count: Int,
    val `private`: Boolean,
) : Serializable