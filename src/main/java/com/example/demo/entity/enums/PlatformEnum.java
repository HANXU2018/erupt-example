package com.example.demo.entity.enums;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2022/1/10 22:47
 *  , @VL(value = "牛客", label = "牛客")
 *                             , @VL(value = "LeetCode", label = "LeetCode")
 *                             , @VL(value = "PAT", label = "PAT")
 */
public enum PlatformEnum {
    NOWCODE("牛客"),
    LEETCODE("LeetCode"),
    LintCODE("LintCode"),
    PAT_ZOJ("PAT_ZOJ"),
    PAT("PAT");
    // 成员变量
    private String name;

    PlatformEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
