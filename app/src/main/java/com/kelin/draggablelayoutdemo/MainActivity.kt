package com.kelin.draggablelayoutdemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kelin.draggablelayout.DraggableLayout
import com.kelin.draggablelayout.DraggableLayout.OnLayoutChangeListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = TestAdapter(getDataList("我是条目"))
        rvList2.layoutManager = LinearLayoutManager(this)
        rvList2.adapter = TestAdapter(getDataList("我是拖拽列表的条目"))
        rvList2.requestDisallowInterceptTouchEvent(true)
        dlRoot.addOnScrollChangedListener(object : DraggableLayout.OnScrollChangedListener {
            override fun onScrollProgressChanged(currentProgress: Float) {
            }

            override fun onScrollFinished(currentStatus: Int) {
                Log.e("addOnScrollChangedListener","onScrollFinished"+currentStatus)
            }

            override fun onChildScroll(top: Int) {
                Log.e("addOnScrollChangedListener","onChildScroll"+top)
            }


        })
//        tvRoot.setOnClickListener { dlRoot.scrollToOpen() }
    }

    override fun onStart() {
        super.onStart()
        tvTitle.setOnClickListener{
            Toast.makeText(this,"23123",Toast.LENGTH_LONG).show()
        }
//        dlRoot.setIsSupportExit(true)
//        dlRoot.setAllowHorizontalScroll(true)
//        dlRoot.setOnScrollChangedListener(mOnScrollChangedListener)
    }

    private fun getDataList(title: String): List<String> {
        val list = ArrayList<String>()
        for (i: Int in 1..100) {
            list.add("$title $i")
        }
        return list
    }

    inner class TestAdapter(private val dataList: List<String>) : RecyclerView.Adapter<TestViewHolder>() {

        override fun getItemViewType(position: Int) = R.layout.item_test

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TestViewHolder(parent, viewType)

        override fun getItemCount() = dataList.size

        override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
            holder.onBindViewHolder(dataList[position])
        }
    }

    inner class TestViewHolder(parent: ViewGroup, @LayoutRes layoutRes: Int) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)) {

        fun onBindViewHolder(text: String) {
            itemView.findViewById<TextView>(R.id.tvText).text = text
        }
    }
}
