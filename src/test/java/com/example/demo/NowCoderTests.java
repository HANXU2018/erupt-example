package com.example.demo;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpRequest;
import com.example.demo.dao.ArticleRepository;
import com.example.demo.model.Article;
import com.example.demo.model.firstTiku.FirstProblem;
import com.example.demo.utils.List2StringLableUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class NowCoderTests {

    //@Test
    //void getProblList(){
    //    String result1 = "<!DOCTYPE html>\n" +
    //            "\n" +
    //            "\n" +
    //            "<!-- html version 1999 -->\n" +
    //            "\n" +
    //            "\n" +
    //            "\n" +
    //            "\n" +
    //            "\n" +
    //            "\n" +
    //            "\n" +
    //            "<html>\n" +
    //            "<head>\n" +
    //            "<meta charset=\"utf-8\">\n" +
    //            "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
    //            "<meta name=\"google-site-verification\" content=\"lFSmn0rlLR-OPkas5c2GxdhTKP4J4PyaahhQ2TNanjs\"/>\n" +
    //            "<meta itemprop=\"image\" content=\"https://static.nowcoder.com/acm/images-acm/res/logo/acm-logo.png\"/>\n" +
    //            "<meta name=\"description\"  content=\"ACM/CSP/NOI比赛知识点考点练习平台。包含正式赛历年真题、各知识点练习题、编程能力水平评估、题目视频教程等多维度训练规划，帮助初学者快速刷题提升竞赛竞技水平\" >\n" +
    //            "<meta name=\"keywords\"  content=\"ACM,CSP,NOI,CCPC,NOI,OI,ICPC,Virtual Judge,C语言,C++,提高组,普及组,入门组,信息学,算法,题解,编程比赛,程序设计,coding,在线编程,编程学习,训练,牛客网,牛客竞赛\" >\n" +
    //            "<title>竞赛题库_ACM/NOI/CSP基础提高训练专区_牛客竞赛OJ</title>\n" +
    //            "<meta name=\"nowcoder-uuid\" content=\"f39615ed-c5f5-4bed-926a-bb3de5ef306d\"/>\n" +
    //            "\n" +
    //            "<link rel=\"stylesheet\" href=\"//static.nowcoder.com/acm/nowcoder/1.0.497/stylesheets-acm/v2lib/fontcustom/global/global.css\" />\n" +
    //            "<link rel=\"stylesheet\" href=\"//static.nowcoder.com/acm/nowcoder/1.0.497/stylesheets-acm/v2lib/global/global.ui.css\" />\n" +
    //            "<link rel=\"stylesheet\" href=\"//static.nowcoder.com/acm/nowcoder/1.0.497/stylesheets-acm/v2lib/base.css\">\n" +
    //            "<link rel=\"stylesheet\" href=\"//static.nowcoder.com/acm/nowcoder/1.0.497/javascripts-acm/lib/common.css\">\n" +
    //            "<link rel=\"stylesheet\" href=\"//static.nowcoder.com/acm/nowcoder/1.0.497/javascripts-acm/lib/env.css\">\n" +
    //            "<link rel=\"stylesheet\" href=\"//static.nowcoder.com/acm/nowcoder/1.0.497/stylesheets-acm/element-ui.css\">\n" +
    //            "<link rel=\"stylesheet\" href=\"//static.nowcoder.com/acm/nowcoder/1.0.497/stylesheets-acm/pages/questionBank/index.css\">\n" +
    //            "<link rel=\"stylesheet\" href=\"//static.nowcoder.com/acm/nowcoder/1.0.497/javascripts-acm/page/problem/list/main.entry.css\">\n" +
    //            "</head>\n" +
    //            "<body>\n" +
    //            "    \n" +
    //            "            <div class=\"nk-container acm-container\">\n" +
    //            "    \n" +
    //            "    <div class=\"acm-header js-site-global-nav\">\n" +
    //            "        <div class=\"acm-header-main clearfix\">\n" +
    //            "            <a class=\"acm-logo\" href=\"/acm/home\" title=\"牛客竞赛\">\n" +
    //            "                <img src=\"//static.nowcoder.com/acm/images-acm/logo.png\" style=\"width:139px;height:42px;\" alt=\"\"/>\n" +
    //            "            </a>\n" +
    //            "                    <ul class=\"acm-nav\">\n" +
    //            "                <li><a class=\"    \" href=\"/acm/home\">首页</a></li>\n" +
    //            "                <li><a class=\"    \" href=\"/acm/contest/vip-index\">比赛</a></li>\n" +
    //            "                <li><a class=\"    selected active\" href=\"/acm/problem/list\">题库</a></li>\n" +
    //            "                <li><a class=\"    \" href=\"/acm/course\">课程</a></li>\n" +
    //            "                <li><a class=\"    \" href=\"/acm/discuss\">竞赛讨论区</a></li>\n" +
    //            "                </li>\n" +
    //            "            </ul>\n" +
    //            "                                                    <div class=\"nav-account\">\n" +
    //            "                    <a href=\"https://www.nowcoder.com/login\" class=\"nav-account-login\" id=\"nav-login\">登录</a>/\n" +
    //            "                    <a href=\"https://www.nowcoder.com/register\" class=\"nav-account-reg\" id=\"jsNavRegister\">注册</a>\n" +
    //            "                </div>\n" +
    //            "                        <ul class=\"acm-nav-info\">\n" +
    //            "                            <li>\n" +
    //            "                    <a href=\"https://www.nowcoder.com/\" target=\"_blank\"><i class=\"icon-home\"></i>去牛客网</a>\n" +
    //            "                </li>\n" +
    //            "\n" +
    //            "                                        </ul>\n" +
    //            "        </div>\n" +
    //            "    </div>\n" +
    //            "\n" +
    //            "\n" +
    //            "    \n" +
    //            "<div class=\"nk-container\">\n" +
    //            "<div class=\"nk-main question-bank clearfix\">\n" +
    //            "<div class=\"nk-content\">\n" +
    //            "<div class=\"module-box\">\n" +
    //            "<div class=\"module-head clearfix\">\n" +
    //            "<h1>题库</h1>\n" +
    //            "</div>\n" +
    //            "<div class=\"module-body\">\n" +
    //            "<div class=\"question-type-wrapper\">\n" +
    //            "<a href=\"/acm/problem/collection/list\" class=\"question-type-item\" target=\"_blank\">\n" +
    //            "<span class=\"question-type-ico question-type-ico1\"></span>\n" +
    //            "<span>按题单练习</span>\n" +
    //            "</a>\n" +
    //            "<a href=\"/acm/skill/noip-tg\" class=\"question-type-item\" target=\"_blank\">\n" +
    //            "<span class=\"question-type-ico question-type-ico2\"></span>\n" +
    //            "<span>按知识点练习</span>\n" +
    //            "</a>\n" +
    //            "<a href=\"/acm/challenge/summary\" class=\"question-type-item\" target=\"_blank\">\n" +
    //            "<span class=\"question-type-ico question-type-ico3\"></span>\n" +
    //            "<span>题目通关挑战</span>\n" +
    //            "</a>\n" +
    //            "<a href=\"https://ac.nowcoder.com/discuss/817596?f=b\" class=\"question-type-item\" target=\"_blank\">\n" +
    //            "<span class=\"question-type-ico question-type-ico5\"></span>\n" +
    //            "<span>新手入门</span>\n" +
    //            "</a>\n" +
    //            "<a href=\"https://ac.nowcoder.com/courses/cover/live/231\" class=\"question-type-item\" target=\"_blank\">\n" +
    //            "<span class=\"question-type-ico question-type-ico4\"></span>\n" +
    //            "<span>题目视频讲解</span>\n" +
    //            "</a>\n" +
    //            "</div>\n" +
    //            "<div class=\"question-oprt-mod\">\n" +
    //            "<div class=\"fold-search-box open js-search\">\n" +
    //            "<a href=\"javascript:void(0);\" class=\"icon-search icon-search-out\"></a>\n" +
    //            "<div class=\"search-input-wrap\">\n" +
    //            "<label class=\"icon-search\"></label>\n" +
    //            "<input type=\"text\" placeholder=\"输入关键词或题号回车搜索\" value=\"\">\n" +
    //            "</div>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"search-wrap-close\"></a>\n" +
    //            "</div>\n" +
    //            "<div class=\"question-filter-wrapper\">\n" +
    //            "<div class=\"with-sub-oprt js-filter-platform\">\n" +
    //            "<span> 所属平台  </span>\n" +
    //            "<span class=\"icon-angle-down\"></span><span class=\"icon-angle-up\"></span>\n" +
    //            "</div>\n" +
    //            "<div class=\"with-sub-oprt js-filter-source\">\n" +
    //            "<span> 来源  </span>\n" +
    //            "<span class=\"icon-angle-down\"></span><span class=\"icon-angle-up\"></span>\n" +
    //            "</div>\n" +
    //            "<div class=\"with-sub-oprt js-filter-difficulty\">\n" +
    //            "<span> 难度  </span>\n" +
    //            "<span class=\"icon-angle-down\"></span><span class=\"icon-angle-up\"></span>\n" +
    //            "</div>\n" +
    //            "<div class=\"with-sub-oprt js-filter-tag\">\n" +
    //            "<span>   </span>\n" +
    //            "<span class=\"icon-angle-down\"></span><span class=\"icon-angle-up\"></span>\n" +
    //            "</div>\n" +
    //            "<div class=\"with-sub-oprt js-filter-status\">\n" +
    //            "<span> 状态 </span>\n" +
    //            "<span class=\"icon-angle-down\"></span><span class=\"icon-angle-up\"></span>\n" +
    //            "</div>\n" +
    //            "</div>\n" +
    //            "</div>\n" +
    //            "<table class=\"no-border\">\n" +
    //            "<tbody>\n" +
    //            "<tr>\n" +
    //            "<th width=\"75\">状态</th>\n" +
    //            "<th width=\"110\" data-type=\"id\">题号\n" +
    //            "<div class=\"rank-oprt\"><a href=\"javascript:void(0);\" class=\"rank-up js-rank\" data-asc=\"1\"></a><a href=\"javascript:void(0);\" class=\"rank-down js-rank\" data-asc=\"0\"></a></div>\n" +
    //            "</th>\n" +
    //            "<th>标题</th>\n" +
    //            "<th width=\"150\">显示知识点 <span class=\"js-knowledge\"></span></th>\n" +
    //            "<th width=\"20\" data-type=\"difficulty\">难度\n" +
    //            "<div class=\"rank-oprt\"><a href=\"javascript:void(0);\" class=\"rank-up js-rank\" data-asc=\"1\"></a><a href=\"javascript:void(0);\" class=\"rank-down js-rank\" data-asc=\"0\"></a></div>\n" +
    //            "</th>\n" +
    //            "<th width=\"85\" data-type=\"acceptRate\">通过率\n" +
    //            "<div class=\"rank-oprt\"><a href=\"javascript:void(0);\" class=\"rank-up js-rank\" data-asc=\"1\"></a><a href=\"javascript:void(0);\" class=\"rank-down js-rank\" data-asc=\"0\"></a></div>\n" +
    //            "</th>\n" +
    //            "<th width=\"90\">操作</th>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"13822\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13822\" target=\"_blank\">NC13822</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/13822\" target=\"_blank\" class=\"title\">Keep In Line</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1283\">枚举</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>433/1204</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13822\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"13822\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"13825\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13825\" target=\"_blank\">NC13825</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/13825\" target=\"_blank\" class=\"title\">相反数</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>967/1484</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13825\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"13825\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"13882\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13882\" target=\"_blank\">NC13882</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/13882\" target=\"_blank\" class=\"title\">Knapsack Problem</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1283\">枚举</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "1星\n" +
    //            "</td>\n" +
    //            "<td>459/778</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13882\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"13882\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"13883\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13883\" target=\"_blank\">NC13883</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/13883\" target=\"_blank\" class=\"title\">Matrix</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1283\">枚举</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "1星\n" +
    //            "</td>\n" +
    //            "<td>510/1265</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13883\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"13883\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"13884\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13884\" target=\"_blank\">NC13884</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/13884\" target=\"_blank\" class=\"title\">Paint Box</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1313\">组合数学</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"14419\">排列组合</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>169/734</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13884\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"13884\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"13885\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13885\" target=\"_blank\">NC13885</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/13885\" target=\"_blank\" class=\"title\">Music Problem</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1283\">枚举</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1558\">STL</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>817/2890</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13885\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"13885\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"13886\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13886\" target=\"_blank\">NC13886</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/13886\" target=\"_blank\" class=\"title\">Shortest Path</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1310\">树</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "2星\n" +
    //            "</td>\n" +
    //            "<td>266/431</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13886\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"13886\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"13887\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13887\" target=\"_blank\">NC13887</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/13887\" target=\"_blank\" class=\"title\">Maximize The Beautiful Value</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1279\">贪心</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "2星\n" +
    //            "</td>\n" +
    //            "<td>722/1827</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13887\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"13887\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"13888\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13888\" target=\"_blank\">NC13888</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/13888\" target=\"_blank\" class=\"title\">Maximize The Beautiful Value++</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1302\">单调队列单调栈</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "4星\n" +
    //            "</td>\n" +
    //            "<td>26/158</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13888\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"13888\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"13889\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13889\" target=\"_blank\">NC13889</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/13889\" target=\"_blank\" class=\"title\">Magic Maze</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1301\">动态规划</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1586\">最长路</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "2星\n" +
    //            "</td>\n" +
    //            "<td>152/404</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13889\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"13889\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"13890\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13890\" target=\"_blank\">NC13890</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/13890\" target=\"_blank\" class=\"title\">HGCD</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1277\">数学</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1583\">打表题</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "5星\n" +
    //            "</td>\n" +
    //            "<td>15/36</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13890\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"13890\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"13891\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13891\" target=\"_blank\">NC13891</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/13891\" target=\"_blank\" class=\"title\">The Trip On Abandoned Railway</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1297\">线段树</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>48/159</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13891\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"13891\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"13892\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13892\" target=\"_blank\">NC13892</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/13892\" target=\"_blank\" class=\"title\">Segment Tree</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1283\">枚举</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "1星\n" +
    //            "</td>\n" +
    //            "<td>178/306</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13892\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"13892\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"13947\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13947\" target=\"_blank\">NC13947</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/13947\" target=\"_blank\" class=\"title\">Contest</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1276\">排序</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1297\">线段树</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1315\">树状数组</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>416/1365</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13947\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"13947\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"13949\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13949\" target=\"_blank\">NC13949</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/13949\" target=\"_blank\" class=\"title\">River</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1282\">二分</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "5星\n" +
    //            "</td>\n" +
    //            "<td>42/264</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13949\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"13949\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"13950\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13950\" target=\"_blank\">NC13950</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/13950\" target=\"_blank\" class=\"title\">Alliances</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1310\">树</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>93/267</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/13950\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"13950\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14112\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14112\" target=\"_blank\">NC14112</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14112\" target=\"_blank\" class=\"title\">Check In</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1283\">枚举</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "1星\n" +
    //            "</td>\n" +
    //            "<td>1084/3232</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14112\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14112\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14113\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14113\" target=\"_blank\">NC14113</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14113\" target=\"_blank\" class=\"title\">Squared Permutation</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1297\">线段树</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1315\">树状数组</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "4星\n" +
    //            "</td>\n" +
    //            "<td>74/461</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14113\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14113\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14114\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14114\" target=\"_blank\">NC14114</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14114\" target=\"_blank\" class=\"title\">Quailty's Life</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1301\">动态规划</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1591\">离散化</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "4星\n" +
    //            "</td>\n" +
    //            "<td>6/12</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14114\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14114\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14115\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14115\" target=\"_blank\">NC14115</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14115\" target=\"_blank\" class=\"title\">Air Hockey</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1282\">二分</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1314\">计算几何</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>86/376</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14115\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14115\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14116\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14116\" target=\"_blank\">NC14116</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14116\" target=\"_blank\" class=\"title\">Simple Database</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1283\">枚举</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "5星\n" +
    //            "</td>\n" +
    //            "<td>6/22</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14116\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14116\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14117\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14117\" target=\"_blank\">NC14117</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14117\" target=\"_blank\" class=\"title\">Training Plan</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1301\">动态规划</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "2星\n" +
    //            "</td>\n" +
    //            "<td>209/625</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14117\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14117\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14118\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14118\" target=\"_blank\">NC14118</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14118\" target=\"_blank\" class=\"title\">Certain Maze</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1285\">状压dp</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "4星\n" +
    //            "</td>\n" +
    //            "<td>17/86</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14118\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14118\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14119\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14119\" target=\"_blank\">NC14119</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14119\" target=\"_blank\" class=\"title\">Random Maze</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1263\">广度优先搜索(BFS)</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1283\">枚举</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>14/18</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14119\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14119\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14120\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14120\" target=\"_blank\">NC14120</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14120\" target=\"_blank\" class=\"title\">Cactus Exploration</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1279\">贪心</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>42/137</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14120\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14120\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14121\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14121\" target=\"_blank\">NC14121</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14121\" target=\"_blank\" class=\"title\">Whalyzh's Problem</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1594\">网络流</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "5星\n" +
    //            "</td>\n" +
    //            "<td>12/69</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14121\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14121\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14122\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14122\" target=\"_blank\">NC14122</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14122\" target=\"_blank\" class=\"title\">ACM Battle</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1263\">广度优先搜索(BFS)</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>125/395</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14122\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14122\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14131\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14131\" target=\"_blank\">NC14131</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14131\" target=\"_blank\" class=\"title\">反蝴蝶效应</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1283\">枚举</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "1星\n" +
    //            "</td>\n" +
    //            "<td>1331/3269</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14131\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14131\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14132\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14132\" target=\"_blank\">NC14132</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14132\" target=\"_blank\" class=\"title\">贝伦卡斯泰露</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1264\">深度优先搜索(DFS)</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "2星\n" +
    //            "</td>\n" +
    //            "<td>478/2057</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14132\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14132\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14133\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14133\" target=\"_blank\">NC14133</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14133\" target=\"_blank\" class=\"title\">形态形成场</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1283\">枚举</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1285\">状压dp</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>23/62</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14133\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14133\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14134\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14134\" target=\"_blank\">NC14134</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14134\" target=\"_blank\" class=\"title\">生物课程</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1283\">枚举</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "1星\n" +
    //            "</td>\n" +
    //            "<td>437/1071</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14134\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14134\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14135\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14135\" target=\"_blank\">NC14135</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14135\" target=\"_blank\" class=\"title\">绝对半径2051</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1283\">枚举</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "2星\n" +
    //            "</td>\n" +
    //            "<td>229/714</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14135\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14135\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14136\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14136\" target=\"_blank\">NC14136</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14136\" target=\"_blank\" class=\"title\">监视任务</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1297\">线段树</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>292/880</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14136\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14136\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14247\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14247\" target=\"_blank\">NC14247</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14247\" target=\"_blank\" class=\"title\">Xorto</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1283\">枚举</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "2星\n" +
    //            "</td>\n" +
    //            "<td>683/2145</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14247\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14247\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14248\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14248\" target=\"_blank\">NC14248</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14248\" target=\"_blank\" class=\"title\">Treepath</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1310\">树</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "2星\n" +
    //            "</td>\n" +
    //            "<td>856/2165</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14248\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14248\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14250\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14250\" target=\"_blank\">NC14250</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14250\" target=\"_blank\" class=\"title\">MMSet2</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1310\">树</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>407/1497</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14250\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14250\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14251\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14251\" target=\"_blank\">NC14251</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14251\" target=\"_blank\" class=\"title\">Cut</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1283\">枚举</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>70/211</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14251\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14251\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14254\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14254\" target=\"_blank\">NC14254</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14254\" target=\"_blank\" class=\"title\">Color</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1264\">深度优先搜索(DFS)</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "5星\n" +
    //            "</td>\n" +
    //            "<td>113/602</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14254\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14254\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14266\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14266\" target=\"_blank\">NC14266</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14266\" target=\"_blank\" class=\"title\">Laptop</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1276\">排序</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "2星\n" +
    //            "</td>\n" +
    //            "<td>2019/5095</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14266\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14266\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14268\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14268\" target=\"_blank\">NC14268</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14268\" target=\"_blank\" class=\"title\">Distance</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1582\">思维题</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "2星\n" +
    //            "</td>\n" +
    //            "<td>628/2264</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14268\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14268\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14269\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14269\" target=\"_blank\">NC14269</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14269\" target=\"_blank\" class=\"title\">Sum</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1297\">线段树</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>183/682</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14269\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14269\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14270\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14270\" target=\"_blank\">NC14270</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14270\" target=\"_blank\" class=\"title\">Fancy Signal Translate</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1274\">哈希</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "2星\n" +
    //            "</td>\n" +
    //            "<td>251/613</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14270\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14270\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14271\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14271\" target=\"_blank\">NC14271</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14271\" target=\"_blank\" class=\"title\">Factorial Surplus Tail</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1301\">动态规划</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "5星\n" +
    //            "</td>\n" +
    //            "<td>40/135</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14271\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14271\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14272\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14272\" target=\"_blank\">NC14272</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14272\" target=\"_blank\" class=\"title\">Fantasy Strange Tree</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1301\">动态规划</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1310\">树</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>46/88</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14272\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14272\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14291\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14291\" target=\"_blank\">NC14291</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14291\" target=\"_blank\" class=\"title\">Cut</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1279\">贪心</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "1星\n" +
    //            "</td>\n" +
    //            "<td>1479/3719</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14291\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14291\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14292\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14292\" target=\"_blank\">NC14292</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14292\" target=\"_blank\" class=\"title\">Travel</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1294\">最短路</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>296/1997</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14292\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14292\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14293\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14293\" target=\"_blank\">NC14293</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14293\" target=\"_blank\" class=\"title\">Delete</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1294\">最短路</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1309\">拓扑排序</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "5星\n" +
    //            "</td>\n" +
    //            "<td>67/378</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14293\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14293\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14294\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14294\" target=\"_blank\">NC14294</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14294\" target=\"_blank\" class=\"title\">Butterfly</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1291\">并查集</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1297\">线段树</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "3星\n" +
    //            "</td>\n" +
    //            "<td>256/1795</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14294\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14294\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14295\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14295\" target=\"_blank\">NC14295</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14295\" target=\"_blank\" class=\"title\">Mod</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1286\">前缀和</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "5星\n" +
    //            "</td>\n" +
    //            "<td>5/86</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14295\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14295\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "<tr data-problemId=\"14300\">\n" +
    //            "<td>\n" +
    //            "<span class=\"ico-todo\">未提交</span>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14300\" target=\"_blank\">NC14300</a>\n" +
    //            "</td>\n" +
    //            "<td class=\"fn-right\" colspan=\"2\">\n" +
    //            "<a href=\"/acm/problem/14300\" target=\"_blank\" class=\"title\">Palindrome</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1270\">堆/优先队列</a>\n" +
    //            "<a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1297\">线段树</a>\n" +
    //            "</td>\n" +
    //            "<td>\n" +
    //            "4星\n" +
    //            "</td>\n" +
    //            "<td>52/267</td>\n" +
    //            "<td>\n" +
    //            "<a href=\"/acm/problem/14300\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
    //            "<a href=\"javascript:void(0);\" data-id=\"14300\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
    //            "</td>\n" +
    //            "</tr>\n" +
    //            "</tbody>\n" +
    //            "</table>\n" +
    //            "<div class=\"pagination\">\n" +
    //            "<span style=\"letter-spacing: 0;\n" +
    //            "vertical-align: bottom;\n" +
    //            "line-height: 32px;\">\n" +
    //            "共 21171 条\n" +
    //            "</span>\n" +
    //            "<ul data-total=\"424\" style=\"display:inline-block\">\n" +
    //            "<li class=\"txt-pager js-first-pager\"><a data-page=\"1\" href=\"?keyword=&tagId=&platformTagId=0&sourceTagId=0&difficulty=0&status=all&order=id&asc=true&pageSize=50&page=1\">首页</a></li>\n" +
    //            "<li class=\"txt-pager js-pre-pager\"><a data-page=\"1\"\n" +
    //            "href=\"?keyword=&tagId=&platformTagId=0&sourceTagId=0&difficulty=0&status=all&order=id&asc=true&pageSize=50&page=1\">上一页</a></li>\n" +
    //            "<li class=\"js-1-pager\"><a href=\"?keyword=&tagId=&platformTagId=0&sourceTagId=0&difficulty=0&status=all&order=id&asc=true&pageSize=50&page=1\" data-page=\"1\">1</a></li>\n" +
    //            "<li class=\"active js-2-pager\"><a href=\"javascript:void(0)\" data-page=\"2\">2</a></li>\n" +
    //            "<li class=\"js-3-pager\"><a href=\"?keyword=&tagId=&platformTagId=0&sourceTagId=0&difficulty=0&status=all&order=id&asc=true&pageSize=50&page=3\" data-page=\"3\">3</a></li>\n" +
    //            "<li class=\"js-4-pager\"><a href=\"?keyword=&tagId=&platformTagId=0&sourceTagId=0&difficulty=0&status=all&order=id&asc=true&pageSize=50&page=4\" data-page=\"4\">4</a></li>\n" +
    //            "<li class=\"js-5-pager\"><a href=\"?keyword=&tagId=&platformTagId=0&sourceTagId=0&difficulty=0&status=all&order=id&asc=true&pageSize=50&page=5\" data-page=\"5\">5</a></li>\n" +
    //            "<li class=\"js-6-pager\"><a href=\"?keyword=&tagId=&platformTagId=0&sourceTagId=0&difficulty=0&status=all&order=id&asc=true&pageSize=50&page=6\" data-page=\"6\">6</a></li>\n" +
    //            "<li class=\"js-7-pager\"><a href=\"?keyword=&tagId=&platformTagId=0&sourceTagId=0&difficulty=0&status=all&order=id&asc=true&pageSize=50&page=7\" data-page=\"7\">7</a></li>\n" +
    //            "<li class=\"js-8-pager\"><a href=\"?keyword=&tagId=&platformTagId=0&sourceTagId=0&difficulty=0&status=all&order=id&asc=true&pageSize=50&page=8\" data-page=\"8\">8</a></li>\n" +
    //            "<li class=\"txt-pager js-next-pager\"><a data-page=\"3\" href=\"?keyword=&tagId=&platformTagId=0&sourceTagId=0&difficulty=0&status=all&order=id&asc=true&pageSize=50&page=3\">下一页</a></li>\n" +
    //            "<li class=\"txt-pager js-last-pager\"><a data-page=\"424\" href=\"?keyword=&tagId=&platformTagId=0&sourceTagId=0&difficulty=0&status=all&order=id&asc=true&pageSize=50&page=424\">末页</a></li>\n" +
    //            "</ul>\n" +
    //            "</div>\n" +
    //            "</div>\n" +
    //            "</div>\n" +
    //            "</div>\n" +
    //            "<div class=\"nk-bar\">\n" +
    //            "<div class=\"module-box tags-module\">\n" +
    //            "<div class=\"module-head clearfix\"><h1>热门题单</h1></div>\n" +
    //            "<div class=\"module-body\">\n" +
    //            "<li>\n" +
    //            "<a href=\"/acm/archive/oi-advance\" target=\"_blank\">\n" +
    //            "<div class=\"pic\"><img src=\"https://uploadfiles.nowcoder.com/images/20190718/9254955_1563441237946_056728955EB37BFB05D3E38C2D69C9AC\"></div>\n" +
    //            "<div class=\"cont\"><h4>《算法竞赛进阶指南》题目练习</h4><p>255题</p></div>\n" +
    //            "</a>\n" +
    //            "</li>\n" +
    //            "<li>\n" +
    //            "<a href=\"/acm/noip/chusai-pj\" target=\"_blank\">\n" +
    //            "<div class=\"pic\"><img src=\"https://uploadfiles.nowcoder.com/images/20190226/744792398_1551169530733_2B195D74C34AEAE7A5E6445B99A2FA4F\"></div>\n" +
    //            "<div class=\"cont\"><h4>NOIP初赛普及组历史真题练习</h4><p>639题</p></div>\n" +
    //            "</a>\n" +
    //            "</li>\n" +
    //            "<li>\n" +
    //            "<a href=\"/acm/noip/fusai-pj\" target=\"_blank\">\n" +
    //            "<div class=\"pic\"><img src=\"https://uploadfiles.nowcoder.com/images/20190226/744792398_1551169568032_E768D041283007AFC6F1B546688F1ED2\"></div>\n" +
    //            "<div class=\"cont\"><h4>NOIP复赛普及组历史真题练习</h4><p>75题</p></div>\n" +
    //            "</a>\n" +
    //            "</li>\n" +
    //            "<li>\n" +
    //            "<a href=\"/acm/noip/chusai-tg\" target=\"_blank\">\n" +
    //            "<div class=\"pic\"><img src=\"https://uploadfiles.nowcoder.com/images/20190226/744792398_1551169530733_2B195D74C34AEAE7A5E6445B99A2FA4F\"></div>\n" +
    //            "<div class=\"cont\"><h4>NOIP初赛提高组历史真题练习</h4><p>658题</p></div>\n" +
    //            "</a>\n" +
    //            "</li>\n" +
    //            "<li>\n" +
    //            "<a href=\"/acm/noip/fusai-tg\" target=\"_blank\">\n" +
    //            "<div class=\"pic\"><img src=\"https://uploadfiles.nowcoder.com/images/20190226/744792398_1551169568032_E768D041283007AFC6F1B546688F1ED2\"></div>\n" +
    //            "<div class=\"cont\"><h4>NOIP复赛提高组历史真题练习</h4><p>93题</p></div>\n" +
    //            "</a>\n" +
    //            "</li>\n" +
    //            "<li>\n" +
    //            "<a href=\"/acm/archive/noisx\" target=\"_blank\">\n" +
    //            "<div class=\"pic\"><img src=\"https://uploadfiles.nowcoder.com/images/20190212/9254955_1549971033404_6287C52970BCD1627748DB03A9F345C0\"></div>\n" +
    //            "<div class=\"cont\"><h4>省选历年真题练习</h4><p>637题</p></div>\n" +
    //            "</a>\n" +
    //            "</li>\n" +
    //            "<li>\n" +
    //            "<a href=\"/acm/archive/noi\" target=\"_blank\">\n" +
    //            "<div class=\"pic\"><img src=\"https://uploadfiles.nowcoder.com/images/20190212/9254955_1549971026862_FDFD702E0F641536B9C515A815064472\"></div>\n" +
    //            "<div class=\"cont\"><h4>NOI历史真题练习</h4><p>106题</p></div>\n" +
    //            "</a>\n" +
    //            "</li>\n" +
    //            "<li>\n" +
    //            "<a href=\"/acm/archive/usaco\" target=\"_blank\">\n" +
    //            "<div class=\"pic\"><img src=\"https://uploadfiles.nowcoder.com/images/20190212/9254955_1549971040091_5552C9C5BCD2CB9A7DF5FEB1250D77BF\"></div>\n" +
    //            "<div class=\"cont\"><h4>USACO训练题（中文版）</h4><p>100题</p></div>\n" +
    //            "</a>\n" +
    //            "</li>\n" +
    //            "<li>\n" +
    //            "<a href=\"/acm/archive/usaco-month\" target=\"_blank\">\n" +
    //            "<div class=\"pic\"><img src=\"https://uploadfiles.nowcoder.com/images/20190212/9254955_1549971040091_5552C9C5BCD2CB9A7DF5FEB1250D77BF\"></div>\n" +
    //            "<div class=\"cont\"><h4>USACO月赛题（中/英版）</h4><p>687题</p></div>\n" +
    //            "</a>\n" +
    //            "</li>\n" +
    //            "<li>\n" +
    //            "<a href=\"/acm/archive/foreign-oi\" target=\"_blank\">\n" +
    //            "<div class=\"pic\"><img src=\"https://uploadfiles.nowcoder.com/images/20191023/11000112_1571820910195_FEFE721643CB0A578173F011AE6A2506\"></div>\n" +
    //            "<div class=\"cont\"><h4>JOI、JOISC、ROI等国外OI真题练习</h4><p>100题</p></div>\n" +
    //            "</a>\n" +
    //            "</li>\n" +
    //            "<li>\n" +
    //            "<a href=\"https://ac.nowcoder.com/discuss/159885\" target=\"_blank\">\n" +
    //            "<div class=\"pic\"><img src=\"https://uploadfiles.nowcoder.com/images/20190314/6927804_1552530616947_1543967BA4BD29180855D6F51975A0AD\"></div>\n" +
    //            "<div class=\"cont\"><h4>“神犇”级真题练习</h4><p>1024题</p></div>\n" +
    //            "</a>\n" +
    //            "</li>\n" +
    //            "</ul>\n" +
    //            "<a href=\"/acm/problem/collection/list\" class=\"link-show-all\" target=\"_blank\">查看全部</a>\n" +
    //            "</div>\n" +
    //            "</div>\n" +
    //            "<div class=\"module-box tags-module\">\n" +
    //            "<div class=\"module-head clearfix\"><h1>热门知识点</h1></div>\n" +
    //            "<div class=\"module-body\">\n" +
    //            "<div class=\"tags-box\">\n" +
    //            "<a href=\"/acm/skill/detail/noip-tg/1582\" target=\"_blank\" class=\"tag-label\">思维题</a>\n" +
    //            "<a href=\"/acm/skill/detail/noip-tg/1594\" target=\"_blank\" class=\"tag-label\">网络流</a>\n" +
    //            "<a href=\"/acm/skill/detail/noip-tg/1627\" target=\"_blank\" class=\"tag-label\">数论</a>\n" +
    //            "<a href=\"/acm/skill/detail/noip-tg/1604\" target=\"_blank\" class=\"tag-label\">容斥原理</a>\n" +
    //            "<a href=\"/acm/skill/detail/noip-tg/1558\" target=\"_blank\" class=\"tag-label\">STL</a>\n" +
    //            "<a href=\"/acm/skill/detail/noip-tg/1619\" target=\"_blank\" class=\"tag-label\">多项式算法</a>\n" +
    //            "<a href=\"/acm/skill/detail/noip-tg/1608\" target=\"_blank\" class=\"tag-label\">搜索优化</a>\n" +
    //            "<a href=\"/acm/skill/detail/noip-tg/1587\" target=\"_blank\" class=\"tag-label\">构造题</a>\n" +
    //            "<a href=\"/acm/skill/detail/noip-tg/1570\" target=\"_blank\" class=\"tag-label\">分治</a>\n" +
    //            "<a href=\"/acm/skill/detail/noip-tg/1567\" target=\"_blank\" class=\"tag-label\">tarjan</a>\n" +
    //            "</div>\n" +
    //            "<a href=\"/acm/skill/noip-tg\" target=\"_blank\" class=\"link-show-all\">查看全部</a>\n" +
    //            "</div>\n" +
    //            "</div>\n" +
    //            "</div>\n" +
    //            "</div>\n" +
    //            "</div>\n" +
    //            "            \n" +
    //            "        <div class=\"fixed-menu\">\n" +
    //            "        <ul>\n" +
    //            "            <li>\n" +
    //            "                <a href=\"javascript:void(0);\" class=\"gotop js-nav-go-top\" title=\"回到顶部\"></a>\n" +
    //            "            </li>\n" +
    //            "            <li>\n" +
    //            "                <a class=\"fixed-wb\" target=\"_blank\" href=\"http://www.weibo.com/nowcoder\"></a>\n" +
    //            "                                                </li>\n" +
    //            "            <li>\n" +
    //            "                <a href=\"tencent://groupwpa/?subcmd=all&param=7B2267726F757055696E223A3135373539343730352C2274696D655374616D70223A313431333130373737387D0A\" class=\"qq\" title=\"QQ\"></a>\n" +
    //            "            </li>\n" +
    //            "            <li>\n" +
    //            "                <a href=\"javascript:void(0);\" class=\"wx\"></a>\n" +
    //            "                <div class=\"wx-qrcode\">\n" +
    //            "                    <img src=\"//static.nowcoder.com/images/wx-rcode.jpg\" alt=\"二维码\" />\n" +
    //            "                    <p>扫描二维码，关注牛客网</p>\n" +
    //            "                </div>\n" +
    //            "            </li>\n" +
    //            "            <li>\n" +
    //            "                <a href=\"https://www.nowcoder.com/subject/index/b211632d69fd42f7b55aae38d4242fda\" class=\"feedback\" title=\"意见反馈\"></a>\n" +
    //            "                <a href=\"https://www.nowcoder.com/subject/index/b211632d69fd42f7b55aae38d4242fda\" class=\"feedback-letter\">意见反馈</a>\n" +
    //            "            </li>\n" +
    //            "            <li>\n" +
    //            "                <a href=\"javascript:void(0);\" class=\"qrcode\"></a>\n" +
    //            "                <div class=\"wx-qrcode\">\n" +
    //            "                    <img src=\"//uploadfiles.nowcoder.com/app/web_app_download.png\" alt=\"二维码\" />\n" +
    //            "                    <p>下载牛客APP，随时随地刷题</p>\n" +
    //            "                </div>\n" +
    //            "            </li>\n" +
    //            "        </ul>\n" +
    //            "                        </div>\n" +
    //            "    \n" +
    //            "<div class=\"acm-ft-wrap js-nowcoder-footer\">\n" +
    //            "            <div class=\"fixed-foot clearfix js-fixed-foot\" style=\"display:none;\">\n" +
    //            "            <div class=\"fixed-foot-main\">\n" +
    //            "                <div class=\"fixed-foot-tip\">刷真题、补算法、看面经、得内推</div>\n" +
    //            "                <div class=\"fixed-foot-login\">\n" +
    //            "                    <span>使用第三方账号直接登录使用吧：</span>\n" +
    //            "                    <a href=\"javascript:void(0);\" data-href=\"https://api.weibo.com/oauth2/authorize?client_id=3023520088&redirect_uri=http%3A%2F%2Fwww.nowcoder.com%2Foauth2%2Fsinaconfig&response_type=code&state=web&scope=follow_app_official_microblog\" class=\"ft-login-item ft-login-wb nc-js-action-oauth\" title=\"登录微博\"></a>\n" +
    //            "                    <a href=\"javascript:void(0);\" data-href=\"https://graph.qq.com/oauth2.0/authorize?client_id=101003590&redirect_uri=http%3A%2F%2Fwww.nowcoder.com%2Foauth2%2Fqqconfig&response_type=code&state=web\" class=\"ft-login-item ft-login-qz nc-js-action-oauth\" title=\"登录Qzone\"></a>\n" +
    //            "                                    <a href=\"javascript:void(0);\" data-href=\"/oauth2/login/wechat_mp_index\" class=\"ft-login-item ft-login-wx nc-js-action-oauth\" title=\"登录微信\"></a>\n" +
    //            "                    <a href=\"javascript:void(0);\" data-href=\"https://github.com/login/oauth/authorize?client_id=1c539827b9400016d0c9&scope=user:email&redirect_uri=http%3A%2F%2Fwww.nowcoder.com%2Foauth2%2Fgitconfig&response_type=code&state=web\" class=\"ft-login-item ft-login-git nc-js-action-oauth\" title=\"登录git\"></a>\n" +
    //            "                    <a href=\"javascript:void(0);\" class=\"more-login nc-req-auth\">更多</a>\n" +
    //            "                </div>\n" +
    //            "            </div>\n" +
    //            "        </div>\n" +
    //            "    \n" +
    //            "    <div class=\"acm-ft-cont clearfix\">\n" +
    //            "        <dl class=\"acm-ft-web-info\">\n" +
    //            "            <dt class=\"acm-ft-web-name\">牛客竞赛，专业的竞技算法训练平台</dt>\n" +
    //            "            <dd>\n" +
    //            "                <a href=\"javascript:void(0);\" class=\"ft-qq-ico\">\n" +
    //            "                    <div class=\"tooltip top\">\n" +
    //            "                        <div class=\"tooltip-arrow\"></div>\n" +
    //            "                        <div class=\"tooltip-inner\">\n" +
    //            "                            <img width=\"110\" src=\"//uploadfiles.nowcoder.com/images/20190505/56_1557038918812_4D7DC2E135102E1A73BC7FB4746507BA\">\n" +
    //            "                            <p>扫描二维码，进入QQ群</p>\n" +
    //            "                        </div>\n" +
    //            "                    </div>\n" +
    //            "                </a>\n" +
    //            "                <a href=\"javascript:void(0);\" class=\"ft-wx-ico\">\n" +
    //            "                    <div class=\"tooltip top\">\n" +
    //            "                        <div class=\"tooltip-arrow\"></div>\n" +
    //            "                        <div class=\"tooltip-inner\">\n" +
    //            "                            <img width=\"110\" src=\"//uploadfiles.nowcoder.com/images/20171109/68_1510217575727_4FA2AAE7EB5CCAF0C9D0DEA16D00A11C\">\n" +
    //            "                            <p>扫码关注“比赛自动姬”</p>\n" +
    //            "                        </div>\n" +
    //            "                    </div>\n" +
    //            "                </a>\n" +
    //            "                <a href=\"https://weibo.com/nowcoder\" class=\"ft-wb-ico\" target=\"_blank\"></a>\n" +
    //            "                <a href=\"https://www.zhihu.com/org/niu-ke-wang-53/activities\" class=\"ft-zh-ico\" target=\"_blank\"></a>\n" +
    //            "            </dd>\n" +
    //            "        </dl>\n" +
    //            "        <div class=\"txt-center\">\n" +
    //            "            <ul class=\"acm-ft-links\">\n" +
    //            "                <li><a href=\"//www.nowcoder.com/nowcoder/about\">关于我们</a></li>\n" +
    //            "                <li><a href=\"/nowcoder/recruitment\">加入我们</a></li>\n" +
    //            "                <li><a href=\"//www.nowcoder.com/subject/index/b211632d69fd42f7b55aae38d4242fda\">意见反馈</a></li>\n" +
    //            "                <li><a href=\"//hr.nowcoder.com\">企业服务</a></li>\n" +
    //            "                <li><a href=\"/nowcoder/school-cooperation\">校企合作</a></li>\n" +
    //            "                <li><a href=\"/html/cooperation\">联系我们</a></li>\n" +
    //            "                <li><a href=\"/html/disclaimer\">免责声明</a></li>\n" +
    //            "                <li><a href=\"/html/links\">友情链接</a></li>\n" +
    //            "            </ul>\n" +
    //            "            <ul class=\"webrights\">\n" +
    //            "                <li>公司地址：北京市朝阳区北苑路北美国际商务中心K2座一层-北京牛客科技有限公司</li>\n" +
    //            "                <li>\n" +
    //            "                    <span>联系方式：<a href=\"tel:010-60728802\">010-60728802</a></span>\n" +
    //            "                    <span class=\"ml-2\">投诉举报电话：<a href=\"tel:010-57596212\">010-57596212</a>（朝阳人力社保局）</span>\n" +
    //            "                </li>\n" +
    //            "                <li>\n" +
    //            "                    <span>牛客科技©<span id=\"jsNCPageFooterYear\"></span> All rights reserved</span>\n" +
    //            "                    <a href=\"mailto:admin@nowcoder.com\" class=\"ml-4\">admin@nowcoder.com</a>\n" +
    //            "                </li>\n" +
    //            "                <li>\n" +
    //            "                    <a class=\"ft-info-item\" target=\"_blank\" href=\"//beian.miit.gov.cn\">京ICP备14055008号-4</a>\n" +
    //            "                    <a class=\"ft-info-item ml-1\" target=\"_blank\" href=\"//uploadfiles.nowcoder.com/acts/icp.png\">增值电信业务经营许可证</a>\n" +
    //            "                    <a class=\"ft-info-item ml-1\" target=\"_blank\" href=\"//static.nowcoder.com/protocol/license-center.html#business\">营业执照</a>\n" +
    //            "                    <a class=\"ft-info-item ml-1\" target=\"_blank\" href=\"//static.nowcoder.com/protocol/license-center.html#human-resources\">人力资源服务许可证</a>\n" +
    //            "                </li>\n" +
    //            "                <li>\n" +
    //            "                    <img src=\"//static.nowcoder.com/company/images/res/ghs.png\" height=\"18\">\n" +
    //            "                    <a class=\"ft-info-item\" target=\"_blank\" href=\"http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11010502036488\">京公网安备 11010502036488号</a>\n" +
    //            "                </li>\n" +
    //            "            </ul>\n" +
    //            "            <script>try{document.getElementById('jsNCPageFooterYear').innerHTML=new Date().getFullYear();}catch(e){}</script>\n" +
    //            "        </div>\n" +
    //            "\n" +
    //            "    </div>\n" +
    //            "</div>\n" +
    //            "\n" +
    //            "\n" +
    //            "            <div class=\"living-layer js-global-tips\" style=\"display:none;\">\n" +
    //            "        <a href=\"javascript:void(0);\" class=\"icon-nc-cancel\"></a>\n" +
    //            "        <a class=\"cont js-global-tips-href\" href=\"\" target=\"_blank\">\n" +
    //            "            <i class=\"icon-nc-play\"></i>\n" +
    //            "            <div class=\"living-layer-cont\">\n" +
    //            "                <p class=\"living-layer-title\"></p>\n" +
    //            "                <p></p>\n" +
    //            "            </div>\n" +
    //            "        </a>\n" +
    //            "    </div>\n" +
    //            "    \n" +
    //            "\n" +
    //            "</div>\n" +
    //            "    <script>\n" +
    //            "window.isLogin = false;\n" +
    //            "window.globalInfo = {\n" +
    //            "canLoadInIframe: 'true'\n" +
    //            "};\n" +
    //            "window.useNeteaseCaptcha = true;\n" +
    //            "window.globalInfo.isAcmCompleteInfo = false;\n" +
    //            "window.globalInfo.isAcmSuperAdmin = false;\n" +
    //            "window.globalInfo.acmUserPlanType = 2;\n" +
    //            "window.globalInfo.hasAcmUserPlanType = false;\n" +
    //            "window.showCodeCompleteV2 = true;\n" +
    //            "</script>\n" +
    //            "<script type=\"text/javascript\">\n" +
    //            "window.ncDeployImageRoot = \"//static.nowcoder.com/acm\";\n" +
    //            "window.ncJsInDev =  false ;\n" +
    //            "window.ncJsVersion = \"1.03.12\";\n" +
    //            "window.ncJsPluginVersion = \"1.0.17\";\n" +
    //            "window.ncIsInNowcoderMainSite = true;\n" +
    //            "</script>\n" +
    //            "<script type=\"text/javascript\">\n" +
    //            "(function () {\n" +
    //            "var oBody = document.body;\n" +
    //            "var sOverflow = oBody.style.overflow;\n" +
    //            "var sUa = (window.navigator || {}).userAgent || '';\n" +
    //            "var m = sUa.match(/MSIE ([^;]*)|Trident.*; rv(?:\\s|:)?([0-9.]+)/) || [];\n" +
    //            "var nVersion = +m[1] || +m[2] || 0;\n" +
    //            "nVersion && nVersion <= 8 && _fShowMask();\n" +
    //            "function _fShowMask() {\n" +
    //            "oBody.style.overflow = 'hidden';\n" +
    //            "var nWidth = window.document.documentElement.clientWidth;\n" +
    //            "var nHeight = window.document.documentElement.clientHeight;\n" +
    //            "var nTop = ('pageYOffset' in window ? window.pageYOffset : document.documentElement.scrollTop) || 0;\n" +
    //            "var sHtml = [\n" +
    //            "'<div style=\"position:absolute;left:0;top:' + nTop + 'px;z-index:99999;width:' + nWidth + 'px;height:' + nHeight + 'px;\">',\n" +
    //            "'<div style=\"position:absolute;left:0;top:0;width:100%;height:100%;background:#000;opacity:0.5;filter:alpha(opacity=50);\"></div>',\n" +
    //            "'<div class=\"pop-box\" style=\"width:800px;z-index:100000;left:' + (nWidth - 800) / 2 + 'px;top:' + Math.max(0, (nHeight - 624) / 2) + nTop + 'px;\">',\n" +
    //            "'<div class=\"pop-title\"><h1>浏览器版本过低提示</h1><a href=\"javascript:void(0);\" onclick=\"closeIE8GlobalMask()\" class=\"pop-close\" title=\"关闭\"></a></div>',\n" +
    //            "'<div class=\"pop-content clearfix\">',\n" +
    //            "'<div class=\"pop-subject-tips-wrapper\">',\n" +
    //            "'<a href=\"javascript:void(0);\"><img src=\"//static.nowcoder.com/fe/style/images/common/res/tips/13.png\"></a>',\n" +
    //            "'<div class=\"pop-exit-main\">你使用的浏览器版本过低，无法正常访问牛客<br />请使用牛客APP或更换浏览器。</div>',\n" +
    //            "'<div class=\"pop-download-browser\">',\n" +
    //            "'<ul class=\"pop-browser-list\">',\n" +
    //            "'<li><p>下载牛客APP</p><div class=\"pic\"><img src=\"https://uploadfiles.nowcoder.com/app/app_download.png\"></div></li>',\n" +
    //            "'<li><a href=\"https://static.nowcoder.com/b/i/googlechrome74_32.exe\" target=\"_blank\"><p>下载Chrome浏览器</p><div class=\"pic\"><img src=\"//static.nowcoder.com/images/res/browser/chrome.png\"></div></a></li>',\n" +
    //            "'<li><a href=\"https://www.firefox.com.cn/download/#more\" target=\"_blank\"><p>下载Firefox浏览器</p><div class=\"pic\"><img src=\"//static.nowcoder.com/images/res/browser/firefox.png\"></div></a></li>',\n" +
    //            "'</ul>',\n" +
    //            "'<a href=\"javascript:void(0);\" onclick=\"closeIE8GlobalMask()\" class=\"link-green\">我知道访问会出错，但是我还要继续用这个浏览器浏览 &gt;&gt;</a>',\n" +
    //            "'</div>',\n" +
    //            "'</div>',\n" +
    //            "'</div>',\n" +
    //            "'</div>',\n" +
    //            "'</div>'].join('');\n" +
    //            "var oDv = document.createElement('div');\n" +
    //            "oDv.innerHTML = sHtml;\n" +
    //            "oBody.appendChild(oDv);\n" +
    //            "window.closeIE8GlobalMask = function () {\n" +
    //            "oBody.removeChild(oDv);\n" +
    //            "oBody.style.overflow = sOverflow;\n" +
    //            "_fShowBar();\n" +
    //            "};\n" +
    //            "}\n" +
    //            "function _fShowBar() {\n" +
    //            "var oDv = document.createElement('div');\n" +
    //            "oDv.innerHTML = '<div class=\"tip-block-box\" onclick=\"closeIE8GlobalBar()\" style=\"position:fixed;left:0;top:54px;width:100%;z-index:99999;\"><div class=\"tip-block\">你使用的浏览器版本过低，无法正常访问牛客，请使用牛客APP或更换浏览器，<a href=\"javascript:void(0);\">点击查看详情</a>。</div></div>';\n" +
    //            "oBody.appendChild(oDv);\n" +
    //            "window.closeIE8GlobalBar  = function () {\n" +
    //            "oBody.removeChild(oDv);\n" +
    //            "_fShowMask();\n" +
    //            "};\n" +
    //            "}\n" +
    //            "})();\n" +
    //            "</script>\n" +
    //            "<script src=\"//static.nowcoder.com/acm/nowcoder/1.0.497/javascripts-acm/lib/vendor.dll.js\" type=\"text/javascript\"></script>\n" +
    //            "<script type=\"text/javascript\">\n" +
    //            "if (!window.seajs && !window.vendor_library) {\n" +
    //            "var oBody = document.body;\n" +
    //            "var aChild = oBody.childNodes;\n" +
    //            "var oDv = document.createElement('div');\n" +
    //            "oDv.innerHTML = [\n" +
    //            "'<div style=\"text-align:center;\">',\n" +
    //            "'<div style=\"font-size:18px;margin:20px 0;font-weight: bold;\">',\n" +
    //            "'因你使用的网络运营商限制，导致静态文件加载出错，如果想要彻底解决问题，请联系你使用的网络运营商（如运营商不处理可以前往工信部投诉），也可以按照以下步骤修改电脑的DNS',\n" +
    //            "'<br />推荐使用以下DNS: 223.5.5.5(首选) 和 119.29.29.29(备用)',\n" +
    //            "'</div>',\n" +
    //            "'<img style=\"border:1px solid #ccc;\" src=\"//qqstatic.nowcoder.com/files/cdn.jpg\" />',\n" +
    //            "'</div>'].join('');\n" +
    //            "aChild.length === 0 && oBody.appendChild(oDv);\n" +
    //            "aChild.length > 0 && oBody.insertBefore(oDv, aChild[0]);\n" +
    //            "}\n" +
    //            "</script>\n" +
    //            "<script src=\"//static.nowcoder.com/acm/nowcoder/1.0.497/javascripts-acm/lib/runtime.js\" type=\"text/javascript\"></script>\n" +
    //            "<script src=\"//static.nowcoder.com/acm/nowcoder/1.0.497/javascripts-acm/lib/common.js\" type=\"text/javascript\"></script>\n" +
    //            "<script src=\"//static.nowcoder.com/acm/nowcoder/1.0.497/javascripts-acm/lib/env.js\" type=\"text/javascript\"></script>\n" +
    //            "<script type=\"text/javascript\">\n" +
    //            "var _hmt = _hmt || [];\n" +
    //            "(function() {\n" +
    //            "var hm = document.createElement(\"script\");\n" +
    //            "hm.src = \"//hm.baidu.com/hm.js?a808a1326b6c06c437de769d1b85b870\";\n" +
    //            "var s = document.getElementsByTagName(\"script\")[0];\n" +
    //            "s.parentNode.insertBefore(hm, s);\n" +
    //            "})();\n" +
    //            "(function () {\n" +
    //            "!function(e,t,n,g,i){e[i]=e[i]||function(){(e[i].q=e[i].q||[]).push(arguments)},n=t.createElement(\"script\"),tag=t.getElementsByTagName(\"script\")[0],n.async=1,n.src=('https:'==document.location.protocol?'https://':'http://')+g,tag.parentNode.insertBefore(n,tag)}(window,document,\"script\",\"assets.growingio.com/2.1/gio.js\",\"gio\");\n" +
    //            "gio('init', 'c196c3667d214851b11233f5c17f99d5', {});\n" +
    //            "gio('config',  {host : 'grow.nowcoder.com'});\n" +
    //            "gio('clearUserId');\n" +
    //            "gio('send');\n" +
    //            "})();\n" +
    //            "</script>\n" +
    //            "<script type=\"text/javascript\">\n" +
    //            "window.pageInfo = window.pageInfo || {};\n" +
    //            "window.pageInfo.extendGreatHan = {\n" +
    //            "keyword: '', //搜索关键词\n" +
    //            "tagId: \"\", //逗号分隔，知识点tagId。0表示不筛选，下同\n" +
    //            "platformTagId: \"0\", //平台标签tagId\n" +
    //            "sourceTagId: \"0\", //逗号分隔，来源tagId\n" +
    //            "difficulty: \"0\", //难度，星级\n" +
    //            "order: 'id', //acceptRate,id,difficulty\n" +
    //            "asc: true, //是否正序\n" +
    //            "status: \"all\", //用户通过状态筛选\n" +
    //            "acCount: 0, //通过\n" +
    //            "failedCount: 0, //尝试中；失败\n" +
    //            "submitCount: 0, //提交题目数\n" +
    //            "unsubmittedCount: 0, //未提交\n" +
    //            "acRate: 0.0, //通过率,非百分比\n" +
    //            "allPlatformTagArray : [{\"name\":\"牛客\",\"id\":4245},{\"name\":\"UVALive\",\"id\":3290},{\"name\":\"UVA\",\"id\":3289}], //所有平台标签tag的下拉\n" +
    //            "allSourceTagArray: [{\"name\":\"提高训练营\",\"id\":3278},{\"name\":\"牛客挑战赛\",\"id\":3279},{\"name\":\"OI赛前集训营\",\"id\":3280},{\"name\":\"OI周赛-提高组\",\"id\":3281},{\"name\":\"OI周赛-普及组\",\"id\":3282},{\"name\":\"牛客练习赛\",\"id\":3283},{\"name\":\"基础训练营\",\"id\":3284},{\"name\":\"高校赛\",\"id\":3285},{\"name\":\"牛客小白月赛\",\"id\":3286},{\"name\":\"其他比赛\",\"id\":3287},{\"name\":\"《信息学奥赛一本通-提高篇》\",\"id\":3295},{\"name\":\"《算法竞赛进阶指南》\",\"id\":3296},{\"name\":\"OI复赛普及组历年真题\",\"id\":3298},{\"name\":\"OI复赛提高组历年真题\",\"id\":3300},{\"name\":\"省选历年真题\",\"id\":3301},{\"name\":\"NOI历年真题\",\"id\":3302},{\"name\":\"USACO训练题（中文版）\",\"id\":3303},{\"name\":\"USACO月赛题（中/英版）\",\"id\":3304},{\"name\":\"JOI、JOISC、ROI等国外OI\",\"id\":3305},{\"name\":\"CSP知识点练习\",\"id\":3306},{\"name\":\"节日娱乐赛\",\"id\":14486},{\"name\":\"正式比赛题目\",\"id\":14485}], //所有来源tag的下拉\n" +
    //            "allDifficultyArray: [{id: 1, name: \"1星\"}, {id: 2, name: \"2星\"}, {id: 3, name: \"3星\"}, {id: 4, name: \"4星\"}, {id: 5, name: \"5星\"}], //下拉\n" +
    //            "allTagArray: [{\"childTags\":[{\"name\":\"排列组合\",\"id\":14419},{\"name\":\"母函数\",\"id\":14420},{\"name\":\"Polya定理\",\"id\":14421},{\"name\":\"组合数学\",\"id\":145467},{\"name\":\"置换群\",\"id\":145468},{\"name\":\"卢卡斯定理\",\"id\":145469},{\"name\":\"卡特兰数\",\"id\":145470},{\"name\":\"容斥原理与鸽巢原理\",\"id\":145471},{\"name\":\"斯坦纳树\",\"id\":145472},{\"name\":\"斯特林数\",\"id\":145473},{\"name\":\"二项式定理\",\"id\":145474},{\"name\":\"Burnside引理\",\"id\":145475},{\"name\":\"prufer序列\",\"id\":145476}],\"name\":\"组合数学\",\"id\":145410,\"isnew\":true},{\"childTags\":[{\"name\":\"线性代数\",\"id\":14422},{\"name\":\"矩阵的逆\",\"id\":14423},{\"name\":\"矩阵乘法\",\"id\":145477},{\"name\":\"线性基\",\"id\":145478},{\"name\":\"高斯消元\",\"id\":145479}],\"name\":\"线性代数\",\"id\":145411,\"isnew\":true},{\"childTags\":[{\"name\":\"最近公共祖先（LCA）\",\"id\":14430},{\"name\":\"最大团\",\"id\":14431},{\"name\":\"图论\",\"id\":145496},{\"name\":\"最短路\",\"id\":145497},{\"name\":\"图的遍历\",\"id\":145498},{\"name\":\"拓扑排序\",\"id\":145499},{\"name\":\"生成树\",\"id\":145500},{\"name\":\"费用流\",\"id\":145501},{\"name\":\"欧拉回路\",\"id\":145502},{\"name\":\"连通性\",\"id\":145503},{\"name\":\"网络流\",\"id\":145504},{\"name\":\"图匹配\",\"id\":145505}],\"name\":\"图论\",\"id\":145414,\"isnew\":true},{\"childTags\":[{\"name\":\"三维计算几何\",\"id\":14432},{\"name\":\"计算几何\",\"id\":145506},{\"name\":\"凸包\",\"id\":145507},{\"name\":\"半平面交\",\"id\":145508},{\"name\":\"最小圆覆盖\",\"id\":145509}],\"name\":\"计算几何\",\"id\":145415,\"isnew\":true},{\"childTags\":[{\"name\":\"费马小定理\",\"id\":14424},{\"name\":\"离散对数\",\"id\":14425},{\"name\":\"质因数分解\",\"id\":14426},{\"name\":\"欧拉函数\",\"id\":14427},{\"name\":\"gcd与exgcd\",\"id\":145480},{\"name\":\"素数筛\",\"id\":145481},{\"name\":\"素数判定\",\"id\":145482},{\"name\":\"逆元\",\"id\":145483},{\"name\":\"欧拉定理\",\"id\":145484},{\"name\":\"中国剩余定理\",\"id\":145485},{\"name\":\"数论\",\"id\":145607}],\"name\":\"数论\",\"id\":145412,\"isnew\":true},{\"childTags\":[{\"name\":\"数值积分\",\"id\":14428},{\"name\":\"进制转换\",\"id\":14429},{\"name\":\"数学\",\"id\":145486},{\"name\":\"多项式\",\"id\":145487},{\"name\":\"快速幂\",\"id\":145488},{\"name\":\"高精度\",\"id\":145489},{\"name\":\"bsgs算法\",\"id\":145490},{\"name\":\"快速傅里叶变换（FFT）/快速数论变换（NTT）\",\"id\":145491},{\"name\":\"博弈论\",\"id\":145492},{\"name\":\"概率期望\",\"id\":145493},{\"name\":\"莫比乌斯反演\",\"id\":145494},{\"name\":\"生成函数\",\"id\":145495},{\"name\":\"拉格朗日插值\",\"id\":145783}],\"name\":\"数学\",\"id\":145413,\"isnew\":true},{\"childTags\":[{\"name\":\"AC自动机\",\"id\":145538},{\"name\":\"kmp与扩展kmp\",\"id\":145539},{\"name\":\"字典树（Trie树）\",\"id\":145540},{\"name\":\"字符串\",\"id\":145541},{\"name\":\"后缀数组(SA)\",\"id\":145542},{\"name\":\"后缀自动机(SAM)\",\"id\":145543},{\"name\":\"Manacher\",\"id\":145544},{\"name\":\"后缀树\",\"id\":145545},{\"name\":\"Trie图\",\"id\":145546},{\"name\":\"字符串hash\",\"id\":145547},{\"name\":\"bm算法\",\"id\":145548},{\"name\":\"回文自动机(PAM)\",\"id\":145549}],\"name\":\"字符串\",\"id\":145418,\"isnew\":true},{\"childTags\":[{\"name\":\"语言题\",\"id\":145550}],\"name\":\"语言题\",\"id\":145419,\"isnew\":true},{\"childTags\":[{\"name\":\"树堆Treap\",\"id\":14433},{\"name\":\"块状链表\",\"id\":14434},{\"name\":\"数据结构\",\"id\":145510},{\"name\":\"动态树(LCT)\",\"id\":145511},{\"name\":\"堆/优先队列\",\"id\":145512},{\"name\":\"栈\",\"id\":145513},{\"name\":\"队列\",\"id\":145514},{\"name\":\"平衡树\",\"id\":145515},{\"name\":\"并查集\",\"id\":145516},{\"name\":\"树套树\",\"id\":145517},{\"name\":\"线段树\",\"id\":145518},{\"name\":\"单调队列单调栈\",\"id\":145519},{\"name\":\"树\",\"id\":145520},{\"name\":\"树链剖分\",\"id\":145521},{\"name\":\"树状数组\",\"id\":145522},{\"name\":\"KD Tree\",\"id\":145523},{\"name\":\"ST表\",\"id\":145524},{\"name\":\"分块\",\"id\":145525},{\"name\":\"莫队\",\"id\":145526},{\"name\":\"仙人掌\",\"id\":145527},{\"name\":\"左偏树/可并堆\",\"id\":145528},{\"name\":\"STL\",\"id\":145529},{\"name\":\"链表\",\"id\":145530},{\"name\":\"主席树\",\"id\":145531},{\"name\":\"伸展树Splay\",\"id\":145532},{\"name\":\"DSU on tree\",\"id\":145782}],\"name\":\"数据结构\",\"id\":145416,\"isnew\":true},{\"childTags\":[{\"name\":\"可持久化trie树\",\"id\":145533},{\"name\":\"可持久化堆\",\"id\":145534},{\"name\":\"可持久化平衡树\",\"id\":145535},{\"name\":\"可持久化线段树\",\"id\":145536},{\"name\":\"可持久化数据结构\",\"id\":145537}],\"name\":\"可持久化数据结构\",\"id\":145417,\"isnew\":true},{\"childTags\":[{\"name\":\"贪心\",\"id\":145553}],\"name\":\"贪心\",\"id\":145422,\"isnew\":true},{\"childTags\":[{\"name\":\"递归\",\"id\":145554}],\"name\":\"递归\",\"id\":145423,\"isnew\":true},{\"childTags\":[{\"name\":\"构造\",\"id\":145551}],\"name\":\"构造\",\"id\":145420,\"isnew\":true},{\"childTags\":[{\"name\":\"排序\",\"id\":145552},{\"name\":\"归并\",\"id\":146430}],\"name\":\"排序\",\"id\":145421,\"isnew\":true},{\"childTags\":[{\"name\":\"倍增\",\"id\":145563}],\"name\":\"倍增\",\"id\":145426,\"isnew\":true},{\"childTags\":[{\"name\":\"哈希\",\"id\":145564}],\"name\":\"哈希\",\"id\":145427,\"isnew\":true},{\"childTags\":[{\"name\":\"递推\",\"id\":145555}],\"name\":\"递推\",\"id\":145424,\"isnew\":true},{\"childTags\":[{\"name\":\"二分\",\"id\":145556},{\"name\":\"cdq分治\",\"id\":145557},{\"name\":\"树分治\",\"id\":145558},{\"name\":\"01分数规划\",\"id\":145559},{\"name\":\"分治\",\"id\":145560},{\"name\":\"整体二分\",\"id\":145561},{\"name\":\"三分\",\"id\":145562}],\"name\":\"分治\",\"id\":145425,\"isnew\":true},{\"childTags\":[{\"name\":\"差分\",\"id\":14435},{\"name\":\"状压枚举\",\"id\":14436},{\"name\":\"枚举\",\"id\":145567},{\"name\":\"前缀和\",\"id\":145568},{\"name\":\"位运算\",\"id\":145569},{\"name\":\"尺取法\",\"id\":145570},{\"name\":\"扫描线\",\"id\":145571},{\"name\":\"离散化\",\"id\":145572},{\"name\":\"折半枚举\",\"id\":145573}],\"name\":\"枚举\",\"id\":145430,\"isnew\":true},{\"childTags\":[{\"name\":\"启发式迭代加深搜索(IDA*)\",\"id\":14437},{\"name\":\"迭代加深\",\"id\":14438},{\"name\":\"广度优先搜索(BFS)\",\"id\":145574},{\"name\":\"深度优先搜索(DFS)\",\"id\":145575},{\"name\":\"启发式搜索(A*)\",\"id\":145576},{\"name\":\"搜索减枝\",\"id\":145577},{\"name\":\"搜索\",\"id\":145578}],\"name\":\"搜索\",\"id\":145431,\"isnew\":true},{\"childTags\":[{\"name\":\"康拓展开\",\"id\":145565}],\"name\":\"康拓展开\",\"id\":145428,\"isnew\":true},{\"childTags\":[{\"name\":\"模拟\",\"id\":145566}],\"name\":\"模拟\",\"id\":145429,\"isnew\":true},{\"childTags\":[{\"name\":\"模拟退火\",\"id\":14440}],\"name\":\"模拟退火\",\"id\":145434,\"isnew\":true},{\"childTags\":[{\"name\":\"线性dp\",\"id\":14439},{\"name\":\"dp的优化\",\"id\":145579},{\"name\":\"背包问题\",\"id\":145580},{\"name\":\"插头dp\",\"id\":145581},{\"name\":\"状压dp\",\"id\":145582},{\"name\":\"动态规划\",\"id\":145583},{\"name\":\"数位dp\",\"id\":145584},{\"name\":\"树形dp\",\"id\":145585},{\"name\":\"区间dp\",\"id\":145586},{\"name\":\"概率dp\",\"id\":145587},{\"name\":\"环套树dp\",\"id\":145588},{\"name\":\"记忆化搜索\",\"id\":145589}],\"name\":\"动态规划\",\"id\":145432,\"isnew\":true},{\"childTags\":[{\"name\":\"GarsiaWachs算法\",\"id\":145591}],\"name\":\"GarsiaWachs算法\",\"id\":145433,\"isnew\":true},{\"childTags\":[{\"name\":\"思维\",\"id\":146427}],\"name\":\"思维\",\"id\":146426,\"isnew\":true},{\"childTags\":[{\"name\":\"暴力\",\"id\":146429}],\"name\":\"暴力\",\"id\":146428,\"isnew\":true}], //所有知识点tag的下拉\n" +
    //            "allStatusArray: [{id: \"ac\", name: \"通过\"}, {id: \"failed\", name: \"未通过\"}, {id: \"unsubmitted\", name: \"未提交\"}] //下拉\n" +
    //            "};\n" +
    //            "</script>\n" +
    //            "<script type=\"text/javascript\" src=\"//static.nowcoder.com/acm/nowcoder/1.0.497/javascripts-acm/page/problem/list/main.entry.js\"></script>\n" +
    //            "</body>\n" +
    //            "</html>\n";
    //
    //    List<String> problems = ReUtil.findAll("<tr data-problemId=\"(.*?)</tr>", result1, 1);
    //
    //    /*
    //
    //    13822">
    //    <td>
    //    <span class="ico-todo">未提交</span>
    //    </td>
    //    <td>
    //    <a href="/acm/problem/13822" target="_blank">NC13822</a>
    //    </td>
    //    <td class="fn-right" colspan="2">
    //    <a href="/acm/problem/13822" target="_blank" class="title">Keep In Line</a>
    //    <a href="javascript:void(0);" class="tag-label js-tag" target="_blank" data-id="1283">枚举</a>
    //    </td>
    //    <td>3星</td>
    //    <td>433/1204</td>
    //    <td>
    //    <a href="/acm/problem/13822" target="_blank" class="ico-bank-item ico-bank-code js-nc-title-tips" title="提交代码"></a>
    //    <a href="javascript:void(0);" data-id="13822" class="js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips" title="添加到题单"></a>
    //    </td>
    //
    //
    //    * */
    //
    //    for(String problem : problems){
    //        Integer problemID = ReUtil.getFirstNumber(problem);
    //        String url = "https://ac.nowcoder.com/acm/problem/"+problemID;
    //        //System.out.println(problem);
    //
    //        String ss = HtmlUtil.cleanHtmlTag(problem);
    //        //System.out.println(HtmlUtil.cleanHtmlTag(problem));
    //
    //        String[] split = ss.split("\\n+|\\t+|\\r+|\\>");
    //        List<String> stringlist = new ArrayList<>();
    //        for(int i=0;i<split.length;i++){
    //            String a = split[i];
    //            String trim = a.trim();
    //            if(!trim.isEmpty()){
    //                stringlist.add(trim);
    //            }
    //        }
    //        String star =  stringlist.get(stringlist.size()-2).substring(0,1);
    //        String tihaoID = stringlist.get(2);
    //        String problemTitle = stringlist.get(3);
    //
    //        List<String> kpointsList = new ArrayList<>();
    //        for(int i = 4; i<stringlist.size()-2;i++){
    //            kpointsList.add(stringlist.get(i));
    //        }
    //
    //        String platform = null;
    //
    //        if(tihaoID.startsWith("NC")){
    //            platform = "牛客ACM";
    //        }else if(tihaoID.startsWith("UVALive")){
    //            platform = "UVALive";
    //        }else if(tihaoID.startsWith("UVA")){
    //            platform = "UVA";
    //        }
    //
    //        FirstProblem firstProblem = new FirstProblem();
    //        firstProblem.setDifficulty(Integer.parseInt(star));
    //        firstProblem.setLink(url);
    //        firstProblem.setTopicTags(List2StringLableUtils.list2StringSplit(kpointsList,"|"));
    //        firstProblem.setThirdId(tihaoID);
    //        firstProblem.setPlaform(platform);
    //        firstProblem.setProblemName(problemTitle);
    //        firstProblem.setCompanys("牛客ACM");
    //        firstProblem.setUpdateTime(new Date());
    //        firstProblem.setCreateTime(new Date());
    //        System.out.println(firstProblem);
    //    }
    //
    //}

}
