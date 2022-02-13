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
public class LeetCodeProblemimportHandler implements  EruptJobHandler{
    @Autowired
    JsonImportRepository jsonImportRepository;
    String PATCookie = "_ga=GA1.2.1309248200.1634965458; _gid=GA1.2.1053479663.1644472214; PTASession=3919f17c-90cf-4526-ae3d-bc26ca5dfc55; _gat=1; JSESSIONID=BD9A0852F6B6F17A4C513008851B3E9E";

    @SneakyThrows
    @Override
    public String exec(String code, String param) {
        String ans = "";
        // 最简单的HTTP请求，可以自动通过header等信息判断编码，不区分HTTP和HTTPS
        JSONObject jsonValue = new JSONObject(param);
        JSONArray month = jsonValue.getJSONArray("month");
        for(int i=0;i<26;i++){
            String link = "https://leetcode-cn.com/graphql/";
            String body = "{\n" +
                    "    \"query\": \"\\n    query problemsetQuestionList($categorySlug: String, $limit: Int, $skip: Int, $filters: QuestionListFilterInput) {\\n  problemsetQuestionList(\\n    categorySlug: $categorySlug\\n    limit: $limit\\n    skip: $skip\\n    filters: $filters\\n  ) {\\n    hasMore\\n    total\\n    questions {\\n      acRate\\n      difficulty\\n      freqBar\\n      frontendQuestionId\\n      isFavor\\n      paidOnly\\n      solutionNum\\n      status\\n      title\\n      titleCn\\n      titleSlug\\n      topicTags {\\n        name\\n        nameTranslated\\n        id\\n        slug\\n      }\\n      extra {\\n        hasVideoSolution\\n        topCompanyTags {\\n          imgUrl\\n          slug\\n          numSubscribed\\n        }\\n      }\\n    }\\n  }\\n}\\n    \",\n" +
                    "    \"variables\": {\n" +
                    "        \"categorySlug\": \"\",\n" +
                    "        \"skip\": {},\n" +
                    "        \"limit\": 100,\n" +
                    "        \"filters\": {}\n" +
                    "    }\n" +
                    "}";
            String queryBody = StrFormatter.format(body,i*100);
            String result = HttpRequest.post(link)
                    .header("Content-Type","application/json")
                    .body(queryBody).execute().body();
            JsonImport jsonImport = new JsonImport();
            jsonImport.setEvent("Leetcode题库");
            jsonImport.setMsg("LeetCode题目集合"+i);
            jsonImport.setJsonData(result);
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
