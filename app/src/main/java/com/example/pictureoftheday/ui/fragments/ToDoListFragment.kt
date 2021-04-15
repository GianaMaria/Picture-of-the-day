package com.example.pictureoftheday.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureoftheday.R
import com.example.pictureoftheday.model.ToDoListData
import com.example.pictureoftheday.ui.recyclerView.ItemTouchHelperCallback
import com.example.pictureoftheday.ui.recyclerView.OnListItemClickListener
import com.example.pictureoftheday.ui.recyclerView.OnStartDragListener
import com.example.pictureoftheday.ui.recyclerView.adapters.ToDoRVAdapter
import kotlinx.android.synthetic.main.fragment_to_do_list.*

class ToDoListFragment : Fragment() {

    private lateinit var adapter: ToDoRVAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arrayListOf<ToDoListData>(generateItem())

        adapter = ToDoRVAdapter(
            object : OnListItemClickListener {
                override fun onItemClick(data: ToDoListData) {

                }
            },
            data,
            object : OnStartDragListener {
                override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                    itemTouchHelper.startDrag(viewHolder)
                }
            }
        )
        recycler_view_todo.adapter = adapter
        recyclerActivityFAB.setOnClickListener { adapter.appendItem() }
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(recycler_view_todo)
    }

    private fun generateItem() = ToDoListData("Введите название", "Введите задачу", false)
}
