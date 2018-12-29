package com.taonce.myanko.vm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel


/**
 * Author: taoyongxiang
 * Date: 2018/12/26
 * Project: MyToast
 * Desc:
 * Copyright (C) 2018 Aulton. All rights reserved.
 */

class SampleModel : ViewModel() {
	val profileData = MutableLiveData<UserInfo>()
	var profile: UserInfo? = null

	override fun onCleared() {
		super.onCleared()
		profile = null
	}

	fun getUserInfo(){
		profileData.postValue(profile)
	}


}