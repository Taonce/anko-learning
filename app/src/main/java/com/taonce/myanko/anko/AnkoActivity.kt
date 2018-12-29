package com.taonce.myanko.anko

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.taonce.myanko.R
import com.taonce.myanko.anko.custom.SettingActivity
import com.taonce.myanko.anko.custom.myLinearLayout
import com.taonce.myanko.anko.view.CustomCircleActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onLongClick

class AnkoActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		MainUI().setContentView(this@AnkoActivity)
	}
}

class MainUI : AnkoComponent<AnkoActivity> {
	override fun createView(ui: AnkoContext<AnkoActivity>) = with(ui) {
		verticalLayout {
			gravity = Gravity.CENTER
			// 布局的属性params在闭包里面的lparams中设置，但是button、TextView等控件的属性params是在闭包外的lparams设置
			lparams(matchParent, matchParent)
			editText {
				hint = "userName"
				gravity = Gravity.CENTER
				// 监听输入框输入情况
				addTextChangedListener(object : TextWatcher {
					override fun afterTextChanged(s: Editable?) {
					}

					override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
					}

					override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
					}
				})
			}.lparams(width = matchParent, height = 200)

			editText {
				hint = "password"
				top = 20
				gravity = Gravity.CENTER
			}.lparams(width = matchParent, height = 200)

			button("list") {
				backgroundColor = Color.parseColor("#FF9999")
				alpha = 0.5f
				// 点击事件
				onClick {
					// anko封装的intent携带值跳转
					startActivity<ListActivity>("aulton" to "aulton")
				}
				// 长按事件
				onLongClick {
					toast("Long Click")
				}
			}.lparams(dip(150), dip(50))

			button("setting") {
				backgroundColor = Color.parseColor("#FF7777")
				alpha = 0.5f
				// 点击事件
				onClick {
					// anko封装的intent携带值跳转
					startActivity<SettingActivity>("aulton" to "aulton")
				}
			}.lparams(dip(250), dip(50)) {
				topMargin = dip(16)
			}

			button("custom_view") {
				backgroundColor = Color.parseColor("#FF7777")
				alpha = 0.5f
				// 点击事件
				onClick {
					// anko封装的intent携带值跳转
					startActivity<CustomCircleActivity>("aulton" to "aulton")
				}
			}.lparams(dip(250), dip(50)) {
				topMargin = dip(16)
			}

		}
	}
}

