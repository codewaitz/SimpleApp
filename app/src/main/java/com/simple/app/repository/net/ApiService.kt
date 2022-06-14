package com.simple.app.repository.net

import com.simple.app.bean.event.LoginPassword
import com.simple.app.repository.data.LoginRep
import com.simple.app.repository.net.ext.BaseRep
import io.reactivex.rxjava3.core.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

/**
 * @author: yangkui
 * @Date: 2022/4/14
 * @Description: api service
 */
interface ApiService {

    //密码登录
    @POST("/login")
    fun login(@Body loginPassword: LoginPassword): Observable<BaseRep<LoginRep?>>

    // 上传文件
    @Multipart
    @POST("/dreamApi/pub/upload")
    fun upload(@Part part: MultipartBody.Part): Observable<BaseRep<String?>>
}