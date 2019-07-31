package com.easyang.core

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.easyang.common.ktx.VIEW_CLICK_DEBOUNCE_INTERVAL
import com.easyang.common.ktx.debounceClick
import com.easyang.common.ktx.log
import com.easyang.common.ktx.permission.requestPermission
import com.easyang.common.ktx.textwatch.textWatch
import com.easyang.common.ktx.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etInput.textWatch {
            onChanged { charSequence, _, _, _ ->
                textView.text = charSequence
            }
        }
        requestPermission(Manifest.permission.CAMERA) {
            onGranted {

            }

        }
        VIEW_CLICK_DEBOUNCE_INTERVAL.toString().log()
        textView.debounceClick {
            toast("Hello World")
        }


    }

}
