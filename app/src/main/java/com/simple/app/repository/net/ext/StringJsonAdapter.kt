package com.simple.app.repository.net.ext

import com.google.gson.JsonParser
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter


/**
 * @author: yangkui
 * @Date: 2022/5/9
 * @Description:StringJsonAdapter
 */
class StringJsonAdapter : TypeAdapter<String>() {
    override fun write(out: JsonWriter, value: String) {
        out.jsonValue(value)
    }

    override fun read(reader: JsonReader): String {
        return JsonParser.parseReader(reader).toString()
    }
}
