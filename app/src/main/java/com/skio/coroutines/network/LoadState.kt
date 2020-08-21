package com.skio.coroutines.network


/**
 * @author LiuYX
 *
 *
 * @desc 网络加载状态
 */
sealed class LoadState {
    object Idle : LoadState()
    object Loading : LoadState()
    object Succeed : LoadState()
    class Failed(val error: Throwable) : LoadState()
}
