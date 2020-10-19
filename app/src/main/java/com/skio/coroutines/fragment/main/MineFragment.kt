package com.skio.coroutines.fragment.main

import android.os.Bundle
import android.util.Log
import android.view.View
import com.skio.coroutines.R
import com.skio.coroutines.base.BaseFragment
import com.skio.coroutines.customview.RadiosAnimalButtonView
import com.skio.coroutines.databinding.FragmentMineBinding
import com.skio.coroutines.fragment.main.model.MineViewModel
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
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
//    getUser()
  }
  fun onGetButton(view: View){
    radios_button.resetView()
//    getUser()
  }
  fun onGetSwitch(view: View){
    if(radios_button.buttonType==1){
      radios_button.buttonType = 2
    }else{
      radios_button.buttonType = 1
    }

//    getUser()
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  private fun getUser() {
    launch {
      mMineViewModel.getUserInfo().catch {

      }.onStart {
      }.onCompletion {

      }.collectLatest {
        if (it == null) return@collectLatest
        Log.w("数据返回", "==" + it.data?.toString())

      }
    }

  }

}
