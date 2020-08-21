package com.skio.coroutines.network.interceptor

import com.skio.coroutines.base.Constant
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @author LiuYX
 *
 *
 * @desc 用于切换baseUrl的拦截器
 */
class SwitchBaseUrlInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
            val oldReq = chain.request()
            val headers = oldReq.headers.toMultimap()
            val header = headers[Constant.Net.SWITCH_HEADER]
            if (header!= null) {
                val urlStr = when (header[0]) {
                    Constant.Net.TAG_AUTH -> Constant.Net.AUTH_URL
                    Constant.Net.TAG_ACCOUNT -> Constant.Net.ACCOUNT_URL
                    Constant.Net.TAG_EXT -> Constant.Net.EXT_URL
                    else -> Constant.Net.BASE_URL
                }
                val url = urlStr.toHttpUrlOrNull()
                if (url != null) {
                    val newUrl = oldReq.url
                            .newBuilder()
                            .scheme(url.scheme)
                            .host(url.host)
                            .port(url.port)
                            .build()
                    val newReq = oldReq.newBuilder()
                            .removeHeader(Constant.Net.SWITCH_HEADER)
                            .url(newUrl)
                            .build()
                    return chain.proceed(newReq)
                }
            }
        return chain.proceed(oldReq)
    }
}
