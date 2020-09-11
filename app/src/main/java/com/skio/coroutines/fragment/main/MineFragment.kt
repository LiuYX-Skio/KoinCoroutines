package com.skio.coroutines.fragment.main

import android.os.Bundle
import android.util.Log
import com.skio.coroutines.R
import com.skio.coroutines.base.BaseFragment
import com.skio.coroutines.databinding.CommonRefreshList
import com.skio.coroutines.fragment.main.model.MineViewModel
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MineFragment() : BaseFragment<CommonRefreshList,MineViewModel>() {

  override var mViewModel: MineViewModel = MineViewModel()

  override fun init(savedInstanceState: Bundle?) {
    cancel_bt.setOnClickListener {
      initData()

    }
  }

  fun initData(){
    mViewModel?.getUserInfo()

  }


  override fun bindLayout(): Int= R.layout.fragment_mine;



}
