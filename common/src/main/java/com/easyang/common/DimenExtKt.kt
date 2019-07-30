package com.easyang.common

import android.content.Context

/**
 * @author SC16004984
 * @date 2019/7/30 0030.
 */

public inline fun Int.dp(context: Context): Float {
    return this * context.resources.displayMetrics.density + 0.5f
}

public inline fun Float.dp(context: Context): Float {
    return this * context.resources.displayMetrics.density + 0.5f
}

public inline fun Int.px(context: Context): Float {
    return this / context.resources.displayMetrics.density + 0.5f
}

public inline fun Float.px(context: Context): Float {
    return this / context.resources.displayMetrics.density + 0.5f
}
