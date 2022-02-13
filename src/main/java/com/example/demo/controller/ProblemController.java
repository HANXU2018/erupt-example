package com.example.demo.controller;
import com.example.demo.dao.FirstProblemRepository;
import com.example.demo.model.firstTiku.FirstProblem;
import com.example.demo.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2022/1/7 20:38
 */
@RestController
public class ProblemController {
    @Autowired
    private FirstProblemRepository firstProblemRepository;

    @RequestMapping("/getAllProblem")
    public ResponseResult<List<FirstProblem>> getAllProblem(){
        return ResponseResult.success(firstProblemRepository.findAll());
    }
}
