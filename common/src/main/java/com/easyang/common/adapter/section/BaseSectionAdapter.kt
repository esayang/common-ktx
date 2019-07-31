package com.easyang.common.adapter.section

import com.easyang.common.adapter.BaseAdapter
import com.easyang.common.adapter.BaseViewHolder

/**
 * @author SC16004984
 * @date 2019/7/31 0031.
 */
abstract class BaseSectionAdapter<T : SectionItem, VH : BaseViewHolder> private constructor(
    val itemLayoutRes: Int,
    val headLayputRes: Int,
    list: MutableList<SectionItem>
) :
    BaseAdapter<SectionItem, VH>(list) {

}