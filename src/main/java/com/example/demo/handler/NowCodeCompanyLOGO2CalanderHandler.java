package com.example.demo.handler;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import com.example.demo.dao.CalendarRepository;
import com.example.demo.dao.CompanyInfoRepository;
import com.example.demo.dao.JsonImportRepository;
import com.example.demo.model.CompanyInfo;
import com.example.demo.model.calendar.Calendar;
import com.example.demo.model.imports.JsonImport;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xyz.erupt.job.handler.EruptJobHandler;

import java.util.List;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2022/1/10 15:56
 */
@Log4j2
@Service
public class NowCodeCompanyLOGO2CalanderHandler implements  EruptJobHandler{
    @Autowired
    JsonImportRepository jsonImportRepository;
    @Autowired
    CalendarRepository calendarRepository;
    @Autowired
    CompanyInfoRepository companyInfoRepository;

    @SneakyThrows
    @Override
    public String exec(String code, String param) {
        List<Calendar> all = calendarRepository.findAll();
        for(int i = 0;i<all.size();i++){
            Calendar calendar = all.get(i);
            String intro = calendar.getIntro();
            String odName = calendar.getOdName();
            System.out.println(odName);
            if(odName.equals("校招笔试")||odName.equals("赛码网")){
                String companyNmae = intro.substring("【校招笔试】".length());
                if(companyNmae.endsWith("非技术类")){
                    companyNmae = companyNmae.substring(0,companyNmae.length()-4);
                }else if(companyNmae.endsWith("技术类")){
                    companyNmae = companyNmae.substring(0,companyNmae.length()-3);
                }
                CompanyInfo companyInfo = companyInfoRepository.findCompanyInfoByCompanyName(companyNmae);
                System.out.println(companyInfo);
                if(companyInfo != null && StringUtils.isNotBlank(companyInfo.getImageurl())){
                    calendarRepository.updatepicNameById(companyInfo.getImageurl(),calendar.getId());
                }
            }
        }
        return "true";
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
