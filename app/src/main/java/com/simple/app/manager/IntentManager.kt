package com.simple.app.manager

import android.content.Context
import android.content.Intent
import com.simple.app.business.environment.EnvironmentActivity
import com.simple.app.business.launch.LaunchActivity
import com.simple.app.business.web.WebActivity

/**
 * @author: yangkui
 * @Date: 2022/4/14
 * @Description: 意图管理
 */
object IntentManager {
    // 启动
    fun startLaunch(context: Context) {
        context.startActivity(Intent(context, LaunchActivity::class.java))
    }

    // web title=标题 url=地址
    fun startWeb(context: Context, title: String, url: String) {
        context.startActivity(
            Intent(
                context,
                WebActivity::class.java
            ).putExtra("title", title).putExtra("url", url)
        )
    }

    // 环境
    fun startEnvironment(context: Context) {
        context.startActivity(Intent(context, EnvironmentActivity::class.java))
    }
}