package com.example.githubchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubchallenge.service.NetworkStatus
import com.example.githubchallenge.service.Resource
import com.example.githubchallenge.service.model.Repositories
import com.example.githubchallenge.service.repository.ProjectsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProjectListViewModel @Inject
constructor(private val repository: ProjectsRepository) : ViewModel() {


    private val _projectsList = MutableLiveData<Resource<Repositories>?>()
    val projectsList: LiveData<Resource<Repositories>?>
        get() = _projectsList


    /**
     * Function that gets the list of projects
     */
    fun getProjects() {
        _projectsList.value = Resource(NetworkStatus.LOADING)
        viewModelScope.launch {
            _projectsList.value = withContext(Dispatchers.IO) {
                val response = repository.getProjects()
                response
            }

        }

    }
}