package com.example.githubchallenge.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubchallenge.R
import com.example.githubchallenge.databinding.FragmentProjectListBinding
import com.example.githubchallenge.service.NetworkStatus
import com.example.githubchallenge.service.model.Item
import com.example.githubchallenge.view.adapter.EndlessRecyclerViewScrollListener
import com.example.githubchallenge.view.adapter.ProjectListAdapter
import com.example.githubchallenge.view.callback.AdapterListener
import com.example.githubchallenge.viewmodel.ProjectListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectListFragment : Fragment(), AdapterListener,
    EndlessRecyclerViewScrollListener.EndlessStateListener {

    private val viewModel by viewModels<ProjectListViewModel>()
    private lateinit var binding: FragmentProjectListBinding
    private lateinit var projectListAdapter: ProjectListAdapter

    private var currentPage = 1

    private lateinit var featuredNotificationScrollListener: EndlessRecyclerViewScrollListener

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
        getProjects(currentPage)
        setUpViews()

    }

    private fun setObservers() {
        viewModel.projectsList.observe(viewLifecycleOwner) {
            when (it?.status) {
                NetworkStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                NetworkStatus.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    projectListAdapter.updateList(it.data?.items ?: emptyList())
                    currentPage++

                }
                NetworkStatus.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(binding.root, "Oh! Error!", Snackbar.LENGTH_SHORT).show()


                }
                null -> {//do nothing
                }

            }
    }
    }


    private fun getProjects(page: Int) {
        viewModel.getProjects(page)
    }

    private fun setUpViews() {
        projectListAdapter = ProjectListAdapter(this@ProjectListFragment)
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
            featuredNotificationScrollListener = EndlessRecyclerViewScrollListener(
                layoutManager as LinearLayoutManager,
                this@ProjectListFragment
            )
            addOnScrollListener(featuredNotificationScrollListener)
        }
        binding.swipeRefresh.run {
            setOnRefreshListener {
                setColorSchemeResources(R.color.purple_500)
                currentPage = 1
                viewModel.getProjects(currentPage)
                isRefreshing = false
            }
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

    override fun onLoadMore(view: RecyclerView?) {
        viewModel.getProjects(currentPage)
    }


}