package com.easyang.common.adapter.multi

import android.content.Context
import android.view.View
import com.easyang.common.adapter.BaseAdapter
import com.easyang.common.adapter.BaseViewHolder
import com.easyang.common.adapter.ItemViewDelegate

/**
 * @author SC16004984
 * @date 2019/7/31 0031.
 */
open class BaseMultiViewAdapter<T : MultiViewItem>(list: MutableList<T>) : BaseAdapter<T>(list) {

    private var mItemViewDelegates: MutableMap<Int, ItemViewDelegate<T>> = mutableMapOf()

    public fun addItemViewDelegate(type: Int, itemViewDelegate: ItemViewDelegate<T>): BaseMultiViewAdapter<T> {
        mItemViewDelegates[type] = itemViewDelegate
        return this
    }

    override fun getDefItemViewType(position: Int): Int {
        return list[position].getViewType()
    }

    override fun bindView(context: Context, type: Int): View {
        if (mItemViewDelegates.containsKey(type)) {
            throw IllegalArgumentException("adapter have no this ViewType")
        }
        return View.inflate(context, mItemViewDelegates[type]!!.getItemLayout(), null)
    }

    override fun bindData(holder: BaseViewHolder, item: T, position: Int) {
        if (mItemViewDelegates.containsKey(item.getViewType())) {
            mItemViewDelegates[item.getViewType()]!!.bindData(holder, item, position)
        }
    }

}