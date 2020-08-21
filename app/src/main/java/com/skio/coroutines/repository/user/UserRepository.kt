package com.skio.coroutines.repository.user

import com.lyj.fakepixiv.app.network.service.UserService
import com.skio.coroutines.entity.user.UserInfoEntity
import com.skio.coroutines.network.RetrofitManager
import kotlinx.coroutines.*

/**
 * @author greensun
 *
 * @date 2019/4/10
 *
 * @desc
 */
class UserRepository private constructor(){

    val service: UserService by lazy { RetrofitManager.instance.userService }

    companion object {
        @JvmStatic
        val instance: UserRepository by lazy { UserRepository() }
    }

  /**
   * 获取用户信息
   */
  suspend fun getUserInfo(): UserInfoEntity {
    return withContext(Dispatchers.IO) {
      val resp = service.getUserInfo().result
      resp
    }
  }




}
