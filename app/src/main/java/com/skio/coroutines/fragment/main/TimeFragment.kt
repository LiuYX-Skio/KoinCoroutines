package com.skio.coroutines.fragment.main
import com.skio.coroutines.base.*

import android.os.Bundle
import android.view.View
import com.skio.coroutines.R
import com.skio.coroutines.databinding.FragmentTimeBinding

class TimeFragment : BaseFragment<FragmentTimeBinding>() {
  override fun getLayoutId(): Int = R.layout.fragment_time

  override fun initFragment(view: View, savedInstanceState: Bundle?) {
  }
}
