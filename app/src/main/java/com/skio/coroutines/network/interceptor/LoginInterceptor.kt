package com.skio.coroutines.network.interceptor

import com.google.gson.Gson
import com.skio.coroutines.base.AppManager
import com.skio.coroutines.utils.ClientUtils
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull


/**
 * 包含token,header,错误处理
 *
 */


class LoginInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder()
        //请求头
        newBuilder.header("operatorSystem", ClientUtils.getOs() + ClientUtils.getOsVersion())
                .header("appVersion",
                  appVersion
                )
          .header("fromType", "2")
           .header("phoneModels", ClientUtils.getModel())

        //2 Form数据转json
//        if (request.method == "POST") {
//            val body = request.body!!
//            if (body is FormBody) {
//                val map = mutableMapOf<String, String>()
//
//                if (!form2jsonInterceptor.any { return@any it.contains(request.url.toString()) }) {
//                    for (i in 0 until body.size) {
//                        val name = body.name(i)
//                        val value = body.value(i)
//                        map[name] = value
//                    }
//                    if (map.isNotEmpty()) {
//                        val newRequestBody = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), Gson().toJson(map))
//                        newBuilder.method("POST", newRequestBody)
//                    }
//                }
//            }
//        }
        return chain.proceed(newBuilder.build())
    }

    companion object {
        @JvmField
        var token: String = ""

        @JvmField
        val appVersion = AppManager.appVersion

        //如果你的form数据不想转成json,必须在此配置添加,添加项不必包含host
        private val form2jsonInterceptor = arrayListOf<String>()
    }
}
