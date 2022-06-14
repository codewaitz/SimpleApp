package com.simple.app.manager.view

import android.app.Activity
import android.view.View
import com.simple.app.databinding.HeaderTextBinding

/**
 * @author: yangkui
 * @Date: 2022/4/13
 * @Description: 详情文字头部
 */
object HeaderTextManager {
    /**
     * 标题中间
     */
    fun center(activity: Activity, headerTextBinding: HeaderTextBinding, title: String) {
        headerTextBinding.ivBack.setOnClickListener {
            activity.finish()
        }
        headerTextBinding.tvTitleCenter.text = title
        headerTextBinding.tvTitleCenter.visibility = View.VISIBLE
        headerTextBinding.tvRight.visibility = View.GONE
    }

    // 右边按钮
    fun centerRight(
        activity: Activity,
        headerTextBinding: HeaderTextBinding,
        title: String,
        right: String,
        onRightClickListener: OnRightClickListener
    ) {
        headerTextBinding.ivBack.setOnClickListener {
            activity.finish()
        }
        headerTextBinding.tvTitleCenter.text = title
        headerTextBinding.tvTitleCenter.visibility = View.VISIBLE
        headerTextBinding.tvRight.text = right
        headerTextBinding.tvRight.visibility = View.VISIBLE
        headerTextBinding.tvRight.setOnClickListener {
            onRightClickListener.onRight()
        }
    }

    interface OnRightClickListener {
        fun onRight()
    }
}