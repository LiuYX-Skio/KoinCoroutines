package com.skio.coroutines.fragment.main.model

import androidx.databinding.ObservableField
import com.skio.coroutines.base.BaseViewModel
import com.skio.coroutines.network.LoadState
import com.skio.coroutines.repository.user.UserRepository
import com.skio.coroutines.utils.LogUtils
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MineViewModel : BaseViewModel() {
  var loadState: ObservableField<LoadState> = ObservableField(LoadState.Idle)

  fun getUserInfo() {
    launch(CoroutineExceptionHandler { _, err ->
      loadState.set(LoadState.Failed(err))
    }) {
      loadState.set(LoadState.Loading)
      var resp=UserRepository.instance
        .getUserInfo()
      LogUtils.warnInfo(resp.toString())
      loadState.set(LoadState.Succeed)
    }
  }



}
