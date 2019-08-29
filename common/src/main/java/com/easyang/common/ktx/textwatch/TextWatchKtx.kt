package com.easyang.common.ktx.textwatch

import android.widget.TextView

/**
 * @author SC16004984
 * @date 2019/7/31 0031.
 */

inline fun TextView.textWatch(crossinline watch: TextWatchDsl.() -> Unit) {
    val watchDsl = TextWatchDsl()
    this.addTextChangedListener(watchDsl)
    watch.invoke(watchDsl)
}
