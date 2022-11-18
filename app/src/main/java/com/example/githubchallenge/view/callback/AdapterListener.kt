package com.example.githubchallenge.view.callback

import com.example.githubchallenge.service.model.Item

interface AdapterListener {
    /**This function handles the click on the items in the recycler view
     * @param project represents the item that was clicked
     **/
    fun onItemClick(project: Item)
}