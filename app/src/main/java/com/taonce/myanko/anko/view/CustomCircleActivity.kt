package com.taonce.myanko.anko.view

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
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
		relativeLayout {
			gravity = Gravity.CENTER
			lparams(width = 1000, height = 1000)
			backgroundColor = Color.parseColor("#99FF99")
			customCircle {
				startAngle = 0f
				endAngle = 270f
				arcBg = Color.parseColor("#FF9999")
				paintWidth = 20f
			}
		}
	}
}