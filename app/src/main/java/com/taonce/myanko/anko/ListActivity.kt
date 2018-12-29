package com.taonce.myanko.anko

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ListActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		Log.d("aulton", "intent value is ${intent.getStringExtra("aulton")}")
		ListUI().setContentView(this)
	}
}

class ListUI : AnkoComponent<ListActivity> {
	private var rv: RecyclerView? = null
	override fun createView(ui: AnkoContext<ListActivity>): View {
		with(ui) {
			rv = recyclerView {
				layoutManager = LinearLayoutManager(ui.ctx)
				lparams(width = matchParent, height = matchParent)
				adapter = MyAdapter(ui.ctx, mutableListOf("1",
						"2", "3", "4"))
			}
		}
		return rv as View
	}
}

class MyAdapter(private val context: Context,
				private val mData: MutableList<String>) : RecyclerView.Adapter<MyAdapter.MyHolder>() {

	override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
		// 根据anko生成itemView，并且给itemView的tag赋值，从而取得MyHolder
		return AdapterUI().createView(AnkoContext.create(context, p0)).tag as MyHolder
	}

	override fun getItemCount(): Int {
		return mData.size
	}

	override fun onBindViewHolder(holder: MyHolder, p1: Int) {
		holder.tv_name.text = mData[p1]
	}

	class MyHolder(view: View, val tv_name: TextView) : RecyclerView.ViewHolder(view)

	class AdapterUI : AnkoComponent<ViewGroup> {
		override fun createView(ui: AnkoContext<ViewGroup>): View {
			var tv_name: TextView? = null
			val item_view = with(ui) {
				relativeLayout {
					lparams(width = matchParent, height = dip(50))
					tv_name = textView {
						textSize = 12f
						textColor = Color.parseColor("#FF0000")
						backgroundColor = Color.parseColor("#FFF0F5")
						gravity = Gravity.CENTER
					}.lparams(width = matchParent, height = dip(50)) {
						// 设置外边距，
						topMargin = dip(10)
					}
				}
			}
			// 返回itemView，并且通过tag取得MyHolder
			item_view.tag = MyHolder(item_view, tv_name = tv_name!!)
			return item_view
		}
	}
}
