package com.simple.app.repository.net.ext

import com.simple.app.constant.Constants
import com.simple.app.repository.net.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * @author: yangkui
 * @Date: 2022/4/15
 * @Description: RetrofitFactory
 */
object RetrofitFactory {
    private const val connectTimeout = 10 //s,连接超时
    private const val readTimeout = 10 //s,读取超时
    private const val writeTimeout = 10 //s,写超时


    private var apiService: ApiService? = null//api服务器

    //api服务器
    fun getApiService(): ApiService {
        if (apiService == null) {
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(connectTimeout.toLong(), TimeUnit.SECONDS)
            builder.readTimeout(readTimeout.toLong(), TimeUnit.SECONDS)
            builder.writeTimeout(writeTimeout.toLong(), TimeUnit.SECONDS)
            builder.retryOnConnectionFailure(true) //出现错误时会重新发送请求
            builder.addInterceptor(RequestInterceptor())
            builder.addInterceptor(LogInterceptor())
            val retrofit: Retrofit = Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService!!
    }
}