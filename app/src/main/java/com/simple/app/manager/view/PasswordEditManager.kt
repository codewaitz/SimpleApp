package com.simple.app.manager.view

import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import com.simple.app.R

/**
 * @author: yangkui
 * @Date: 2022/4/14
 * @Description: 密码管理器
 */
class PasswordEditManager(private val etPassword: EditText, private val ivPasswordEye: ImageView) {
    private var isShow = false // 密码显示状态
    fun manager() {
        // 初始化当前状态
        isShow =
            if (etPassword.inputType != InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                ivPasswordEye.setImageResource(R.drawable.icon_login_password_visible)
                false
            } else {
                ivPasswordEye.setImageResource(R.drawable.icon_login_password_unvisible)
                true
            }
        // 监听
        ivPasswordEye.setOnClickListener {
            showOrHide()
        }
    }

    /**
     * 密码显示或隐藏 （切换）
     */
    private fun showOrHide() {
        //记住光标开始的位置
        val pos = etPassword.selectionStart
        if (isShow) {
            etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            ivPasswordEye.setImageResource(R.drawable.icon_login_password_visible)
        } else {
            etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            ivPasswordEye.setImageResource(R.drawable.icon_login_password_unvisible)
        }
        isShow = !isShow
        etPassword.setSelection(pos)
    }
}