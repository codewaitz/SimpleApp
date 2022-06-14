package com.simple.app.repository.net.ext

import android.lib.common.utils.ResourceUtil
import android.lib.common.widget.toast.XToast
import com.simple.app.R
import com.simple.app.constant.Constants
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * @author: yangkui
 * @Date: 2022/4/15
 * @Description:BaseObserver
 */
open class BaseObserver<T>(private val compositeDisposable: CompositeDisposable) : Observer<T> {
    override fun onSubscribe(disposable: Disposable?) {
        compositeDisposable.add(disposable)
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
        try {
            if (e is BaseException) {
                interceptException(e)
            } else { // 未拦截异常处理
                unInterceptException(e)
            }
        } finally {
            onComplete()
        }
    }

    // 拦截异常处理
    private fun interceptException(e: BaseException) {
        var exceptionList = mutableListOf(
            Constants.FilterCode
        )
        if (!exceptionList.contains(e.errorCode)) {
            XToast.showText(e.errorMsg)
        }
        onError(e)
    }

    // 未拦截异常处理
    private fun unInterceptException(e: Throwable) {
        var errorMsg = ResourceUtil.getString(R.string.exception_unknown)
        when (e) {
            is UnknownHostException -> {
                errorMsg = ResourceUtil.getString(R.string.exception_net)
            }
            is SocketTimeoutException -> {
                errorMsg = ResourceUtil.getString(R.string.exception_timeout)
            }
            is ConnectException -> {
                errorMsg = ResourceUtil.getString(R.string.exception_connection)
            }
            is ParseException,
            is JSONException -> {
                errorMsg = ResourceUtil.getString(R.string.exception_code)
            }
        }
        XToast.showText(errorMsg)
        onError(BaseException("SystemError", errorMsg, ""))
    }

    open fun onError(exception: BaseException) {}

    override fun onComplete() {
    }
}