package com.wxtoplink.api4.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by Stephen on 2017/9/2.
 */

public class GSONUtils {

    private GSONUtils() {

    }

    /**
     *
     * @param gsonStr
     * @param claze
     * @param <T>
     * @return
     */
    public static <T>T fromJson(String gsonStr, Class<T> claze){
        Gson gson = new Gson();
        return gson.fromJson(gsonStr, claze);
    }

    /**
     * 对象转成gson字符串
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static String toJsonNullString(Object object){
        return new GsonBuilder()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>())
                .create()
                .toJson(object);
    }

    public static class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType != String.class) {
                return null;
            }
            return (TypeAdapter<T>) new NullStringAdapter();
        }
    }

    public static class NullStringAdapter extends TypeAdapter<String> {
        public String read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }
        public void write(JsonWriter writer, String value) throws IOException {
            if (value == null) {
                writer.setSerializeNulls(true);//设置允许序列化空字符串
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

    public static class GSONNullStringConverter implements JsonSerializer<String>,JsonDeserializer<String> {
        //序列化String,若值为null,输出空字符串
        @Override
        public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
            if(src == null){
                return new JsonPrimitive("");
            }else return new JsonPrimitive(src);
        }


        @Override
        public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return json.getAsJsonPrimitive().getAsString();
        }
    }

}
