package com.example.demo.handler;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
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
public class NowCodeACMimportHandler implements  EruptJobHandler{
    @Autowired
    JsonImportRepository jsonImportRepository;

    @SneakyThrows
    @Override
    public String exec(String code, String param) {
        String ans = "";
        // 最简单的HTTP请求，可以自动通过header等信息判断编码，不区分HTTP和HTTPS
        JSONObject jsonValue = new JSONObject(param);
        JSONArray month = jsonValue.getJSONArray("month");
        for(int i=0;i<month.length();i++){
            String preurl = "https://ac.nowcoder.com/acm/calendar/contest?token=&month=";
            String endurl = "&_=1644410939206";
            String qurymonth = month.getString(i);
            String url = preurl+qurymonth+endurl;
            System.out.println(url);
            String result1= HttpUtil.get(url);
            JsonImport jsonImport = new JsonImport();
            jsonImport.setEvent("牛客竞赛日历");
            jsonImport.setMsg(qurymonth+"牛客竞赛日历");
            jsonImport.setJsonData(result1);
            try {
                jsonImportRepository.save(jsonImport);
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
        SecureUtil.md5();
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
