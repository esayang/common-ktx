package com.easyang.common.adapter.section

import com.easyang.common.adapter.multi.MultiViewItem

/**
 * @author SC16004984
 * @date 2019/7/31 0031.
 */
abstract class SectionItem : MultiViewItem {
    private var isHead: Boolean = false
    var head: String = ""

    constructor(isHead: Boolean) {
        this.isHead = isHead
    }

    constructor()

    companion object {
        const val VIEW_TYPE_ITEM = 1
        const val VIEW_TYPE_HEAD = 2
    }

    override fun getViewType(): Int {
        return VIEW_TYPE_ITEM
    }

}