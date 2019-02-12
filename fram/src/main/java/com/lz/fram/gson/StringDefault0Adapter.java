package com.lz.fram.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Created by Allen on 2017/11/20.
 * <p>
 * 定义为long类型,如果后台返回""或者null,则返回""
 */

public class StringDefault0Adapter implements JsonSerializer<String>, JsonDeserializer<String> {
    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            if (json.getAsString().equals("") || json.getAsString().equals("null")) {
                return "";
            }
        } catch (Exception ignore) {
        }

        return json.getAsString();

    }

    @Override
    public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }

}