package com.easyang.common.ktx

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat

/**
 * @author easyang
 * @date 2019/7/30 0030.
 */

var VIEW_CLICK_DEBOUNCE_INTERVAL: Long = 1000

inline fun View.debounceClick(crossinline click: (View) -> Unit) {
    debounceClick(VIEW_CLICK_DEBOUNCE_INTERVAL, click)
}

inline fun View.debounceClick(delayInterval: Long, crossinline click: (View) -> Unit) {
    this.setOnClickListener {
        this.isClickable = false
        click.invoke(it)
        postDelayed({
            this.isClickable = true
        }, delayInterval)
    }
}

 fun View.backgroundColor(@ColorRes colorRes: Int) {
    this.setBackgroundColor(ResourcesCompat.getColor(resources, colorRes, null))
}



