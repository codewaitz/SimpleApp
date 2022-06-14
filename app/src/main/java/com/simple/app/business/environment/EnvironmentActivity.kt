package com.simple.app.business.environment

import android.lib.common.base.BaseActivity
import android.lib.common.local.mmkv.KV
import android.lib.common.manager.AllActivityManager
import com.simple.app.constant.Constants
import com.simple.app.constant.InitData
import com.simple.app.constant.Key
import com.simple.app.manager.LoginManager
import com.simple.app.manager.view.HeaderTextManager
import com.simple.app.R
import com.simple.app.databinding.ActivityEnvironmentBinding
import kotlin.system.exitProcess


/**
 * @author: yangkui
 * @Date: 2022/4/21
 * @Description:EnvironmentActivity
 */
class EnvironmentActivity :
    BaseActivity<ActivityEnvironmentBinding>(ActivityEnvironmentBinding::inflate) {
    private var defaultCheckId = 0

    override fun onCreate() {
        HeaderTextManager.centerRight(
            this,
            vb.headerText,
            getString(R.string.environment),
            getString(R.string.save),
            object :
                HeaderTextManager.OnRightClickListener {
                override fun onRight() {
                    save() // 保存数据
                    AllActivityManager.finishAll()
                    exitProcess(0)
                }
            })
        // 设置当前环境
        defaultCheckId = when (Constants.BASE_URL) {
            InitData.BASE_URL_RELEASE -> {
                vb.rbRelease.id
            }
            InitData.BASE_URL_DEBUG -> {
                vb.rbDebug.id
            }
            else -> {
                vb.rbLocal.id
            }
        }
        vb.rgEnvironment.check(defaultCheckId)
    }

    private fun save() {
        if (vb.rgEnvironment.checkedRadioButtonId != defaultCheckId) {
            LoginManager.logout() // 环境切换登出
        }
        when (vb.rgEnvironment.checkedRadioButtonId) {
            vb.rbRelease.id -> {
                KV.encode(Key.BASE_URL, InitData.BASE_URL_RELEASE)
            }
            vb.rbDebug.id -> {
                KV.encode(Key.BASE_URL, InitData.BASE_URL_DEBUG)
            }
            vb.rbLocal.id -> {
                KV.encode(Key.BASE_URL, InitData.BASE_URL_LOCAL)
            }
        }
    }
}