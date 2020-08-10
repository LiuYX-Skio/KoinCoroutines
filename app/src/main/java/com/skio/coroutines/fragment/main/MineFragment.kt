package com.skio.coroutines.fragment.main
import com.skio.coroutines.base.*

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.skio.coroutines.R
import com.skio.coroutines.databinding.FragmentMineBinding
import com.skio.coroutines.entity.user.UserInfoEntity
import com.skio.coroutines.fragment.main.model.MineViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MineFragment : BaseFragment<FragmentMineBinding>() {
  private val mMineViewModel by sharedViewModel<MineViewModel>()
  private var mUserJob: Job? = null

  override fun getLayoutId(): Int = R.layout.fragment_mine

  override fun initFragment(view: View, savedInstanceState: Bundle?) {
    getUser()
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  private fun getUser() {
    launch {
      mMineViewModel.getUserInfo().catch {
        Log.w("数据返回","====错误")

      }.onStart {
        Log.w("数据返回","====开始")

      }.onCompletion {
        Log.w("数据返回","====完成")

      }.collectLatest {
        if (it == null) return@collectLatest
        Log.w("数据返回","===="+it.result)

      }
    }

  }

}
