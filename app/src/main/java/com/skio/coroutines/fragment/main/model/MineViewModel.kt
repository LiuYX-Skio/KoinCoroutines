package com.skio.coroutines.fragment.main.model

import android.util.Log
import androidx.databinding.ObservableField
import com.skio.coroutines.base.BaseViewModel
import com.skio.coroutines.network.LoadState
import com.skio.coroutines.repository.user.UserRepository
import com.skio.coroutines.utils.LogUtils
import kotlinx.coroutines.*

class MineViewModel : BaseViewModel() {
  var loadState: ObservableField<LoadState> = ObservableField(LoadState.Idle)

  fun getUserInfo() {

    launch(CoroutineExceptionHandler { _, err ->
      loadState.set(LoadState.Failed(err))
      Log.w("接口调用","666"+err.message)

    }) {

      loadState.set(LoadState.Loading)
      var resp=UserRepository.instance
        .getUserInfo()
      Log.w("接口调用","888"+resp.data)

      loadState.set(LoadState.Succeed)
    }
  }



}
