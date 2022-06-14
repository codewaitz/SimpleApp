package com.simple.app.repository

import android.lib.common.base.BaseModel
import com.simple.app.repository.net.HttpListener
import com.simple.app.repository.net.ext.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * @author: yangkui
 * @Date: 2022/4/14
 * @Description: NetApiModel
 */
class AppModel : BaseModel() {
    // 执行网络请求
    fun <T> request(observable: Observable<BaseRep<T?>>, listener: HttpListener<T>) {
        if (listener != null) listener.onLoading()
        observable.covert().execute(object : BaseObserver<T?>(compositeDisposable) {
            override fun onSubscribe(disposable: Disposable?) {
                super.onSubscribe(disposable)
                compositeDisposable.add(disposable)
            }

            override fun onNext(t: T?) {
                super.onNext(t)
                if (listener != null) listener.onSuccess(t)
            }

            override fun onError(e: BaseException) {
                super.onError(e)
                if (listener != null) listener.onError(e.errorMsg, e.errorCode, null)
            }

            override fun onComplete() {
                super.onComplete()
                if (listener != null) listener.onEnd()
            }
        })
    }
}