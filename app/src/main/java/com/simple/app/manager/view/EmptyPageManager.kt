package com.simple.app.manager.view

import android.view.View
import com.simple.app.databinding.EmptyPageBinding

/**
 * @author: yangkui
 * @Date: 2022/4/13
 * @Description: 缺省页管理
 */
object EmptyPageManager {
    /**
     * 列表缺省设置
     */
    fun <T> list(
        list: MutableList<T>,
        emptyPageBinding: EmptyPageBinding,
        dataView: View,
        emptyImageRes: Int,
        emptyStringRes: Int
    ) {
        if (list.size < 1) {
            emptyPageBinding.linearEmpty.visibility = View.VISIBLE
            emptyPageBinding.ivEmpty.setImageResource(emptyImageRes)
            emptyPageBinding.tvEmpty.text =
                emptyPageBinding.root.resources.getString(emptyStringRes)
            dataView.visibility = View.GONE
            return
        } else {
            emptyPageBinding.linearEmpty.visibility = View.GONE
            dataView.visibility = View.VISIBLE
        }
    }
}