package com.simple.app.repository.net.ext

import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

/*
*  Observable扩展
*/
fun <T> Observable<T>.execute(observer: BaseObserver<T>) {
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(observer)
}

/*
*  返回数据转换
*/
fun <T> Observable<BaseRep<T>>.covert(): Observable<T> {
    return this.flatMap { t ->
        if (t.code == "Success") {
            try {
                t.resultBean = Gson().fromJson(t.data, t.resultBeanType)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            Observable.just(t.resultBean)
        } else {
            Observable.error(BaseException(t.code, t.msg, t.data))
        }
    }
}