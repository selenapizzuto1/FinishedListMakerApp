
package com.raywenderlich.listmaker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ListSelectionRecyclerViewAdapter(val lists: ArrayList<TaskList>, val clickListener: ListSelectionRecyclerViewClickListener) : RecyclerView.Adapter<ListSelectionViewHolder>() {

  interface ListSelectionRecyclerViewClickListener {
    fun listItemClicked(list: TaskList)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
    val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_selection_view_holder, parent, false)
    return ListSelectionViewHolder(view)
  }

  override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
    holder.listPosition.text = (position + 1).toString()
    holder.listTitle.text = lists[position].name
    holder.itemView.setOnClickListener {
      clickListener.listItemClicked(lists[position])
    }
  }

  override fun getItemCount(): Int {
    return lists.size
  }

  fun addList(list: TaskList) {
    // 1
    lists.add(list)

    // 2
    notifyDataSetChanged()
  }

}
