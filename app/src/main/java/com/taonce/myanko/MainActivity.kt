package com.taonce.myanko

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.http.GET
import java.io.IOException
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

	@SuppressLint("ResourceType")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		verticalLayout {
			button("volley") {
				onClick {
					Snackbar.make(this@button, "snack_bar", Snackbar.LENGTH_SHORT).show()
					useVolley()
				}
			}
			button("okhttp3") {
				onClick { getNetWork() }
			}
			button("retrofit") {
				onClick { useRetrofit() }
			}
			button("alertDialog") {
				onClick {
					alert {
						title = "Dialog"
						message = "AlertDialog"
						okButton { toast("dialog positive") }
						cancelButton { toast("dialog negative") }
						show()
					}
				}
			}
		}

	}

	/**
	 * 使用OKHttp3进行网络请求
	 */
	private fun getNetWork() = runBlocking {
		val okHttpClient = OkHttpClient()
		val request = Request.Builder()
				.url("https://www.baidu.com")
				.get()
				.build()
		val call = okHttpClient.newCall(request)
		GlobalScope.launch(Dispatchers.IO) {
			call.clone().enqueue(object : Callback {
				override fun onFailure(call: Call, e: IOException) {
					GlobalScope.launch(Dispatchers.Main) {
						toast("request is failed, exception is $e")
					}
				}

				override fun onResponse(call: Call, response: Response) {
					val result = response.body()?.string()
					GlobalScope.launch(Dispatchers.Main) {
						toast("request result is $result")
					}
				}
			})
		}
	}

	/**
	 * 使用Volley进行网络请求
	 */
	private fun useVolley() {
		val requestQueue = Volley.newRequestQueue(this, null)
		val request = StringRequest(com.android.volley.Request.Method.GET,
				"https://www.baidu.com",
				Listener<String> {
					toast("volley is success")
					Log.d("aulton", "volley success is $it")
				},
				ErrorListener {
					toast("volley is failed")
					Log.d("aulton", "volley error is ${it.message}")
				})
		request.retryPolicy = DefaultRetryPolicy(30 * 1000, 0, 0f)
		requestQueue.add(request)
	}

	/**
	 * 使用Retrofit2进行网络请求，并将结果转换为String类型
	 */
	private fun useRetrofit() {
		val retrofit = Retrofit.Builder()
				.baseUrl("https://www.baidu.com/")
				.addConverterFactory(object : Converter.Factory() {
					override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
						return Converter<ResponseBody, String> {
							return@Converter it.string()
						}
					}
				})
				.build()
		val service = retrofit.create(RetrofitService::class.java)
		service.getBaidu().enqueue(object : retrofit2.Callback<String> {
			override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {
				Log.d("aulton", "retrofit failed msg is ${t.message}")
			}

			override fun onResponse(call: retrofit2.Call<String>, response: retrofit2.Response<String>) {
				Log.d("aulton", "retrofit success result is ${response.body()}")
			}
		})
	}
}

interface RetrofitService {
	@GET(".")
	fun getBaidu(): retrofit2.Call<String>
}
