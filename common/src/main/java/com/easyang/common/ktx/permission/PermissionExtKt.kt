package com.easyang.common.ktx.permission

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author SC16004984
 * @date 2019/7/31 0031.
 */
const val PERMISSION_TAG = "PERMISSION_FRAGMENT_TAG"

fun FragmentActivity.getPermissionFragment(): PermissionFragment {
    var fragment = supportFragmentManager.findFragmentByTag(PERMISSION_TAG) as PermissionFragment?
    if (fragment == null) {
        fragment = PermissionFragment.newInstance()
        supportFragmentManager.beginTransaction().add(fragment, PERMISSION_TAG)
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
            .requestPermmission(permissions, callbackDsl)
}


inline fun Fragment.requestPermissions(permissions: Array<String>, crossinline callback: PermissionCallbackDsl.() -> Unit) {
    val callbackDsl = PermissionCallbackDsl()
    callback.invoke(callbackDsl)
    if (activity == null) {
        return
    }
    activity!!.getPermissionFragment()
            .requestPermmission(permissions, callbackDsl)
}


class PermissionCallbackDsl : PermissionCallback {
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

    fun requestPermmission(permissions: Array<out String>, callback: PermissionCallback) {
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

interface PermissionCallback {

    fun onGranted()

    fun onDenied(deniedPermissions: MutableList<String>)

    fun onNeverAskAgain(neverAskAgainPermissions: MutableList<String>)

}
