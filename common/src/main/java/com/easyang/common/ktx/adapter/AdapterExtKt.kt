package com.easyang.common.ktx.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.easyang.common.adapter.BaseAdapter
import com.easyang.common.adapter.multi.BaseMultiViewAdapter
import com.easyang.common.adapter.multi.MultiViewItem
import com.easyang.common.adapter.section.BaseSectionAdapter
import com.easyang.common.adapter.section.SectionItem

/**
 * @author SC16004984
 * @date 2019/8/6 0006.
 */
fun <T> RecyclerView.bindAdapter(list: MutableList<T>, block: BaseAdapter<T>.() -> Unit): BaseAdapter<T> {
    val dsl = BaseAdapter(list)
    this.adapter = dsl
    block.invoke(dsl)
    return dsl
}

fun <T : MultiViewItem> RecyclerView.bindMultiAdapter(list: MutableList<T>,
                                                      block: BaseMultiViewAdapter<T>.() -> Unit): BaseMultiViewAdapter<T> {
    val dsl = BaseMultiViewAdapter(list)
    this.adapter = dsl
    block.invoke(dsl)
    return dsl
}

fun <T : SectionItem> RecyclerView.bindSectionAdapter(itemLayoutRes: Int, headLayoutRes: Int, list: MutableList<T>,
                                                      block: BaseSectionAdapter<T>.() -> Unit): BaseAdapter<T> {
    val dsl = BaseSectionAdapter(itemLayoutRes, headLayoutRes, list)
    this.adapter = dsl
    block.invoke(dsl)
    return dsl
}





