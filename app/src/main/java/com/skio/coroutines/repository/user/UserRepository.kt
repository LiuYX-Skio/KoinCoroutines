package com.skio.coroutines.repository.user

import com.skio.coroutines.base.BaseResultData
import com.skio.coroutines.entity.user.UserInfoEntity
import com.skio.coroutines.network.RetrofitManager
import com.skio.coroutines.network.service.UserService
import kotlinx.coroutines.*

/**
 * @author LiuYX
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
  suspend fun getUserInfo(): BaseResultData<UserInfoEntity> {

    return withContext(Dispatchers.IO) {
        service.getUserInfo("2c93167b6cadf9b8016cae01284e0001","1001","ptta7UBU7fS1WB821euWnwV2nzXpvC5B")
    }

  }




}
