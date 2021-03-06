package com.skio.coroutines.base

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.skio.coroutines.utils.KLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @author LiuYX
 * @description Activity 基类
 */
abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity(),
  CoroutineScope by MainScope(),
  KLogger {

  protected val mBinding: VB by lazy {
    DataBindingUtil.setContentView(this, getLayoutId()) as VB
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    ActivityStackManager.addActivity(this)
    if (needTransparentStatus()) transparentStatusBar()
    mBinding.lifecycleOwner = this
    initActivity(savedInstanceState)
  }

  override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
    if (needFitDarkMode())
      when (newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        Configuration.UI_MODE_NIGHT_NO -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
      }
  }

  override fun onDestroy() {
    super.onDestroy()
    ActivityStackManager.removeActivity(this)
    cancel()
    mBinding.unbind()
  }

  /** 透明状态栏 */
  open fun transparentStatusBar() {
    window.decorView.systemUiVisibility =
      View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    window.statusBarColor = Color.TRANSPARENT
    supportActionBar?.hide()
  }


  open fun replaceFragment(
    manager: FragmentManager,
    to: Fragment,
    resId: Int
  ) {
    Log.w("数据返回qqqq","=====")
    val transaction = manager.beginTransaction()
    //遍历隐藏所有添加的fragment
    for (fragment in manager.fragments) {
      transaction.hide(fragment!!)
    }
    if (!to.isAdded) { //若没有添加过
      transaction.add(resId, to).addToBackStack(null).commit()
    } else { //若已经添加
      transaction.show(to).commit()
    }
  }

  abstract fun getLayoutId(): Int

  abstract fun initActivity(savedInstanceState: Bundle?)

  protected open fun needTransparentStatus(): Boolean = true

  protected open fun needFitDarkMode(): Boolean = true
}
