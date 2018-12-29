package com.taonce.myanko.anko.view

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.View
import android.view.ViewManager
import org.jetbrains.anko.custom.ankoView


/**
 * Author: taoyongxiang
 * Date: 2018/12/29
 * Project: MyToast
 * Desc:
 * Copyright (C) 2018 Aulton. All rights reserved.
 */

class CustomCircle : View {

	private lateinit var circlePaint: Paint
	private lateinit var circleRect: RectF
	private lateinit var rectPaint: Paint
	var startAngle: Float = 0f
	var endAngle: Float = 0f
	@ColorInt
	var arcBg: Int = 0
		set(value) {
			field = value
			circlePaint.color = value
		}
	var paintWidth: Float = 1f
		set(value) {
			field = value
			circlePaint.strokeWidth = value
			rectPaint.strokeWidth = value
		}


	constructor(context: Context) : this(context, null)
	constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
	constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
		init()
	}

	/**
	 * 初始化
	 */
	private fun init() {
		circlePaint = Paint()

		rectPaint = Paint()
		rectPaint.color = Color.parseColor("#ff0000")

		circleRect = RectF(0F, 0F, 360F, 360F)
	}

	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)
		canvas.drawRect(circleRect, rectPaint)
		canvas.drawArc(circleRect, startAngle, endAngle - startAngle, true, circlePaint)
	}
}

