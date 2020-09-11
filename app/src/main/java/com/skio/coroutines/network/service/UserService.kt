package com.skio.coroutines.network.service

import com.google.gson.JsonElement
import com.skio.coroutines.base.BaseResultData
import com.skio.coroutines.base.Constant
import com.skio.coroutines.entity.user.UserInfoEntity
import com.skio.coroutines.entity.user.UserInfoResp
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

/**
 * @author LiuYX
 *
 * @desc
 */
interface UserService {



    // https://accounts.pixiv.net/api/account/edit
    // new_mail_address=812194178%40qq.com&
    // new_user_account=liaoyijian&current_password=3jeaTcVEUB&new_password=liaolove1314
    // new_user_account=beiyeqingyang&current_password=liaolove1314
    // https://app-api.pixiv.net/v1/user/me/state

    /**
     * 获取账户信息
     */
    @POST("/plan/workuser/get_users_info")
    @FormUrlEncoded
    suspend fun getUserInfo(@Field("userId")userId: String = "2c93167b6cadf9b8016cae01284e0001",
                            @Field("plat_source")plat_source: String = "1001",
                            @Field("token")account: String = "ptta7UBU7fS1WB821euWnwV2nzXpvC5B"): BaseResultData<UserInfoEntity>


    /**
     * 修改账户信息
     */
    @POST("/api/account/edit")
    @Headers("SWITCH-HEADER:TAG_ACCOUNT")
    @FormUrlEncoded
    suspend fun editAccount(@Field("new_mail_address")mail: String = "",
                            @Field("new_user_account")account: String = "",
                            @Field("current_password")current_password: String = "",
                            @Field("new_password")new_password: String = ""): Any


}
