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
public class NowCodeCompanyLOGOHandler implements  EruptJobHandler{
    @Autowired
    JsonImportRepository jsonImportRepository;

    @SneakyThrows
    @Override
    public String exec(String code, String param) {
        String ans = "";
        // 最简单的HTTP请求，可以自动通过header等信息判断编码，不区分HTTP和HTTPS
            String preurl = "https://www.nowcoder.com/community/company-page?token=&page=1&pageSize=3000&_=1644760623201";
            String url = preurl;
            System.out.println(url);
            String result1= HttpUtil.get(url);
            JsonImport jsonImport = new JsonImport();
            jsonImport.setEvent("牛客公司LOGO");
            jsonImport.setMsg("牛客公司LOGO 2500家");
            jsonImport.setJsonData(result1);
            try {
                jsonImportRepository.save(jsonImport);
            }catch (Exception e){
                log.error(e.getMessage());
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
