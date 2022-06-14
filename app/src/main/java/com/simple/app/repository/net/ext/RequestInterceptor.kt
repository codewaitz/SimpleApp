package com.simple.app.repository.net.ext

import android.lib.common.utils.StringUtil
import com.simple.app.manager.LoginManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author: yangkui
 * @Date: 2022/4/8
 * @Description: RequestInterceptor
 */

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        // 添加公共请求头
        builder.addHeader("dreamshop-api-version", "1.0")
        builder.addHeader("port", "Android")
        if (LoginManager.isLogin() && !StringUtil.isEmpty(LoginManager.token)) {
            builder.addHeader("access-token", LoginManager.token)
        }
        // GET请求，添加查询参数
        if (request.method() == "GET") {
            var httpUrl = request.url().newBuilder()
            builder.url(httpUrl.build())
        }
        return chain.proceed(builder.build())
    }
}