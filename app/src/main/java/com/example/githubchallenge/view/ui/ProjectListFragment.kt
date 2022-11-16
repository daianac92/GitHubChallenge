package com.example.githubchallenge.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.githubchallenge.R
import com.example.githubchallenge.databinding.FragmentProjectListBinding
import com.example.githubchallenge.viewmodel.ProjectListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectListFragment : Fragment() {

    private val viewModel by viewModels<ProjectListViewModel>()
    private lateinit var binding: FragmentProjectListBinding
    private val navController by lazy { requireActivity().findNavController(R.id.fragment_container_view) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProjectListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()

    }

    private fun setUpViews() {

    }


}