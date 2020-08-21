package com.skio.coroutines.fragment.main
import com.skio.coroutines.base.*

import android.os.Bundle
import com.skio.coroutines.R
import com.skio.coroutines.databinding.FragmentMineBinding
import com.skio.coroutines.fragment.main.model.MineViewModel
import com.skio.coroutines.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : BaseFragment<FragmentMineBinding,MineViewModel?>() {


  override fun init(savedInstanceState: Bundle?) {
    cancel_bt.setOnClickListener {
      ToastUtils.showToast(activity,"正在请求")
      mViewModel?.getUserInfo()
    }
  }


  override fun bindLayout(): Int= R.layout.fragment_mine;



}
