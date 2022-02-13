package com.example.demo.utils;

import cn.hutool.core.text.UnicodeUtil;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.lang.reflect.Array;
import java.util.List;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2022/2/11 18:27
 */
public class List2StringLableUtils {


    //list转String竖线分割
    public static String list2StringSplit(JSONArray jsonArray, String name, String split) {
        String res = "";
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String lablename = jsonObject.getString(name);
                if (i > 0 && !res.isEmpty()) {
                    res = res.concat(split);
                }
                res = res.concat(lablename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    //list转String竖线分割
    public static String list2StringSplitUniCOde(JSONArray jsonArray, String name, String split) {
        String res = "";
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String lablename = UnicodeUtil.toString(jsonObject.getString(name));
                if (i > 0 && !res.isEmpty()) {
                    res = res.concat(split);
                }
                res = res.concat(lablename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    //list转String竖线分割
    public static String list2StringSplit(JSONArray jsonArray) {
        return list2StringSplit(jsonArray, "name", "|");
    }

    public static String list2StringSplit(List<String> stringList,String split) {
        String res = "";
        for (int i = 0; i < stringList.size(); i++) {
            if (i==0) {
                res=stringList.get(i);
            } else {
                res = res + split + stringList.get(i);
            }
        }
        return res;
    }
}
