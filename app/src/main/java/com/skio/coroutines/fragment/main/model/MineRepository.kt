package com.skio.coroutines.fragment.main.model

import com.skio.coroutines.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author LiuYX
 * @description
 */
class MineRepository(private val api: ApiService) {

  suspend fun getUserInfo() =
    withContext(Dispatchers.IO) {
      api.getUserInfo()
    }


}
