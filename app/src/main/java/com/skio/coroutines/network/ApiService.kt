package com.skio.coroutines.network

import com.skio.coroutines.base.BaseResultData
import com.skio.coroutines.entity.user.UserInfoEntity
import retrofit2.http.*

/**
 * @author kuky.
 * @description
 */
interface ApiService {

  @GET("/eas/fy/app/shipper/mine/user")
  suspend fun getUserInfo(): BaseResultData<String>

}
