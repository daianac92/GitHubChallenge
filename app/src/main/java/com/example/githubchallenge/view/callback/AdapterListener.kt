package com.example.githubchallenge.view.callback

import com.example.githubchallenge.service.model.Item

interface AdapterListener {
    fun onItemClick(project: Item)
}