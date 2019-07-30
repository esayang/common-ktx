package com.easyang.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.easyang.common.debounceClick
import com.easyang.common.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.debounceClick {
            toast("Hello World")
        }


    }

}
