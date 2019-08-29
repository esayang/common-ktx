package com.easyang.core

import android.os.Bundle
import com.easyang.common.debounceClick
import com.easyang.common.toast
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.easyang.common.ktx.adapter.bindAdapter
import com.easyang.common.ktx.dp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.debounceClick {
            toast("Hello World")
        }

        toolBar.title = "Test"
        setSupportActionBar(toolBar)

        mList.layoutManager = LinearLayoutManager(this)
        val adapter = mList.bindAdapter(mutableListOf<String>()) {
            bindView { context, type ->
                val textView = TextView(context)
                textView.layoutParams = ViewGroup.LayoutParams(-1, 48.dp(this@MainActivity).toInt())
                return@bindView textView
            }
            bindData { holder, item, position ->
                (holder.itemView as TextView).text = item

            }
            empty {
                val inflate = View.inflate(this@MainActivity, R.layout.layout_empty, null)
                inflate.layoutParams = ViewGroup.LayoutParams(-1, -1)
                return@empty inflate
            }
            head {
                val inflate = View.inflate(this@MainActivity, R.layout.layout_empty, null)
                inflate.layoutParams = ViewGroup.LayoutParams(-1, 200)
                return@head inflate

            }
        }
        mList.addItemDecoration(DividerItemDecoration(this, VERTICAL))
    }
}
