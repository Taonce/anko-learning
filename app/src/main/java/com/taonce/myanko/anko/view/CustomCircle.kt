package com.taonce.myanko.anko.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.View


/**
 * Author: taoyongxiang
 * Date: 2018/12/29
 * Project: MyToast
 * Desc: 自定义view，在anko的DSL语言中实现
 */

class CustomCircle : View {

	private var circlePaint: Paint? = null
	private var circleRect: RectF? = null
	private var rectPaint: Paint? = null
	// 圆弧开始的角度
	var startAngle: Float = 0f
	// 圆弧结束的角度
	var endAngle: Float = 0f
	// 圆弧的背景颜色
	@ColorInt
	var arcBg: Int = 0
		set(value) {
			field = value
			circlePaint?.color = value
		}
	// 画笔的宽度
	var paintWidth: Float = 1f
		set(value) {
			field = value
			circlePaint?.strokeWidth = value
			rectPaint?.strokeWidth = value
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
		rectPaint?.color = Color.parseColor("#ff0000")

		circleRect = RectF(0F, 0F, 360F, 360F)
	}

	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)
		canvas.drawRect(circleRect!!, rectPaint!!)
		canvas.drawArc(circleRect!!, startAngle, endAngle - startAngle, true, circlePaint!!)
	}
}

