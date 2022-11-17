package com.example.githubchallenge.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubchallenge.R
import com.example.githubchallenge.databinding.FragmentProjectListBinding
import com.example.githubchallenge.service.NetworkStatus
import com.example.githubchallenge.service.model.Item
import com.example.githubchallenge.view.adapter.ProjectListAdapter
import com.example.githubchallenge.view.callback.AdapterListener
import com.example.githubchallenge.viewmodel.ProjectListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectListFragment : Fragment(), AdapterListener {

    private val viewModel by viewModels<ProjectListViewModel>()
    private lateinit var binding: FragmentProjectListBinding
    private lateinit var projectListAdapter: ProjectListAdapter

    private val projects = mutableListOf<Item>()


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
        setObservers()
        getProjects()
        setUpViews()

    }

    private fun setObservers() {
        viewModel.projectsList.observe(viewLifecycleOwner) {
            when (it?.status) {
                NetworkStatus.LOADING -> {}
                NetworkStatus.SUCCESS -> {
                    updateList(it.data?.items)
                }
                NetworkStatus.ERROR -> TODO()
                null -> TODO()
            }
        }
    }

    private fun updateList(repositories: List<Item>?) {
        with(projects) {
            clear()
            addAll(repositories ?: emptyList())
        }
        binding.rvProjects.adapter?.notifyDataSetChanged()

    }


    private fun getProjects() {
        viewModel.getProjects()
    }

    private fun setUpViews() {
        projectListAdapter = ProjectListAdapter(projects, this@ProjectListFragment)
        val manager = LinearLayoutManager(context)
        binding.rvProjects.apply {
            layoutManager = manager
            adapter = projectListAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }


    }

    override fun onItemClick(item: Item) {
        val args = Bundle()
        args.putSerializable("Project", item)
        val fragment = ProjectDetailFragment()
        fragment.arguments = args
        parentFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.fragment_container_view, fragment)
            .addToBackStack(null)
            .commit()
    }


}