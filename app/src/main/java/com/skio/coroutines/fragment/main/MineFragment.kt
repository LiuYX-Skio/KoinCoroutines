package com.skio.coroutines.fragment.main
import com.skio.coroutines.base.*

import android.os.Bundle
import android.util.Log
import android.view.View
import com.skio.coroutines.R
import com.skio.coroutines.databinding.FragmentMineBinding
import com.skio.coroutines.fragment.main.model.MineViewModel
import com.skio.coroutines.utils.ToastUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MineFragment : BaseFragment<FragmentMineBinding>() {
  private val mMineViewModel by sharedViewModel<MineViewModel>()

  override fun getLayoutId(): Int = R.layout.fragment_mine

  override fun initFragment(view: View, savedInstanceState: Bundle?) {
    mBinding?.run {
      mineFragment = this@MineFragment
      viewModel = mMineViewModel

    }

  }
  override fun actionsOnViewInflate() {

  }

  fun onGetUser(view: View){
    getUser()
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  private fun getUser() {
    launch {
      mMineViewModel.getUserInfo().catch {
      }.onStart {
      }.onCompletion {

      }.collectLatest {
        if (it == null) return@collectLatest

      }
    }

  }

}
