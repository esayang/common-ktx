package com.easyang.common.ktx.textwatch

import android.text.Editable
import android.text.TextWatcher

/**
 * @author SC16004984
 * @date 2019/7/31 0031.
 */


final class TextWatchDsl : TextWatcher {
    private var beforeTextChanged: (CharSequence?, Int, Int, Int) -> Unit = { _, _, _, _ ->
    }

    private var onTextChanged: (CharSequence?, Int, Int, Int) -> Unit = { _, _, _, _ ->
    }

    private var afterTextChanged: (CharSequence?) -> Unit = { _ ->
    }

    public final fun beforeChanged(block: (CharSequence?, Int, Int, Int) -> Unit): Unit {
        beforeTextChanged = block
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeTextChanged.invoke(s, start, count, after)
    }

    public final fun onChanged(block: (CharSequence?, Int, Int, Int) -> Unit): Unit {
        onTextChanged = block
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged.invoke(s, start, before, count)
    }

    public final fun afterChanged(block: (CharSequence?) -> Unit): Unit {
        afterTextChanged = block
    }

    override fun afterTextChanged(s: Editable?) {
        afterTextChanged.invoke(s)
    }

}