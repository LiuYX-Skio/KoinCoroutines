package com.skio.coroutines.fragment.main
import com.skio.coroutines.base.*
import android.os.Bundle
import com.skio.coroutines.R
import com.skio.coroutines.databinding.FragmentChallengeBinding
import com.skio.coroutines.fragment.main.model.MineViewModel

class ChallengeFragment() : BaseFragment<FragmentChallengeBinding,BaseViewModel?>() {

  override var mViewModel: BaseViewModel?=null

  override fun bindLayout(): Int = R.layout.fragment_challenge
  override fun init(savedInstanceState: Bundle?) {
  }


}
