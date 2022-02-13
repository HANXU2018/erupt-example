package com.example.demo;

import cn.hutool.http.HttpRequest;
import com.example.demo.dao.ArticleRepository;
import com.example.demo.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class LeetCodeTests {

    @Autowired
    private ArticleRepository articleRepository; //使用方式与 mybatis-plus 大同小异

    //获取所有文章
    @Test
    void findArticleList() {
        for (Article article : articleRepository.findAll()) {
            System.out.println(article.toString());
        }
    }

    //根据标题获取文章
    @Test
    void findArticleByTitle() {
        for (Article article : articleRepository.findByTitle("title")) {
            System.out.println(article.toString());
        }
    }

    //删除
    @Test
    void deleteArticle() {
//        articleRepository.deleteById(1L);
    }

    //新增 or 更新
    @Test
    void saveArticle() {
        Article article = new Article();
        article.setTitle("title");
        article.setTop(false);
        article.setPublish(true);
        article.setCreateTime(new Date());
        article.setContent("test test test ....");
        articleRepository.save(article);
    }

    @Test
    void getProblList(){
        String link = "https://leetcode-cn.com/graphql/";
        String result = HttpRequest.post(link)
                .header("Content-Type","application/json")
                .body("{\n" +
                        "    \"query\": \"\\n    query problemsetQuestionList($categorySlug: String, $limit: Int, $skip: Int, $filters: QuestionListFilterInput) {\\n  problemsetQuestionList(\\n    categorySlug: $categorySlug\\n    limit: $limit\\n    skip: $skip\\n    filters: $filters\\n  ) {\\n    hasMore\\n    total\\n    questions {\\n      acRate\\n      difficulty\\n      freqBar\\n      frontendQuestionId\\n      isFavor\\n      paidOnly\\n      solutionNum\\n      status\\n      title\\n      titleCn\\n      titleSlug\\n      topicTags {\\n        name\\n        nameTranslated\\n        id\\n        slug\\n      }\\n      extra {\\n        hasVideoSolution\\n        topCompanyTags {\\n          imgUrl\\n          slug\\n          numSubscribed\\n        }\\n      }\\n    }\\n  }\\n}\\n    \",\n" +
                        "    \"variables\": {\n" +
                        "        \"categorySlug\": \"\",\n" +
                        "        \"skip\": 0,\n" +
                        "        \"limit\": 100,\n" +
                        "        \"filters\": {}\n" +
                        "    }\n" +
                        "}").execute().body();

        System.out.println(result);

    }

}
