package com.simple.app

import android.app.Application
import android.lib.common.base.BaseApplication

class NApplication : BaseApplication() {
    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}