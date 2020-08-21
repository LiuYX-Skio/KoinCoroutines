package com.skio.coroutines.base

/**
 * @author LiuYX.
 * @description
 */

data class BaseResultData<T>(
    val `result`: T,
    val code: Int,
    val success: Boolean
)
