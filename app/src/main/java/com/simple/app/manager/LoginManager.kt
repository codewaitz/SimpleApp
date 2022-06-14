package com.simple.app.manager

import android.lib.common.local.mmkv.KV
import android.lib.common.utils.StringUtil
import com.google.gson.Gson
import com.simple.app.bean.User
import com.simple.app.constant.Key

/**
 * @author: yangkui
 * @Date: 2022/4/18
 * @Description: UserManager
 */
object LoginManager {
    var user: User? = null // 当前用户
    var token: String = "" // 当前token

    // 判断是否登录
    fun isLogin(): Boolean {
        return user != null && token != null && token.isNotEmpty()
    }

    // 登录用户
    fun login(token: String, user: User) {
        LoginManager.token = token
        LoginManager.user = user
        try {
            KV.encode(Key.TOKEN, token)
            saveUser() // 保存User
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    // 本地登录
    fun restoreLogin() {
        try {
            token = KV.decodeString(Key.TOKEN).toString()
            var userJson = KV.decodeString(Key.LOGIN_USER)
            user = if (StringUtil.isEmpty(userJson)) {
                null
            } else {
                Gson().fromJson(userJson, User::class.java)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    // 本地保存当前User
    fun saveUser() {
        try {
            KV.encode(Key.LOGIN_USER, Gson().toJson(user))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    // 登出
    fun logout() {
        user = null
        token = ""
        KV.removeKey(Key.TOKEN)
        KV.removeKey(Key.LOGIN_USER)
    }
}