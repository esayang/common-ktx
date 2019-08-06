package com.easyang.common.adapter

/**
 * @author SC16004984
 * @date 2019/8/6 0006.
 */
interface ItemViewDelegate<T> {
    abstract fun getItemLayout(): Int

    abstract fun bindData(holder: BaseViewHolder, item: T, position: Int)

}