package com.simple.app.business.launch

import android.lib.common.base.BaseActivity
import android.lib.common.local.mmkv.KV
import com.simple.app.constant.Key.FIRST_SKIP
import com.simple.app.databinding.ActivityLaunchBinding
import com.simple.app.manager.LoginManager

class LaunchActivity : BaseActivity<ActivityLaunchBinding>(ActivityLaunchBinding::inflate) {
    override fun prepareCreate() {
        LoginManager.restoreLogin()
        var firstSkip = KV.decodeBooleanTrue(FIRST_SKIP)
        var isLogin = LoginManager.isLogin()
        if (firstSkip == false || isLogin) {
            startMain()
        }
    }

    override fun onCreate() {
        startMain()
    }

    private fun startLogin() {
    }

    private fun startMain() {
    }
}