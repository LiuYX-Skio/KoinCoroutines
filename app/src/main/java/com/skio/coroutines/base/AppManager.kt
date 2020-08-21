package com.skio.coroutines.base

import android.app.Application
import android.content.Context
import com.skio.coroutines.utils.ClientUtils

class AppManager : Application() {
  override fun onCreate() {
    super.onCreate()
    instance = applicationContext
    appVersion= ClientUtils.getAppVersion(this)
  }

  companion object {
    lateinit var instance: Context
    @JvmStatic
    var appVersion = ""
  }

}
