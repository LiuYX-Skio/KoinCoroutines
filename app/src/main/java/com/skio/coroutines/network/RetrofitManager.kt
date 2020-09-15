package com.skio.coroutines.network

import com.skio.coroutines.network.interceptor.LoginInterceptor
import com.skio.coroutines.utils.jsonPrint
import com.skio.coroutines.utils.kLogger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * @author LiuYX
 * @description
 */
object RetrofitManager {
  private val logger = kLogger<RetrofitManager>()
  private var BASE_URL = "http://www.jihuabiao.net:8890"
  private const val DEFAULT_TIMEOUT :Long= 5
  val apiService: ApiService = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(genericOkClient())
    .build().create(ApiService::class.java)


  private fun genericOkClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(
      object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
          logger.jsonPrint { message }
        }
      })
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
      .connectTimeout(60_000L, TimeUnit.MILLISECONDS)
      .readTimeout(60_000L, TimeUnit.MILLISECONDS)
      .writeTimeout(60_000L, TimeUnit.MILLISECONDS)
      .addInterceptor(LoginInterceptor())
      .addInterceptor(httpLoggingInterceptor)
      .build()
  }
}
