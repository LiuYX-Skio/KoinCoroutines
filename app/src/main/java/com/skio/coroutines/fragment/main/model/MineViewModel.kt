package com.skio.coroutines.fragment.main.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.flow

class MineViewModel(
  private val repository: MineRepository
) : ViewModel() {
  fun getUserInfo() = flow {
    emit(repository.getUserInfo())
  }


}
