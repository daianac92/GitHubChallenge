package com.example.githubchallenge.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        getItem()

    }

    private fun getItem() {
        val item = arguments?.getSerializable("Project") as Item
        setUpViews(item)
    }

    private fun setUpViews(item: Item) {
        with(binding) {
            tvProjectDetailName.text = item.full_name
            tvProjectDetailOwner.text = item.owner.login
            tvProjectDetailDescription.text = item.description
            tvProjectDetailCreatedAt.text = item.created_at
            tvProjectDetailUpdatedAt.text = item.updated_at
            tvProjectDetailWatchers.text = item.watchers_count.toString()
            tvProjectDetailCloneUrl.text = item.clone_url
        }
    }
}