package com.example.githubchallenge.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseEndlessScrollingAdapter<VM> :
    RecyclerView.Adapter<BaseEndlessScrollingAdapter<VM>.ViewHolder>() {
    abstract val listOrientation: ListOrientation

    abstract val contentItemLayout: Int

    protected var mList: List<VM> = emptyList()

    abstract fun bind(itemModel: VM, itemView: View)

    fun refreshList(newList: List<VM>?) {
        mList = newList ?: emptyList()
        notifyDataSetChanged()
    }

    fun resetList() {
        refreshList(null)
    }

    fun updateList(newList: List<VM>) {
        if (mList.containsAll(newList)) {
            mList = newList
        } else {
            mList = mList + newList
        }

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(mList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = contentItemLayout

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(layout, parent, false)
        )
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(itemModel: VM?) {
            itemModel?.let { bind(it, itemView) }
        }
    }

    enum class ListOrientation {
        HORIZONTAL, VERTICAL
    }
}
