package com.skio.coroutines.network

import com.skio.coroutines.base.BaseResultData
import com.skio.coroutines.entity.user.UserInfoEntity
import retrofit2.http.*

/**
 * @author kuky.
 * @description
 */
interface ApiService {

  /**
   * 获取账户信息
   */
  @POST("/plan/workuser/get_users_info")
  @FormUrlEncoded
  suspend fun getUserInfo(@Field("userId")userId: String = "2c93167b6cadf9b8016cae01284e0001",
                          @Field("plat_source")plat_source: String = "1001",
                          @Field("token")account: String = "ptta7UBU7fS1WB821euWnwV2nzXpvC5B"): BaseResultData<UserInfoEntity>

}
