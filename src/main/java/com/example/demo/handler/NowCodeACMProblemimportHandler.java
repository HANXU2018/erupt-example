package com.example.demo.handler;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpRequest;
import com.example.demo.dao.JsonImportRepository;
import com.example.demo.model.imports.JsonImport;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import xyz.erupt.job.handler.EruptJobHandler;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2022/1/10 15:56
 */
@Log4j2
@Service
public class NowCodeACMProblemimportHandler implements  EruptJobHandler{
    @Autowired
    JsonImportRepository jsonImportRepository;
    @SneakyThrows
    @Override
    public String exec(String code, String param) {
        String ans = "";
        // 最简单的HTTP请求，可以自动通过header等信息判断编码，不区分HTTP和HTTPS
        JSONObject jsonValue = new JSONObject(param);
        for(int i=1;i<=424;i++){
            String s = "https://ac.nowcoder.com/acm/problem/list?keyword=&tagId=&platformTagId=0&sourceTagId=0&difficulty=0&status=all&order=id&asc=true&pageSize=50&page={}";
            String link = StrFormatter.format(s,i);
            System.out.println(link);
            TimeUnit.SECONDS.sleep(10);
            //String result1= HttpUtil.get(link);

            String result1 = HttpRequest.get(link).execute().body();
            /*
            <tr data-problemId="13883">
                <td>
                    <span class="ico-todo">未提交</span>
                </td>
                <td>
                    <a href="/acm/problem/13883" target="_blank">NC13883</a>
                </td>
                <td class="fn-right" colspan="2">
                    <a href="/acm/problem/13883" target="_blank" class="title">Matrix</a>
                    <a href="javascript:void(0);" class="tag-label js-tag" target="_blank"
                        data-id="1283">枚举</a>
                </td>
                <td>
                    1星
                </td>
                <td>510/1265</td>
                <td>
                    <a href="/acm/problem/13883" target="_blank"
                        class="ico-bank-item ico-bank-code js-nc-title-tips" title="提交代码"></a>
                    <a href="javascript:void(0);" data-id="13883"
                        class="js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips"
                        title="添加到题单"></a>
                </td>
            </tr>
            *?
             */
            JsonImport jsonImport = new JsonImport();
            jsonImport.setEvent("牛客竞赛题库");
            jsonImport.setMsg("牛客竞赛题目集合"+i);
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
