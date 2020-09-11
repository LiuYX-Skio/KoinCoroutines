package com.skio.coroutines.entity.user

import com.squareup.moshi.JsonClass

data class UserInfoResp(
    val response: UserInfoEntity = UserInfoEntity()
)
