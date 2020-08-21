package com.skio.coroutines.fragment.main
import com.skio.coroutines.base.*

import android.os.Bundle
import android.view.View
import com.skio.coroutines.R
import com.skio.coroutines.databinding.FragmentTimeBinding
import com.skio.coroutines.fragment.main.model.MineViewModel

class TimeFragment() : BaseFragment<FragmentTimeBinding,MineViewModel?>() {
  override fun bindLayout(): Int = R.layout.fragment_time
  override fun init(savedInstanceState: Bundle?) {
  }

}
