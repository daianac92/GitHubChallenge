package com.example.githubchallenge.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.githubchallenge.R
import com.example.githubchallenge.databinding.FragmentProjectDetailBinding
import com.example.githubchallenge.service.model.Item
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectDetailFragment : Fragment() {

    private lateinit var binding: FragmentProjectDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProjectDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarDetail)
        getItem()

    }

    private fun getItem() {
        val item = arguments?.getSerializable("Project") as Item
        setUpViews(item)
    }

    private fun setUpViews(item: Item) {
        with(binding) {
            tvProjectDetailName.text =
                String.format(resources.getString(R.string.projectTitleDetail), item.full_name)
            tvProjectDetailOwner.text =
                String.format(resources.getString(R.string.projectOwnerDetail), item.owner.login)
            tvProjectDetailDescription.text =
                String.format(
                    resources.getString(R.string.projectDescriptionDetail),
                    item.description
                )
            tvProjectDetailCreatedAt.text =
                String.format(
                    resources.getString(R.string.projectCreatedAtDetail),
                    item.created_at.substring(0, 10)
                )
            tvProjectDetailUpdatedAt.text =
                String.format(
                    resources.getString(R.string.projectUpdatedAtDetail),
                    item.updated_at.substring(0, 10)
                )
            tvProjectDetailWatchers.text =
                String.format(
                    resources.getString(R.string.projectWatchersDetail),
                    item.watchers_count.toString()
                )
            tvProjectDetailCloneUrl.text =
                String.format(resources.getString(R.string.projectCloneUrlDetail), item.clone_url)
            toolbarDetail.setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }
}