package com.raywenderlich.listmaker

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

private const val ARG_LIST = "list"

class ListDetailFragment : Fragment() {

  lateinit var list: TaskList

  lateinit var listItemsRecyclerView: RecyclerView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    arguments?.let {
      list = it.getParcelable(ARG_LIST)!!
    }
  }

  override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?): View? {

    // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_list_detail, container, false)

    view?.let {
      listItemsRecyclerView = it.findViewById<RecyclerView>(R.id.list_items_reyclerview)
      listItemsRecyclerView.adapter = ListItemsRecyclerViewAdapter(list)
      listItemsRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    return view
  }

  fun addTask(item: String) {

    list.tasks.add(item)

    val listRecyclerAdapter =  listItemsRecyclerView.adapter as ListItemsRecyclerViewAdapter
    listRecyclerAdapter.list = list
    listRecyclerAdapter.notifyDataSetChanged()
  }

  companion object {

    fun newInstance(list: TaskList): ListDetailFragment {
      val fragment = ListDetailFragment()
      val args = Bundle()
      args.putParcelable(ARG_LIST, list)
      fragment.arguments = args
      return fragment
    }
  }
}
