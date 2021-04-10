package com.example.pictureoftheday.ui.recyclerView

import com.example.pictureoftheday.model.ToDoListData

interface OnListItemClickListener {
    fun onItemClick(data: ToDoListData)
}