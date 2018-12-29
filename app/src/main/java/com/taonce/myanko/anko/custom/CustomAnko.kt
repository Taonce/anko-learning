package com.taonce.myanko.anko.custom

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewManager
import android.widget.ImageView
import android.widget.LinearLayout
import com.taonce.myanko.anko.view.CustomCircle
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView


/**
 * Author: taoyongxiang
 * Date: 2018/12/29
 * Project: MyToast
 * Desc:
 * Copyright (C) 2018 Aulton. All rights reserved.
 */

/**
 * 自定义一种anko view
 */
fun myLinearLayout(viewManager: ViewManager,
				   itemHeight: Int = 40,
				   itemMarginTop: Int = 0,
				   itemMarginBottom: Int = 0,
				   headImageId: Int = 0,
				   headTextRes: String,
				   bottomImageId: Int = 0) = with(viewManager) {
	linearLayout {
		orientation = LinearLayout.HORIZONTAL
		leftPadding = dip(16)
		rightPadding = dip(16)
		backgroundColor = Color.parseColor("#FFFFFF")
		// 设置整体的宽高和外边距
		lparams(width = matchParent, height = dip(itemHeight)) {
			setMargins(0, itemMarginTop, 0, itemMarginBottom)
		}
		// 左边图片
		if (headImageId != 0) {
			imageView(headImageId) {
				scaleType = ImageView.ScaleType.FIT_XY
			}.lparams(width = dip(30), height = dip(30)) {
				gravity = Gravity.CENTER
			}
		}
		// 左边字体
		textView(headTextRes) {
			gravity = Gravity.CENTER_VERTICAL
		}.lparams(width = matchParent, height = matchParent, weight = 1f) {
			if (headImageId != 0) {
				marginStart = dip(16)
			}
		}
		// 右边图片
		if (bottomImageId != 0) {
			imageView(bottomImageId) {
				scaleType = ImageView.ScaleType.FIT_XY
			}.lparams(width = dip(30), height = dip(30)) {
				gravity = Gravity.CENTER
			}
		}
	}
}

/**
 * 以下都是为了在anko中实现自定义的CustomCircle，定义的一系列方法
 */
fun ViewManager.customCircle(): CustomCircle = customCircle {}
inline fun ViewManager.customCircle(theme: Int = 0, init: CustomCircle.() -> Unit): CustomCircle {
	return ankoView({ CustomCircle(it) }, theme, init)
}

fun Context.customCircle(): CustomCircle = customCircle {}
inline fun Context.customCircle(theme: Int = 0, init: CustomCircle.() -> Unit): CustomCircle {
	return ankoView({ CustomCircle(it) }, theme, init)
}

fun Activity.customCircle(): CustomCircle = customCircle {}
inline fun Activity.customCircle(theme: Int = 0, init: CustomCircle.() -> Unit): CustomCircle {
	return ankoView({ CustomCircle(it) }, theme, init)
}