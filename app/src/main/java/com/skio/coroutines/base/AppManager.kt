package com.skio.coroutines.base

import android.app.Application
import android.content.Context
import com.skio.coroutines.utils.ClientUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppManager : Application() {
  override fun onCreate() {
    super.onCreate()
    instance = applicationContext
    appVersion= ClientUtils.getAppVersion(this)
    startKoin {
      androidLogger(Level.ERROR)
      androidContext(this@AppManager)
      androidFileProperties()
      modules(
        dataSourceModule, repositoryModule, viewModelModule,
        fragmentModule, dialogModule, adapterModule
      )
    }
  }

  companion object {
    lateinit var instance: Context
    @JvmStatic
    var appVersion = ""
  }

}
