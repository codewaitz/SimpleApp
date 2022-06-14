package com.simple.app.business.login

import android.lib.common.base.BaseModel
import android.lib.common.base.BaseViewModel
import androidx.lifecycle.MutableLiveData
import com.simple.app.bean.event.LoginPassword
import com.simple.app.manager.LoginManager
import com.simple.app.repository.AppModel
import com.simple.app.repository.data.LoginRep
import com.simple.app.repository.net.HttpListener
import com.simple.app.repository.net.ext.RetrofitFactory

/**
 * @author: yangkui
 * @Date: 2022/4/15
 * @Description: LoginPasswordViewModel
 */
class LoginViewModel : BaseViewModel() {
    private lateinit var model: AppModel
    var loginResult = MutableLiveData<Boolean>()

    override fun createModel(): BaseModel {
        model = AppModel()
        return model
    }

    fun login(phone: String, password: String) {
        if (1 == 1) {
            loginResult.postValue(true)
            return
        }
        model.request(
            RetrofitFactory.getApiService().login(LoginPassword(phone, password)),
            object : HttpListener<LoginRep?>() {
                override fun onLoading() {
                    isLoading.postValue(true)
                }

                override fun onSuccess(result: LoginRep?) {
                    if (result != null) {
                        LoginManager.login(result.token!!, result.userInfo!!)
                        loginResult.postValue(true)
                    }
                }

                override fun onEnd() {
                    super.onEnd()
                    isLoading.postValue(false)
                }
            })
    }
}