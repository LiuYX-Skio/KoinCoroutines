package com.skio.coroutines.base

/**
 * @author kuky.
 * @description
 */

data class BaseResultData<T>(
    val `data`: T,
    val code: Int,
    val message: Boolean
)
