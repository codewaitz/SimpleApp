package com.simple.app.repository.net.ext;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author: yangkui
 * @Date: 2022/5/10
 * @Description: ResponseConverter
 */

class ResponseConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private final Type type;

    ResponseConverter(Gson gson, TypeAdapter<T> adapter, Type type) {
        this.gson = gson;
        this.adapter = adapter;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            T result = adapter.read(jsonReader);
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonIOException("JSON document was not fully consumed.");
            }
            if (result instanceof BaseRep) {
                try {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    Type[] types = parameterizedType.getActualTypeArguments();
                    ((BaseRep) result).setResultBeanType(types[0]);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            return result;
        } finally {
            value.close();
        }
    }
}