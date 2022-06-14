package com.simple.app.business.web

import android.lib.common.base.BaseActivity
import android.lib.common.utils.StringUtil
import com.simple.app.databinding.ActivityWebBinding
import com.simple.app.manager.view.HeaderTextManager


/**
 * @author: yangkui
 * @Date: 2022/4/21
 * @Description:WebActivity
 */
class WebActivity :
    BaseActivity<ActivityWebBinding>(ActivityWebBinding::inflate) {

    override fun onCreate() {
        HeaderTextManager.center(
            this,
            vb.headerText,
            StringUtil.resetEmpty(intent.getStringExtra("title"), "")
        )
        vb.webView.settings.javaScriptEnabled = true
        webViewLoad() // 加载
    }

    private fun webViewLoad() {
        var url = intent.getStringExtra("url")
        if (!StringUtil.isEmpty(url)) {
            vb.webView.loadUrl(url!!)
        }
    }
}