package com.easyang.common.ktx.permission

/**
 * @author SC16004984
 * @date 2019/8/1 0001.
 */
interface PermissionCallback {
    fun onGranted()

    fun onDenied(deniedPermissions: MutableList<String>)

    fun onNeverAskAgain(neverAskAgainPermissions: MutableList<String>)

}

