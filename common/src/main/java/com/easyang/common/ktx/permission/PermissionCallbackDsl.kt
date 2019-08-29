package com.easyang.common.ktx.permission

/**
 * @author SC16004984
 * @date 2019/8/1 0001.
 */
class PermissionCallbackDsl: PermissionCallback {
    private var onGranted = {}
    private var onDenied: (MutableList<String>) -> Unit = {}
    private var onNeverAskAgain: (MutableList<String>) -> Unit = {}
    fun onGranted(block: () -> Unit) {
        onGranted = block
    }

    fun onDenied(block: (MutableList<String>) -> Unit) {
        onDenied = block
    }

    fun onNeverAskAgain(block: (MutableList<String>) -> Unit) {
        onNeverAskAgain = block
    }

    override fun onGranted() {
        onGranted.invoke()
    }

    override fun onDenied(deniedPermissions: MutableList<String>) {
        onDenied.invoke(deniedPermissions)

    }

    override fun onNeverAskAgain(neverAskAgainPermissions: MutableList<String>) {
        onNeverAskAgain.invoke(neverAskAgainPermissions)
    }
}