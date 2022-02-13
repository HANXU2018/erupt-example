package com.example.demo.controller;
import com.example.demo.dao.CalendarRepository;
import com.example.demo.dao.FirstProblemRepository;
import com.example.demo.model.calendar.Calendar;
import com.example.demo.model.firstTiku.FirstProblem;
import com.example.demo.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2022/1/7 20:38
 */
@RestController
@RequestMapping("/api")
public class CalendarController {
    @Autowired
    private CalendarRepository calendarRepository;

    @RequestMapping("/getAllCalendar")
    public ResponseResult<List<Calendar>> getAllCalendar(){
        return ResponseResult.success(calendarRepository.findAllByDeletedIsFalse());
    }
}
