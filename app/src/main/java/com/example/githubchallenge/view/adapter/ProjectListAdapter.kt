package com.example.githubchallenge.view.adapter

import android.view.View
import com.example.githubchallenge.R
import com.example.githubchallenge.databinding.ItemProjectBinding
import com.example.githubchallenge.service.model.Item
import com.example.githubchallenge.view.callback.AdapterListener

class ProjectListAdapter(
    private var listener: AdapterListener
) : BaseEndlessScrollingAdapter<Item>() {

    override val listOrientation: ListOrientation
        get() = ListOrientation.VERTICAL
    override val contentItemLayout: Int
        get() = R.layout.item_project


    override fun bind(item: Item, itemView: View) {
        val binding = ItemProjectBinding.bind(itemView)

        with(binding) {
            tvProjectTitle.text = item.full_name
            tvProjectOwner.text = item.owner.login
            cvProjectItem.setOnClickListener { listener.onItemClick(item) }

        }
    }

}

