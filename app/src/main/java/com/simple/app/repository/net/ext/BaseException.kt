package com.simple.app.repository.net.ext

/**
 * @author: yangkui
 * @Date: 2022/4/15
 * @Description: BaseObserver
 */
data class BaseException(val errorCode: String, val errorMsg: String, val data: String?) :
    Throwable()