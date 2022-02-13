package com.example.demo.dao;

import com.example.demo.model.Article;
import com.example.demo.model.calendar.Calendar;
import com.example.demo.model.firstTiku.FirstProblem;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 直接继承 JpaRepository 就有了增删改查等能力，该功能由 spring-data-jpa 提供
 * 泛型说明：Article 实体类对象  Long 主键数据类型
 *
 */
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    //int saveCalendar(Calendar calendar);

    //方法名称必须要遵循驼峰式命名规则，findBy（关键字）+属性名称（首字母大写）+查询条件（首字母大写）

    List<Calendar>findAllByDeletedIsFalse();

    //@Query("from Calendar where deleted = false")
    //List<Calendar> queryByHql(String name);



    @Query("update Calendar set pic=?1 where id=?2")
    @Modifying
    @Transactional
    //需要执行一个更新操作
    void updatepicNameById(String pic,Long id);



}
