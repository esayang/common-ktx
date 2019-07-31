package com.easyang.common.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author SC16004984
 * @date 2019/7/31 0031.
 */
abstract class BaseAdapter<T, VH : BaseViewHolder>(var list: MutableList<T>) : RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return BaseViewHolder(bindView(parent.context,viewType)) as VH
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        bindData(holder, list[position], position)
    }

    abstract fun bindView(context:Context,type: Int): View

    abstract fun bindData(holder: VH, item: T, position: Int)

    fun addData(item: T) {
        list.add(item)
        notifyItemInserted(list.size - 1)
    }

    fun addData(index: Int, item: T) {
        list.add(index, item)
        notifyItemInserted(index)
    }

    fun addData(items: List<T>) {
        list.addAll(items)
        notifyItemChanged(list.size - items.size, items.size)
    }

    fun addData(index: Int, items: List<T>) {
        list.addAll(index, items)
        notifyItemChanged(index, items.size)
    }

    fun replaceNewDataList(list: MutableList<T>) {
        this.list = list
        notifyDataSetChanged()
    }
}