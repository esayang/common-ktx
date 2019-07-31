package com.easyang.common.adapter.multi

import android.content.Context
import android.view.View
import androidx.annotation.LayoutRes
import com.easyang.common.adapter.BaseAdapter
import com.easyang.common.adapter.BaseViewHolder

/**
 * @author SC16004984
 * @date 2019/7/31 0031.
 */
class BaseMultiViewAdapter<T : MultiViewItem>(list: MutableList<T>) : BaseAdapter<T, BaseViewHolder>(list) {

    private var mViewTypeMap: MutableMap<Int, Int> = mutableMapOf()

    fun addItemTypeLayut(type: Int, @LayoutRes layoutRes: Int): BaseMultiViewAdapter<T> {
        mViewTypeMap[type] = layoutRes
        return this
    }

    override fun bindView(context: Context, type: Int): View {
        if (mViewTypeMap.containsKey(type)) {
            throw IllegalArgumentException("adapter have no this ViewType")
        }
        return View.inflate(context, mViewTypeMap[type]!!, null)
    }

    override fun bindData(holder: BaseViewHolder, item: T, position: Int) {
    }

}