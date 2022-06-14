package com.simple.app.repository.net.ext

import android.lib.common.utils.LogUtil
import android.lib.common.utils.StringUtil
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.nio.charset.Charset

/**
 * @author: yangkui
 * @Date: 2022/4/19
 * @Description: log
 */
class LogInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var reqContent = ""
        var bff = Buffer()
        request.body()?.writeTo(bff)
        reqContent = bff.readString(Charset.defaultCharset())
        var response = chain.proceed(request.newBuilder().build())
        var responseBody = response?.peekBody(1024L * 1024L)
        var repContent = responseBody?.string()
        var log = "网络请求==========\n" + request.method() + ": " + request.url()
        if (!StringUtil.isEmpty(reqContent)) log += "\nBody: $reqContent"
        if (!StringUtil.isEmpty(repContent)) log += "\nResult: $repContent"
        LogUtil.log(log)
        return response!!
    }
}
