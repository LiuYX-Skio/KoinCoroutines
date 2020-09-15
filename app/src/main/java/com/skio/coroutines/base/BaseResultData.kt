package com.skio.coroutines.base

/**
 * @author LiuYX
 * @description
 */

data class BaseResultData<T>(
    val `data`: T,
    val code: Int,
    val message: Boolean
)
