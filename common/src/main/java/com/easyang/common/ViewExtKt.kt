package com.easyang.common

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat

/**
 * @author easyang
 * @date 2019/7/30 0030.
 */

inline fun View.debounceClick(crossinline click: (View) -> Unit) {
    debounceClick(2 * 1000L, click)
}

inline fun View.debounceClick(intervalDelay: Long, crossinline click: (View) -> Unit) {
    this.setOnClickListener {
        this.isClickable = false
        click.invoke(it)
        postDelayed({
            this.isClickable = true
        }, intervalDelay)
    }

}

inline fun View.backgroundColor( @ColorRes colorRes: Int) {
    this.setBackgroundColor(ResourcesCompat.getColor(resources, colorRes, null))
}



