package com.easyang.core

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.easyang.common.ktx.debounceClick
import com.easyang.common.ktx.log
import com.easyang.common.ktx.permission.requestPermissions
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
        requestPermissions(arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            onGranted {
                "onGranted".log()

            }
            onDenied {
                "onDenied".log()

            }
            onNeverAskAgain {
                "onNeverAskAgain".log()

            }

        }

        textView.debounceClick {
            toast("Hello World")
        }
    }
}
