package com.example.demo.handler;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.http.HttpRequest;
import com.example.demo.dao.JsonImportRepository;
import com.example.demo.model.imports.JsonImport;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import xyz.erupt.job.handler.EruptJobHandler;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2022/1/10 15:56
 */
@Log4j2
@Service
public class LintCodeProblemimportHandler implements  EruptJobHandler{
    @Autowired
    JsonImportRepository jsonImportRepository;
    String PATCookie = "_ga=GA1.2.1309248200.1634965458; _gid=GA1.2.1053479663.1644472214; PTASession=3919f17c-90cf-4526-ae3d-bc26ca5dfc55; _gat=1; JSESSIONID=BD9A0852F6B6F17A4C513008851B3E9E";

    @SneakyThrows
    @Override
    public String exec(String code, String param) {
        String ans = "";
        // 最简单的HTTP请求，可以自动通过header等信息判断编码，不区分HTTP和HTTPS
        JSONObject jsonValue = new JSONObject(param);
        String nextlink = null;
        for(int i=1;i<=26;i++){
            String s = "https://www.lintcode.com/v2/api/problems/?_format=new&page={}&page_size=100";
            String link = StrFormatter.format(s,i);
            System.out.println(link);
            //TimeUnit.SECONDS.sleep(10);
            //String result1= HttpUtil.get(link);

            String result1 = null;
            if(nextlink != null){
                System.out.println(nextlink);
                result1 = HttpRequest.get(nextlink).execute().body();
            }else{
                result1 = HttpRequest.get(link).execute().body();
            }
            JSONObject jsonValue2 = new JSONObject(result1);
            if(jsonValue2.has("nextlink")){
                nextlink = jsonValue2.getString("nextlink");
            }

            JsonImport jsonImport = new JsonImport();
            jsonImport.setEvent("Lintcode题库");
            jsonImport.setMsg("LintCode题目集合"+i);
            jsonImport.setJsonData(result1);
            try {
                jsonImportRepository.save(jsonImport);
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
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
