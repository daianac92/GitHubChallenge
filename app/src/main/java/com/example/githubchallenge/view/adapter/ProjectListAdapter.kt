package com.example.githubchallenge.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubchallenge.R
import com.example.githubchallenge.databinding.ItemProjectBinding
import com.example.githubchallenge.service.model.Item
import com.example.githubchallenge.view.callback.OnItemClickListener

class ProjectListAdapter(
    private var projectsList: List<Item>,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<ProjectsViewHolder>() {


    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_project, parent, false)
        return ProjectsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectsViewHolder, position: Int) {
        val item = projectsList[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int = projectsList.size

}


class ProjectsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemProjectBinding.bind(view)

    fun bind(item: Item) {
        with(binding) {
            tvProjectTitle.text = item.full_name
            tvProjectOwner.text = item.owner.login
        }

    }
}

