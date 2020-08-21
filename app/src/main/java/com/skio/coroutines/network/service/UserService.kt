package com.lyj.fakepixiv.app.network.service

import com.skio.coroutines.base.BaseResultData
import com.skio.coroutines.base.Constant
import com.skio.coroutines.entity.user.UserInfoEntity
import retrofit2.Call
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
    @GET("/eas/fy/app/shipper/mine/user")
    suspend fun getUserInfo(): BaseResultData<UserInfoEntity>


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
