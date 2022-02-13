package com.example.demo.handler;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.example.demo.dao.JsonImportRepository;
import com.example.demo.model.imports.JsonImport;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import xyz.erupt.job.handler.EruptJobHandler;

import java.net.HttpURLConnection;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2022/1/10 15:56
 */
@Log4j2
@Service
public class PATTeamProblemimportHandler implements EruptJobHandler {
    @Autowired
    JsonImportRepository jsonImportRepository;
    String Cookie = "_ga=GA1.2.1309248200.1634965458; _gid=GA1.2.1053479663.1644472214; PTASession=3919f17c-90cf-4526-ae3d-bc26ca5dfc55; _gat=1; JSESSIONID=BD9A0852F6B6F17A4C513008851B3E9E";

    @SneakyThrows
    @Override
    public String exec(String code, String param) {
        String ans = "";
        // 最简单的HTTP请求，可以自动通过header等信息判断编码，不区分HTTP和HTTPS
        JSONObject jsonValue = new JSONObject(param);

        String id = "994805147132084224";
        String problemSetId = "994805046380707840";
        //Step1 获取 problem ID

        String url = "https://pintia.cn/api/problem-sets/{}/problems/{}";
        StrFormatter.format(url, problemSetId, id);
        String response = HttpRequest.get(url).header("cookie", Cookie).header("accept", "application/json;charset=UTF-8").execute().body();


        //Step2 然后 获取题目信息
        System.out.println("定时任务开始 打印");
        System.out.println(param);
        System.out.println("定时任务完成 打印");
        return ans;
    }

    @Override
    public void success(String result, String param) {
        EruptJobHandler.super.success(result, param);
    }

    @Override
    public void error(Throwable throwable, String param) {
        EruptJobHandler.super.error(throwable, param);
    }
}
