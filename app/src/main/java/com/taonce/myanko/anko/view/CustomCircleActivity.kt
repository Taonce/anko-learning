package com.taonce.myanko.anko.view

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.LinearLayout
import com.taonce.myanko.anko.custom.customCircle
import org.jetbrains.anko.*


class CustomCircleActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		CustomCircleUI().setContentView(this@CustomCircleActivity)
	}
}

class CustomCircleUI : AnkoComponent<CustomCircleActivity> {
	override fun createView(ui: AnkoContext<CustomCircleActivity>) = with(ui) {
		linearLayout {
			orientation = LinearLayout.VERTICAL
			gravity = Gravity.CENTER
			lparams(matchParent, matchParent)
			verticalLayout {
				lparams(width = dip(200), height = dip(200))
				backgroundColor = Color.parseColor("#ff9999")
				customCircle {
					startAngle = 0f
					endAngle = 180f
					arcBg = Color.WHITE
					paintWidth = 2f
				}
			}

		}
	}
}