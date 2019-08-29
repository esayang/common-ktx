package com.easyang.common.adapter

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

/**
 * @author SC16004984
 * @date 2019/7/31 0031.
 */
open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setText(@IdRes viewId: Int, text: String): TextView {
        val view = getView<TextView>(viewId)
        view.text = text
        return view
    }

    fun setTextColor(@IdRes viewId: Int, @ColorInt color: Int): TextView {
        val view = getView<TextView>(viewId)
        view.setTextColor(color)
        return view
    }

    fun setTextSize(@IdRes viewId: Int, textSize: Float): TextView {
        val view = getView<TextView>(viewId)
        view.textSize = textSize
        return view
    }

    fun <T : View> getView(@IdRes viewId: Int): T {
        return itemView.findViewById(viewId)
    }


}