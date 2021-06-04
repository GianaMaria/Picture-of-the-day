package com.example.pictureoftheday.ui.recyclerView.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureoftheday.model.ToDoListData

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(dataItem: ToDoListData)
}