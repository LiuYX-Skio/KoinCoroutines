package com.skio.coroutines.fragment.main

import android.os.Bundle
import android.util.Log
import com.skio.coroutines.R
import com.skio.coroutines.base.BaseFragment
import com.skio.coroutines.databinding.CommonRefreshList
import com.skio.coroutines.fragment.main.model.MineViewModel
import com.skio.coroutines.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : BaseFragment<CommonRefreshList,MineViewModel?>() {


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
