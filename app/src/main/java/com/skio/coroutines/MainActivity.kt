package com.skio.coroutines

import android.os.Bundle
import com.skio.coroutines.base.BaseActivity
import com.skio.coroutines.config.BaseMainConfig
import com.skio.coroutines.customview.BottomMenuView.BottomItemOnClickListener
import com.skio.coroutines.databinding.ActivityMainBinding
import com.skio.coroutines.fragment.main.ChallengeFragment
import com.skio.coroutines.fragment.main.MineFragment
import com.skio.coroutines.fragment.main.TimeFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<ActivityMainBinding>() {

  private val list by lazy{listOf(TimeFragment(),ChallengeFragment(),MineFragment())}

  override fun getLayoutId(): Int = R.layout.activity_main
  override fun initActivity(savedInstanceState: Bundle?) {
    bottom_switch.setBottomItem(BaseMainConfig.getBottomData())
    //监听点击事件
    bottom_switch.bottomItemOnClickListener = BottomItemOnClickListener { _, i, _ ->
      replaceFragment(supportFragmentManager,list[i],R.id.content_lay)
    }
    //默认选择第几个
    bottom_switch.setShowIndex(0)
  }



}
