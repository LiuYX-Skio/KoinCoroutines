package com.skio.coroutines.fragment.main
import com.skio.coroutines.base.*
import android.os.Bundle
import android.view.View
import com.skio.coroutines.R
import com.skio.coroutines.databinding.FragmentChallengeBinding

class ChallengeFragment : BaseFragment<FragmentChallengeBinding>() {
  override fun getLayoutId(): Int = R.layout.fragment_challenge

  override fun initFragment(view: View, savedInstanceState: Bundle?) {
  }
}
