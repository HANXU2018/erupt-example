package com.example.demo;

import com.example.demo.dao.ArticleRepository;
import com.example.demo.dao.CalendarRepository;
import com.example.demo.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class CalanderTests {

    @Autowired
    CalendarRepository calendarRepository;
    @Test
    void saveArticle() {
        calendarRepository.updatepicNameById("wqeqweq",new Long(1322));
    }


}
