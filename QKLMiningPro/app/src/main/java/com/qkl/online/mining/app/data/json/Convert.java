/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qkl.online.mining.app.data.json;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.qkl.online.mining.app.data.entity.AppCommonsConfig;
import com.qkl.online.mining.app.data.entity.Earnings;
import com.qkl.online.mining.app.data.entity.Exchange;
import com.qkl.online.mining.app.data.entity.ExchangeDetail;
import com.qkl.online.mining.app.data.entity.GameBean;
import com.qkl.online.mining.app.data.entity.MyStar;
import com.qkl.online.mining.app.data.entity.MyYuntEntity;
import com.qkl.online.mining.app.data.entity.SSExchangerate;

import org.json.JSONObject;

import java.io.Reader;
import java.lang.reflect.Type;

/**
 */
public class Convert {

    private static Gson create() {
        return Convert.GsonHolder.gson;
    }

    private static class GsonHolder {
        private static Gson gson = new Gson();
    }

    public static <T> T fromJson(String json, Class<T> type) throws JsonIOException, JsonSyntaxException {
        return create().fromJson(json, type);
    }

    public static <T> T fromJson(String json, Type type) {
        return create().fromJson(json, type);
    }

    public static <T> T fromJson(JsonReader reader, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        return create().fromJson(reader, typeOfT);
    }

    public static <T> T fromJson(Reader json, Class<T> classOfT) throws JsonSyntaxException, JsonIOException {
        return create().fromJson(json, classOfT);
    }

    public static <T> T fromJson(Reader json, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        return create().fromJson(json, typeOfT);
    }

    public static String toJson(Object src) {
        return create().toJson(src);
    }

    public static String toJson(Object src, Type typeOfSrc) {
        return create().toJson(src, typeOfSrc);
    }

    public static MyStar forMyStar(JSONObject jsonObject) {
        try {
            Type type = new TypeToken<MyStar>(){}.getType();
            MyStar myStar = create().fromJson(jsonObject.toString(), type);
            return myStar;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Earnings forEarnings(JSONObject jsonObject) {
        try {
            Type type = new TypeToken<Earnings>(){}.getType();
            Earnings earnings = create().fromJson(jsonObject.toString(), type);
            return earnings;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Exchange forExchange(JSONObject jsonObject) {
        try {
            Type type = new TypeToken<Exchange>(){}.getType();
            Exchange exchange = create().fromJson(jsonObject.toString(), type);
            return exchange;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ExchangeDetail forExchangeDetail(JSONObject jsonObject) {
        try {
            Type type = new TypeToken<ExchangeDetail>(){}.getType();
            ExchangeDetail exchangeDetail = create().fromJson(jsonObject.toString(), type);
            return exchangeDetail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MyYuntEntity forMyYuntEntity(JSONObject jsonObject) {
        try {
            Type type = new TypeToken<MyYuntEntity>(){}.getType();
            MyYuntEntity myYuntEntity = create().fromJson(jsonObject.toString(), type);
            return myYuntEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SSExchangerate forSSExchangerate(JSONObject jsonObject) {
        try {
            Type type = new TypeToken<SSExchangerate>(){}.getType();
            SSExchangerate ssExchangerate = create().fromJson(jsonObject.toString(), type);
            return ssExchangerate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static GameBean forGameBean(JSONObject jsonObject) {
        try {
            Type type = new TypeToken<GameBean>(){}.getType();
            GameBean gameBean = create().fromJson(jsonObject.toString(), type);
            return gameBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static AppCommonsConfig forAppCommonsConfig(JSONObject jsonObject) {
        try {
            Type type = new TypeToken<AppCommonsConfig>(){}.getType();
            AppCommonsConfig appCommonsConfig = create().fromJson(jsonObject.toString(), type);
            return appCommonsConfig;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
