package com.easyang.common.ktx

import android.content.Context

/**
 * @author SC16004984
 * @date 2019/7/30 0030.
 */

public fun Int.dp(context: Context): Float {
    return this * context.resources.displayMetrics.density + 0.5f
}

public fun Float.dp(context: Context): Float {
    return this * context.resources.displayMetrics.density + 0.5f
}

public fun Int.px(context: Context): Float {
    return this / context.resources.displayMetrics.density + 0.5f
}

public fun Float.px(context: Context): Float {
    return this / context.resources.displayMetrics.density + 0.5f
}
