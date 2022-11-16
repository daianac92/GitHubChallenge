package com.example.githubchallenge.viewmodel

import androidx.lifecycle.ViewModel
import com.example.githubchallenge.service.repository.ProjectsRepository

class ProjectListViewModel constructor(private val repository: ProjectsRepository) : ViewModel()