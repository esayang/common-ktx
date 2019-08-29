package com.easyang.common.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author SC16004984
 * @date 2019/7/31 0031.
 */
open class BaseAdapter<T>(var list: MutableList<T>) : RecyclerView.Adapter<BaseViewHolder>() {
    companion object {
        const val EMPTY_TYPE = -0X1000
        const val HEAD_TYPE = -0X1001
    }

    private var mEmptyView: View? = null
    private var mHeadView: View? = null
    private var isShowEmptyView = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        if (HEAD_TYPE == viewType && mHeadView != null) {
            return BaseViewHolder(mHeadView!!)
        }
        if (viewType == EMPTY_TYPE && mEmptyView != null) {
            return BaseViewHolder(mEmptyView!!)
        }
        return BaseViewHolder(bindView(parent.context, viewType))
    }

    override fun getItemCount(): Int {
        var itemCount = list.size
        itemCount += getHeadCount()
        if (itemCount == 0 && mEmptyView != null) {
            isShowEmptyView = true
            return 1
        }
        isShowEmptyView = false
        return itemCount
    }

    override fun getItemViewType(position: Int): Int {
        var index = position
        if (mHeadView != null && position == 0) {
            index -= 1
            return HEAD_TYPE
        }
        if (isShowEmptyView) {
            return EMPTY_TYPE
        }



        return getDefItemViewType(index)
    }


    protected open fun getDefItemViewType(position: Int): Int {
        return getItemViewType(position)
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        var index = position
        if (isShowEmptyView) {
            return
        }
        index -= getHeadCount()
        if (index < 0) {
            return
        }
        bindData(holder, list[index], index)
    }

    private var bindView: (context: Context, type: Int) -> View = { context, type ->
        View(context)
    }

    private var bindData: (holder: BaseViewHolder, item: T, position: Int) -> Unit = { holder, item, position ->

    }


    public fun bindView(block: (context: Context, type: Int) -> View) {
        this.bindView = block
    }

    public fun bindData(block: (holder: BaseViewHolder, item: T, position: Int) -> Unit) {
        this.bindData = block
    }

    public fun empty(block: () -> View) {
        setEmptyView(block.invoke())
    }

    public fun head(block: () -> View) {
        mHeadView = block.invoke()
    }

    open fun bindView(context: Context, type: Int): View {
        return bindView.invoke(context, type)
    }

    open fun bindData(holder: BaseViewHolder, item: T, position: Int) {
        bindData.invoke(holder, item, position)

    }

    public fun setEmptyView(emptyView: View) {
        mEmptyView = emptyView
    }

    public fun addHeadView() {

    }

    fun addData(item: T) {
        list.add(item)

        notifyItemInserted(list.size + getHeadCount() - 1)
    }

    fun addData(index: Int, item: T) {
        list.add(index, item)
        notifyItemInserted(index + getHeadCount())
    }

    fun addData(items: List<T>) {
        list.addAll(items)
        notifyItemChanged(list.size + getHeadCount() - items.size, items.size)
    }

    fun addData(index: Int, items: List<T>) {
        list.addAll(index, items)
        notifyItemChanged(index + getHeadCount(), items.size)
    }

    fun replaceNewDataList(list: MutableList<T>) {
        this.list = list
        notifyDataSetChanged()
    }

    private fun getHeadCount(): Int {
        return if (mHeadView != null) {
            1
        } else {
            0
        }
    }

}