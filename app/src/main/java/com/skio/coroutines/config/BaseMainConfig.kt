package com.skio.coroutines.config

import com.skio.coroutines.R
import com.skio.coroutines.entity.BottomItem


object BaseMainConfig {


  fun getBottomData(): List<BottomItem> {
    val items: MutableList<BottomItem> = ArrayList()
    items.add(BottomItem("倒计时", R.mipmap.time_unselect))
    items.add(BottomItem("挑战营", R.mipmap.time_unselect))
    items.add(BottomItem("我的", R.mipmap.time_unselect))
    return items
  }

}
