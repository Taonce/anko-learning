# anko-learning
1. anko的简介和四个库

2. 引入anko和遇到的问题

   引入anko的依赖：`implementation "org.jetbrains.anko:anko:$anko_version"`

   这时发现有爆红的地方了：提示v7包和v4包版本不一致，这就很纳闷了，我都没用私自动v4包，怎么会出现问题了，就去libraries找原因了，原来是anko也引入了v4的包，而且版本是27.1.1，我新建工程的编译版本是28.0.0，小伙伴们要注意了。

   解决：排除anko包中的v4包

   ```kotlin
   implementation("org.jetbrains.anko:anko:$anko_version") {
           exclude module: 'support-v4'
       }
   ```

3. 简单使用anko

   实现一个简单的登录界面
   ![登录](https://upload-images.jianshu.io/upload_images/6297937-d871fcd4f15d120c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
   既然是使用anko，那么当然是要抛弃xml布局文件咯，也就不用写`setContentView()`来绑定布局文件了，可以直接在`onCreate()`方法里面写anko布局。来看看如何实现这么一个简单的布局：
```
class AnkoActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
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

			button("Login") {
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
			}.lparams(dip(200), dip(50))
		}
	}
}
```
可以很明显的看到我们直接在`onCreate()`里面声明了`verticalLayout{}`，那我们就得去看看`verticalLayout`的源码了，看看是如何实现的。