package com.simple.app.repository.net.ext

import com.google.gson.annotations.JsonAdapter
import java.lang.reflect.Type


/**
 * @author: yangkui
 * @Date: 2022/4/8
 * @Description: 响应
 */
class BaseRep<T> {
    @JsonAdapter(StringJsonAdapter::class)
    var data: String? = ""
    var code: String = ""
    var msg: String = ""

    // 结果返回
    var resultBean: T? = null
    var resultBeanType: Type? = null
}
