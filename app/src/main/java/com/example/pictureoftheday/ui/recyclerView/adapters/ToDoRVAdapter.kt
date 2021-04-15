package com.example.pictureoftheday.ui.recyclerView.adapters

import android.graphics.Color
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureoftheday.R
import com.example.pictureoftheday.model.ToDoListData
import com.example.pictureoftheday.ui.recyclerView.OnListItemClickListener
import com.example.pictureoftheday.ui.recyclerView.OnStartDragListener
import com.example.pictureoftheday.ui.recyclerView.holders.BaseViewHolder
import com.example.pictureoftheday.ui.recyclerView.holders.ItemTouchHelperViewHolder
import kotlinx.android.synthetic.main.item_recycler_view_todo.view.*

class ToDoRVAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var data: MutableList<ToDoListData>,
    private val dragListener: OnStartDragListener
) : RecyclerView.Adapter<BaseViewHolder>(), ItemTouchHelperAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler_view_todo, parent, false)
        )

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

    fun appendItem() {
        data.add(generateItem())
        notifyItemInserted(itemCount - 1)
    }

    private fun generateItem() = ToDoListData("Введите название", "Введите задачу", false, false)

    inner class ViewHolder(view: View) : BaseViewHolder(view), ItemTouchHelperViewHolder {
        override fun bind(dataItem: ToDoListData) {
            itemView.title_item.text = data[layoutPosition].title
            itemView.text_view_task.text = data[layoutPosition].task

            itemView.check_view.visibility =
                if (data[layoutPosition].isComplete) View.GONE else View.VISIBLE
            itemView.check_complete.visibility =
                if (data[layoutPosition].isComplete) View.VISIBLE else View.GONE

            itemView.text_view_task.visibility =
                if (data[layoutPosition].isOpenNotes) View.GONE else View.VISIBLE
            itemView.edit_text_task.visibility =
                if (data[layoutPosition].isOpenNotes) View.VISIBLE else View.GONE
            itemView.save_task_item.visibility =
                if (data[layoutPosition].isOpenNotes) View.VISIBLE else View.GONE

            itemView.archive_item.setOnClickListener {
                data[layoutPosition].isOpenNotes = false
                itemView.motion_container2.setTransition(R.id.start2, R.id.end2)
                itemView.motion_container2.transitionToEnd()
            }

            itemView.title_item.setOnClickListener {
                itemView.container_item_to_do.setTransition(R.id.start1, R.id.end1);
                itemView.container_item_to_do.transitionToEnd()
            }

            itemView.check_view.setOnClickListener {
                data[layoutPosition].isComplete = true
                notifyItemChanged(layoutPosition)
            }

            itemView.check_complete.setOnClickListener {
                data[layoutPosition].isComplete = false
                notifyItemChanged(layoutPosition)
            }

            itemView.save_title.setOnClickListener {
                data[layoutPosition].title = itemView.title_item.text.toString()
                itemView.container_item_to_do.setTransition(R.id.end1, R.id.start1);
                itemView.container_item_to_do.transitionToEnd()
                itemView.title_item.text =
                    Editable.Factory.getInstance().newEditable(data[layoutPosition].task)
                notifyItemChanged(layoutPosition)
            }

            itemView.text_view_task.setOnClickListener {
                data[layoutPosition].isOpenNotes = true
                notifyItemChanged(layoutPosition)
            }

            itemView.save_task_item.setOnClickListener {
                itemView.motion_container2.setTransition(R.id.end2, R.id.start2)
                itemView.motion_container2.transitionToEnd()
                if (itemView.edit_text_task.text.toString() != "") {
                    data[layoutPosition].task = itemView.edit_text_task.text.toString()
                }
                itemView.edit_text_task.text =
                    Editable.Factory.getInstance().newEditable("")
                data[layoutPosition].isOpenNotes = false
                notifyItemChanged(layoutPosition)
            }

            itemView.delete_item.setOnClickListener { removeItem() }
        }

        private fun removeItem() {
            data.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(itemView.context.getColor(R.color.PDark))
        }
    }
}