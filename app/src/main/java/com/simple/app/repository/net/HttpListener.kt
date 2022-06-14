package com.simple.app.repository.net

/**
 * @author: yangkui
 * @Date: 2022/4/13
 * @Description: 请求监听
 */
abstract class HttpListener<T> {
    open fun onLoading() {} // 加载中
    abstract fun onSuccess(result: T?) // 成功
    open fun onError(errorMsg: String?, code: String?, data: Object?) {} // 失败
    open fun onEnd() {} // 结束
}