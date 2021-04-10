package com.example.pictureoftheday.ui.recyclerView.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureoftheday.model.ToDoListData
import com.example.pictureoftheday.ui.recyclerView.OnListItemClickListener
import com.example.pictureoftheday.ui.recyclerView.OnStartDragListener
import com.example.pictureoftheday.ui.recyclerView.holders.BaseViewHolder

class ToDoRVAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var data: MutableList<ToDoListData>,
    private val dragListener: OnStartDragListener
) : RecyclerView.Adapter<BaseViewHolder>(), ItemTouchHelperAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemCount(): Int = data.size

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        data.removeAt(fromPosition).apply {
            data.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

}