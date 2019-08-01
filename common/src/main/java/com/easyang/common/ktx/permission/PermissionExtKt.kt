package com.easyang.common.ktx.permission

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * @author SC16004984
 * @date 2019/7/31 0031.
 */
const val PERMISSION_TAG = "PERMISSION_FRAGMENT_TAG"

fun FragmentActivity.getPermissionFragment(): PermissionFragment {
    var fragment = supportFragmentManager.findFragmentByTag(PERMISSION_TAG) as PermissionFragment?
    if (fragment == null) {
        fragment = PermissionFragment.newInstance()
        supportFragmentManager.beginTransaction().add(fragment, PERMISSION_TAG).commitNow()
    }
    return fragment
}

inline fun FragmentActivity.requestPermission(permission: String, crossinline callback: PermissionCallbackDsl.() -> Unit) {
    this.requestPermissions(arrayOf(permission), callback)
}

inline fun FragmentActivity.requestPermissions(permissions: List<String>, crossinline callback: PermissionCallbackDsl.() -> Unit) {
    this.requestPermissions(permissions.toTypedArray(), callback)
}

inline fun Fragment.requestPermissions(permissions: List<String>, crossinline callback: PermissionCallbackDsl.() -> Unit) {
    this.requestPermissions(permissions.toTypedArray(), callback)
}


inline fun FragmentActivity.requestPermissions(permissions: Array<String>, crossinline callback: PermissionCallbackDsl.() -> Unit) {
    val callbackDsl = PermissionCallbackDsl()
    callback.invoke(callbackDsl)
    getPermissionFragment()
            .requestPermission(permissions, callbackDsl)
}


inline fun Fragment.requestPermissions(permissions: Array<String>, crossinline callback: PermissionCallbackDsl.() -> Unit) {
    val callbackDsl = PermissionCallbackDsl()
    callback.invoke(callbackDsl)
    if (activity == null) {
        return
    }
    activity!!.getPermissionFragment()
            .requestPermission(permissions, callbackDsl)
}

