package com.easyang.common.adapter.section

import com.easyang.common.adapter.BaseViewHolder
import com.easyang.common.adapter.ItemViewDelegate
import com.easyang.common.adapter.multi.BaseMultiViewAdapter

/**
 * @author SC16004984
 * @date 2019/7/31 0031.
 */
class BaseSectionAdapter<T : SectionItem>(itemLayoutRes: Int, headLayoutRes: Int, list: MutableList<T>) : BaseMultiViewAdapter<T>(list) {

    private var onBindHeadData: (holder: BaseViewHolder, item: T) -> Unit = { holder, item -> }


    private var onBindItemData: (holder: BaseViewHolder, item: T, position: Int) -> Unit = { holder, item, position -> }


    fun onBindHead(block: (holder: BaseViewHolder, item: T) -> Unit) {
        this.onBindHeadData = block
    }

    fun onBindHead(block: (holder: BaseViewHolder, item: T, position: Int) -> Unit) {
        this.onBindItemData = block
    }

    public fun onBindHeadData(holder: BaseViewHolder, item: T) {
        onBindHeadData.invoke(holder, item)

    }

    public fun onBindItemData(holder: BaseViewHolder, item: T, position: Int) {
        onBindItemData.invoke(holder, item, position)

    }

    init {
        addItemViewDelegate(SectionItem.VIEW_TYPE_ITEM, itemViewDelegate = object : ItemViewDelegate<T> {
            override fun bindData(holder: BaseViewHolder, item: T, position: Int) {
                onBindItemData(holder, item, position)
            }

            override fun getItemLayout(): Int {
                return itemLayoutRes
            }
        })
        addItemViewDelegate(SectionItem.VIEW_TYPE_HEAD, object : ItemViewDelegate<T> {
            override fun bindData(holder: BaseViewHolder, item: T, position: Int) {
                onBindHeadData(holder, item)
            }

            override fun getItemLayout(): Int {
                return headLayoutRes
            }
        })
    }


}