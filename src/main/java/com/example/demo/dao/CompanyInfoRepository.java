package com.example.demo.dao;

import com.example.demo.model.CompanyInfo;
import com.example.demo.model.calendar.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 直接继承 JpaRepository 就有了增删改查等能力，该功能由 spring-data-jpa 提供
 * 泛型说明：Article 实体类对象  Long 主键数据类型
 *
 */
public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, Long> {

    CompanyInfo findCompanyInfoByCompanyName(String companyName);
}
