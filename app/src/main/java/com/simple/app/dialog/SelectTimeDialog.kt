package com.simple.app.dialog

import android.content.Context
import android.lib.common.utils.DateTimeUtil
import android.lib.common.utils.StringUtil
import android.lib.common.widget.dialog.CommonBottomDialog
import android.lib.common.widget.wheel.WheelView
import android.widget.Button
import android.widget.TextView
import com.simple.app.R

/**
 * @author: yangkui
 * @Date: 2022/4/21
 * @Description: SelectTimeDialog
 */
class SelectTimeDialog(
    context: Context,  // 上下文
    var selectTime: String?, // yyyy-MM-dd HH:mm:ss
    var onSelectListener: OnSelectListener
) :
    CommonBottomDialog(context, context.resources.getString(R.string.select_time_title)) {
    private var hourList = mutableListOf<String>()
    private var minuteList = mutableListOf<String>()
    private var selectHourInitIndex = 0
    private var selectMinuteInitIndex = 0

    // view
    private var wheelHour: WheelView? = null
    private var wheelMinute: WheelView? = null
    private var tvTomorrow: TextView? = null

    override fun getLayoutRes(): Int {
        return R.layout.dialog_select_time
    }

    override fun onCreate() {
        initList() //初始化
        // 设置日期
        var dateText = DateTimeUtil.date2String(
            DateTimeUtil.string2Date(selectTime, "yyyy-MM-dd HH:mm:ss"),
            "yyyy-MM-dd"
        )
        if (StringUtil.isEmpty(dateText)) dateText = DateTimeUtil.getTomorrowDateString()
        tvTomorrow = findViewById(R.id.tv_tomorrow)
        tvTomorrow?.text = dateText
        // 时间选择
        wheelHour = findViewById(R.id.wheel_hour)
        wheelMinute = findViewById(R.id.wheel_minute)
        wheelHour?.setEntries(hourList)
        wheelHour?.setOnWheelChangedListener { view, _, newIndex ->
            view?.getItem(newIndex)
        }
        wheelMinute?.setEntries(minuteList)
        wheelMinute?.setOnWheelChangedListener { view, _, newIndex ->
            view?.getItem(newIndex)
        }
        // 初始化选择
        wheelHour?.currentIndex = selectHourInitIndex
        wheelMinute?.currentIndex = selectMinuteInitIndex
        // 选择
        findViewById<Button>(R.id.btn_select).setOnClickListener {
            dismiss()
            var dateTime =
                tvTomorrow?.text.toString() + " " + wheelHour?.currentItem.toString() + ":" + wheelMinute?.currentItem.toString() + ":00"
            onSelectListener.onSelect(dateTime)
        }
    }

    private fun initList() {
        // 选择索引
        var date = DateTimeUtil.string2Date(selectTime, "yyyy-MM-dd HH:mm:ss")
        var selectHourText = DateTimeUtil.date2String(date, "HH")
        var selectMinuteText = DateTimeUtil.date2String(date, "mm")
        // 初始化list
        for (index in 8..18) {
            var hourText = StringUtil.patch2String(index)
            if (hourText == selectHourText) {
                selectHourInitIndex = index - 8
            }
            hourList.add(hourText)
        }
        if ("00" == selectMinuteText) {
            selectMinuteInitIndex = 0
        }
        minuteList.add("00")
        if ("30" == selectMinuteText) {
            selectMinuteInitIndex = 1
        }
        minuteList.add("30")
    }

    interface OnSelectListener {
        fun onSelect(dateTimeString: String) // yyyy-MM-dd HH:mm:ss
    }
}