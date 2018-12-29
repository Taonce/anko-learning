package com.taonce.myanko.anko.custom

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.taonce.myanko.R
import org.jetbrains.anko.*


/**
 * Author: taoyongxiang
 * Date: 2018/12/29
 * Project: MyToast
 * Desc:
 * Copyright (C) 2018 Aulton. All rights reserved.
 */

class SettingActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		SettingUI().setContentView(this)
	}
}

class SettingUI : AnkoComponent<SettingActivity> {
	override fun createView(ui: AnkoContext<SettingActivity>) = with(ui) {
		verticalLayout {
			myLinearLayout(viewManager = this,
					headImageId = R.mipmap.setting,
					headTextRes = "Setting",
					bottomImageId = R.mipmap.arrow,
					itemMarginBottom = 8,
					itemMarginTop = 8)
			myLinearLayout(viewManager = this,
					headTextRes = "MyInfo",
					bottomImageId = R.mipmap.arrow,
					itemMarginBottom = 8)
			myLinearLayout(this,
					headTextRes = "Exit",
					headImageId = R.mipmap.exit,
					bottomImageId = R.mipmap.arrow)
		}
	}
}