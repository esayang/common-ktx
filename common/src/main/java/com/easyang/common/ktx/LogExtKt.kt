package com.easyang.common.ktx

import android.util.Log

/**
 * @author SC16004984
 * @date 2019/7/30 0030.
 */

class LogggerHelper {
}
const val LOGGER:String = "Logger"
fun String.log(){
    Log.v(LOGGER,this)
}











