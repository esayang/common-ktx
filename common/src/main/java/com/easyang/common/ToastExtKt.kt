package com.easyang.common

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 * @author SC16004984
 * @date 2019/7/30 0030.
 */
inline fun  Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

inline fun Fragment.toast(msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

inline fun Context.toast(@StringRes msg: Int) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

inline fun Fragment.toast(@StringRes msg: Int) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}
