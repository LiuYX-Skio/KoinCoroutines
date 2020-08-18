package com.skio.coroutines.base

/**
 * @author kuky.
 * @description
 */

data class BaseResultData<T>(
    val `result`: T,
    val code: Int,
    val success: Boolean
)
