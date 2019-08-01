package com.easyang.common.ktx.permission

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author SC16004984
 * @date 2019/8/1 0001.
 */
class PermissionFragment : Fragment() {
    companion object {
        fun newInstance(): PermissionFragment {
            return PermissionFragment()
        }
    }

    private val atomicInteger = AtomicInteger(100)
    private val callbackMap = mutableMapOf<Int, PermissionCallback>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true
    }

    fun requestPermission(permissions: Array<out String>, callback: PermissionCallback) {
        val requestCode = atomicInteger.getAndIncrement()
        callbackMap[requestCode] = callback
        requestPermissions(permissions, requestCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (callbackMap.containsKey(requestCode).not()) {
            return
        }
        val grantPermissions = mutableListOf<String>()
        val deniedPermissions = mutableListOf<String>()
        val neverAskAgainPermissions = mutableListOf<String>()
        val callback = callbackMap[requestCode] ?: return
        grantResults.forEachIndexed { index, result ->
            val permission = permissions[index]
            if (result == PackageManager.PERMISSION_DENIED) {
                if (shouldShowRequestPermissionRationale(permission)) {
                    deniedPermissions.add(permission)
                } else {
                    neverAskAgainPermissions.add(permission)
                }
            } else {
                grantPermissions.add(permission)
            }
        }

        if (grantPermissions.isNotEmpty() && deniedPermissions.isEmpty() && neverAskAgainPermissions.isEmpty()) {
            callback.onGranted()
            return
        }

        if (deniedPermissions.isNotEmpty()) {
            callback.onDenied(deniedPermissions)
        }

        if (neverAskAgainPermissions.isNotEmpty()) {
            callback.onNeverAskAgain(neverAskAgainPermissions)
        }


    }

}