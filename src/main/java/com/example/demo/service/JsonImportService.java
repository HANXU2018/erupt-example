package com.example.demo.service;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HtmlUtil;
import com.example.demo.dao.CalendarRepository;
import com.example.demo.dao.CompanyInfoRepository;
import com.example.demo.dao.FirstProblemRepository;
import com.example.demo.entity.enums.PlatformEnum;
import com.example.demo.model.CompanyInfo;
import com.example.demo.model.calendar.Calendar;
import com.example.demo.model.firstTiku.FirstProblem;
import com.example.demo.model.imports.JsonImport;
import com.example.demo.utils.List2StringLableUtils;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2022/1/10 17:38
 */
@Service
public class JsonImportService {
    @Autowired
    CalendarRepository calendarRepository;
    @Autowired
    FirstProblemRepository firstProblemRepository;
    @Autowired
    CompanyInfoRepository companyInfoRepository;

    public String importJsonData(JsonImport data) {
        String stringjson = data.getJsonData();
        String event = data.getEvent();
        if ("牛客竞赛日历".equals(event)) {
            importNowcoderACMCalendar(stringjson);
        } else if ("牛客上周考过".equals(event)) {
            // 上周考过
            importNowcoderlastWeekProblem(stringjson);
        } else if ("牛客校招笔试日历".equals(event)) {
            //牛客校招笔试日历
            importNowcoderCalendar(stringjson);
        } else if ("Leetcode周赛日历".equals(event)) {
            //Leetcode周赛日历
            importLeetcodeCalendar(stringjson);
        } else if ("赛码网校招笔试日历".equals(event)) {
            //赛码网校招笔试日历
            importACMcoderCalendar(stringjson);
        } else if ("PAT天梯赛".equals(event)) {
            //PAT天梯赛题目导入
            importPATTeamProblem(stringjson);
        } else if ("PAT甲级".equals(event)) {
            //PAT甲级题目导入
            importPATJIAProblem(stringjson);
        } else if ("PAT乙级".equals(event)) {
            //PAT乙级题目导入
            importPATYiProblem(stringjson);
        } else if ("PAT顶级".equals(event)) {
            //PAT顶级题目导入
            importPATTopProblem(stringjson);
        } else if ("PATZOJ".equals(event)) {
            //PATZOJ题目导入
            importZOJProblem(stringjson);
        } else if ("Leetcode题库".equals(event)) {
            //Leetcode题目导入
            importLeetCodeProblem(stringjson);
        } else if ("Lintcode题库".equals(event)) {
            //Lintcode题目导入
            importLintCodeProblem(stringjson);
        } else if ("牛客竞赛题库".equals(event)) {
            //牛客竞赛题库题目导入
            importNowCodeACMProblem(stringjson);
        } else if ("牛客公司LOGO".equals(event)) {
            //牛客公司LOGO导入
            importNowCodeLOGOProblem(stringjson);
        } else {
            return "alert('" + event + "导入功能未开发')";
        }
        return "Success";
    }

    //导入 牛客网公司LOGO
    private void importNowCodeLOGOProblem(String stringjson) {
        try {
            JSONObject jsonValue = new JSONObject(stringjson);
            JSONObject data = jsonValue.getJSONObject("data");
            JSONArray jsonArray = data.getJSONArray("community");
            for (int i = 0; i < jsonArray.length(); i++) {
                // 公司logo
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                /*

                {
                    "companyId": 665,
                    "img": "https://uploadfiles.nowcoder.com/files/20191129/4107856_1575019780091_60x60.png",
                    "isAiInterview": true,
                    "name": "字节跳动",
                    "writtenCount": 27,
                    "interviewCount": 129,
                    "effectValue": 657747
                }
                 */

                /*
                 {
                 "imgD50": "https://uploadfiles.nowcoder.com/files/20191129/4107856_1575019780091_60x60.png",
                 "companyName": "字节跳动",
                 "icon": 665,
                 "scale": "C轮",
                 "commentCount": 364,
                 "askPostCount": 4202,
                 "picUrl": "https://uploadfiles.nowcoder.com/files/20181102/392538858_1541149535037_999991342_1533887452270_字节跳动.png",
                 "paperCount": 31,
                 "imgD120": "https://uploadfiles.nowcoder.com/files/20191129/4107856_1575019807332_120x120-2.png",
                 "imgD80G": "https://uploadfiles.nowcoder.com/files/20191129/4107856_1575019673809_80x80-2.png",
                 "name": "字节跳动",
                 "postCount": 7770,
                 "id": 665,
                 "neituiCount": 9674,
                 "category": "直播/短视频"
                 }
                 */

                CompanyInfo companyInfo = new CompanyInfo();
                companyInfo.setInfo(jsonObject.getString("name"));
                companyInfo.setCompanyName(jsonObject.getString("name"));
                companyInfo.setImageurl(jsonObject.getString("picUrl"));
                companyInfo.setMsg(jsonObject.toString());
                companyInfoRepository.save(companyInfo);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //导入 赛码网校招笔试日历
    private void importACMcoderCalendar(String stringjson) {
        try {
            JSONObject jsonValue = new JSONObject(stringjson);
            JSONArray resultList = jsonValue.getJSONArray("result");
            JSONObject result = resultList.getJSONObject(0);
            JSONObject data = result.getJSONObject("data");
            JSONArray jsonArray = data.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                // 日历事件
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                /*
                DEMO样例
                {
                    "id": 1605836976773,
                    "date": "2020-11-08",
                    "time": "10:00",
                    "company": "字节跳动"
                  },
                * */

                Calendar calendar = new Calendar();
                calendar.setOdName("赛码网");
                calendar.setTitle(jsonObject.getString("company"));
                String date = jsonObject.getString("date");
                String time = jsonObject.getString("time");
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                Date start = format.parse(date + "-" + time);
                calendar.setStart(start);
                calendar.setEnd(new Date(start.getTime() + 90 * 60 * 1000));// 默认90分钟
                calendar.setUrl("http://www.acmcoder.com/#/product/exam");
                calendar.setMsg(jsonObject.toString());// 保留原始数据
                calendar.setIntro("【校招笔试】" + jsonObject.getString("company"));
                // 牛客竞赛logo
                calendar.setPic("http://cdn.acmcoder.com/release/www/2.0.1/images/logo.png");
                calendar.setUpdateTime(new Date());
                calendar.setCreateTime(new Date());
                calendarRepository.save(calendar);

                System.out.println("导入数据" + jsonObject.toString());

            }

        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }


    // 导入leetcode 周赛日历
    private void importLeetcodeCalendar(String stringjson) {
        try {
            JSONObject jsonValue = new JSONObject(stringjson);
            JSONObject data = jsonValue.getJSONObject("data");
            JSONObject contestHistory = data.getJSONObject("contestHistory");
            JSONArray jsonArray = contestHistory.getJSONArray("contests");
            for (int i = 0; i < jsonArray.length(); i++) {
                // 日历事件
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                /*
                DEMO样例
                {
                    "containsPremium": false,
                    "title": "第 275 场周赛",
                    "cardImg": "https://assets.leetcode-cn.com/aliyun-lc-upload/contest-config/weekly-contest-275/contest_detail/pc_card.png",
                    "titleSlug": "weekly-contest-275",
                    "description": "<style>\n  .contest-information ol:not(.list-group) {\n    padding-left: 20px;\n  }\n\n  .contest-information ul:not(.list-group) {\n    padding-left: 20px;\n  }\n\n  .contest-information li:not(.list-group-item) {\n    margin-top: 5px;\n  }\n\n  .contest-information .list-group .list-group-item {\n    border: none;\n    margin-bottom: 1px;\n    display: flex;\n    flex-direction: row;\n    justify-content: space-between;\n  }\n\n  .contest-information .list-group .list-group-item > span {\n    flex: 0 0 auto;\n  }\n\n  .contest-information img[alt=\"LeetCoin\"] {\n    position: relative;\n    top: -2px;\n  }\n\n  .contest-information .award-img {\n    display: block;\n    max-width: 600px;\n    width: 100%;\n    border-radius: 4px;\n  }\n\n  .contest-information .inner-banner {\n    margin: 30px 0 50px;\n    display: block;\n    max-width: 100%;\n    border-radius: 8px;\n  }\n\n  .contest-information ol p {\n    margin-top: 1em;\n    margin-bottom: 0.6em;\n  }\n</style>\n\n<div class=\"contest-information container\">\n  <div class=\"row\">\n    <div class=\"col-sm-8 col-md-9\">\n      <h3 class=\"text-300\">\n        欢迎来到第 275 场周赛\n      </h3>\n      <br>\n      <p>本场竞赛由「vika 维格 & 力扣」联合主办</p>\n      <p>\n        <strong>【工作机会奖励】</strong>\n        <ul>\n          <li>\n            <strong>排名第 1 - 1000 名的参赛者</strong>可获「vika 维格」简历内推机会。\n          </li>\n        </ul>\n      </p>\n      <p>\n        <strong>【实物奖励】</strong>\n        <ul>\n          <li>\n            <strong>排名第 1 - 3 名的参赛者</strong>可获 vika 维格提供的「限量版盲盒 + vika 果冻包 + vika 潮牌 T 恤 + 定制扑克牌 + 精美贴纸 + 5000 V 币」x 1 \n          </li>\n          <li>\n            <strong>排名第 4 - 9 名的参赛者</strong>可获  vika 维格提供的「限量版盲盒 + vika 潮牌 T 恤 + 定制扑克牌 + 5000 V 币」× 1\n          </li>\n          <li>\n            <strong>排名第 10 - 16 名的参赛者</strong>可获 vika 维格提供的「限量版盲盒 + 定制扑克 + 3000 V 币」 × 1\n          </li>\n          <li>\n            <strong>排名第 17 - 25 名的参赛者</strong>可获 vika 维格提供的「定制扑克 + 3000 V 币」 × 1\n          </li>\n          <li>\n            <strong>排名第 66 、88、99、666、888、1024 名的参赛者</strong>可获 vika 维格提供的「 3000 V 币」 × 1\n          </li>\n        </ul>\n        <p style=\"border-left: 4px solid rgba(0,0,0,0.5); padding-left: 1em;\">\n          本次周赛奖品将于比赛后<strong> 15 个工作日 </strong>内进行发放，请获奖选手多加关注力扣官方通知。<br />\n        </p>\n\n          <img class=\"inner-banner\" src=\"https://pic.lingkou.xyz/1640857190-VemQjy-ç»´æ ¼_æ¥åè¯¦æé¡µ - å¥åéåå¾ - 1760 Ã 300 px--.png\" />\n\n        <a href=\"https://datayi.cn/w/a9B3w7YR\" target=\"_blank\">\n          <img class=\"inner-banner\" src=\"https://pic.lingkou.xyz/1640859432-UmTlyA-1760-360 ç»´æ ¼.png\" />\n        </a>\n      </p>\n      <h4 class=\"text-300\">\n        <i class=\"fa fa-newspaper-o\" style=\"color: #1DA09C\" aria-hidden=\"true\"></i>\n        &nbsp;重要提示\n      </h4>\n      <ol>\n        <li>力扣一向非常重视竞赛的公平和公正。为保障每一位参赛者的权益，给大家带来更好的竞赛体验，营造健康积极的竞赛环境，我们对竞赛规则进行了更详细的约定和规范，点击 <a href=\"https://support.leetcode-cn.com/hc/kb/article/1278066/\" target=\"_blank\">查看全部</a>。\n        </li>\n        <li>请注意，每个错误提交的惩罚时间为 <b>5 分钟</b> 。</li>\n        <li>为保障竞赛的公平性，力扣将在竞赛中隐藏部分用例。当参赛者在竞赛中提交未通过时，力扣不会显示给参赛者被隐藏的错误用例。</li> \n        <li>本场竞赛的最终排名会在竞赛结束后的 5 个工作日内确认。</li>\n        <li><b>竞赛中的违规行为：</b>\n          <ul>\n            <li>一人使用多账号提交（力扣「中文社区 LCCN」和「美国网站 LCUS」账号属于两个账号）</li>\n            <li>多账号提交雷同代码（抄袭）</li>\n            <li>使用不正当手段影响他人竞赛的</li>\n            <li>竞赛结束前在讨论区发布答案的</li>\n          </ul>\n          <p>\n            如有用户被检查出竞赛中存在违规行为，力扣会坚持以 🙅 零容忍 的态度维护竞赛的 ⚖️ 公平、公正，严格按照以下处罚规则执行：\n          </p>\n          <ul>\n            <li>第一次违规：❗️ 账号内的所有积分清零，账号冻结 1 个月</li>\n            <li>第二次违规：🚫 永久封号</li>\n          </ul>\n          <p>同时我们也鼓励大家共同维护竞赛的公平和公正，我们会给于举报成功的用户额外的奖励：</p>\n          <ul>\n            <li>被认定为违规账号的前 10 名举报者，每人可获得 20 积分奖励</li>\n            <li>每人每场最高可获得举报成功的 100 积分奖励</li>\n          </ul>\n        </li>\n            <p>                另外，为了保证竞赛的公正、公平，请勿在竞赛结束前在讨论区发布、讨论可能会影响竞赛正常进行的内容，包括且不限于发布竞赛题答案、解题思路、方法、提醒注意等，社区管理员将有权根据实际情况予以警告、删除、禁言、封禁其相应帐号的功能，情节严重者，将酌情封禁对应 IP。\n            </P>\n      </ol>\n      <br>\n      <br>\n      <h4 class=\"text-300\">\n        <i class=\"fa fa-bullhorn\" style=\"color: #FEA116\" aria-hidden=\"true\"></i>\n        &nbsp;通知\n      </h4>\n      <p>\n        您必须先\n        <b class=\"text-orange\">报名</b> 后方能参加该场竞赛。我们祝愿您参赛愉快！\n      </p>\n      <br>\n      \n      \n                <a href=\"/circle/discuss/9TMMfX/\" target=\"_blank\">\n                  <img\n                      src=\"https://assets.leetcode-cn.com/aliyun-lc-upload/contest-config/weekly-contest-275/contest_detail/contest_to_discuss_image.png\"\n                      style=\"width:320px; border-radius: 10px; margin: 10px 0;\"\n                  />\n                </a>\n            \n            \n    </div>\n    <div class=\"col-sm-4 col-md-3\">\n      <h3 class=\"text-300\">\n        <i class=\"fa fa-trophy text-orange\" aria-hidden=\"true\"></i>\n        &nbsp;竞赛奖励\n      </h3>\n      <ul class=\"list-group\" style=\"margin-top: 20px\">\n        <li class=\"list-group-item\">\n          <b>第一名</b>\n          <span>\n            5,000\n            <a href=\"/points/\" target=\"_blank\">\n              <img src=\"https://static.leetcode-cn.com/cn-legacy-assets/images/LeetCoin.png\" height=\"15px\" alt=\"LeetCoin\">\n            </a>\n          </span>\n        </li>\n        <li class=\"list-group-item\">\n          <b>第二名</b>\n          <span>\n            2,500\n            <a href=\"/points/\" target=\"_blank\">\n              <img src=\"https://static.leetcode-cn.com/cn-legacy-assets/images/LeetCoin.png\" height=\"15px\" alt=\"LeetCoin\">\n            </a>\n          </span>\n        </li>\n        <li class=\"list-group-item\">\n          <b>第三名</b>\n          <span>\n            1,500\n            <a href=\"/points/\" target=\"_blank\">\n              <img src=\"https://static.leetcode-cn.com/cn-legacy-assets/images/LeetCoin.png\" height=\"15px\" alt=\"LeetCoin\">\n            </a>\n          </span>\n        </li>\n        <li class=\"list-group-item\">\n          <b>4 - 10 名</b>\n          <span>\n            800\n            <a href=\"/points/\" target=\"_blank\">\n              <img src=\"https://static.leetcode-cn.com/cn-legacy-assets/images/LeetCoin.png\" height=\"15px\" alt=\"LeetCoin\">\n            </a>\n          </span>\n        </li>\n        <li class=\"list-group-item\">\n          <b>11 - 50 名</b>\n          <span>\n            300\n            <a href=\"/points/\" target=\"_blank\">\n              <img src=\"https://static.leetcode-cn.com/cn-legacy-assets/images/LeetCoin.png\" height=\"15px\" alt=\"LeetCoin\">\n            </a>\n          </span>\n        </li>\n        <li class=\"list-group-item\">\n          <b>51 - 100 名</b>\n          <span>\n            100\n            <a href=\"/points/\" target=\"_blank\">\n              <img src=\"https://static.leetcode-cn.com/cn-legacy-assets/images/LeetCoin.png\" height=\"15px\" alt=\"LeetCoin\">\n            </a>\n          </span>\n        </li>\n        <li class=\"list-group-item\">\n          <b>101 - 200 名</b>\n          <span>\n            50\n            <a href=\"/points/\" target=\"_blank\">\n              <img src=\"https://static.leetcode-cn.com/cn-legacy-assets/images/LeetCoin.png\" height=\"15px\" alt=\"LeetCoin\">\n            </a>\n          </span>\n        </li>\n        <li class=\"list-group-item\">\n          <b>重在参与奖励</b>\n          <span>\n            5\n            <a href=\"/points/\" target=\"_blank\">\n              <img src=\"https://static.leetcode-cn.com/cn-legacy-assets/images/LeetCoin.png\" height=\"15px\" alt=\"LeetCoin\">\n            </a>\n          </span>\n        </li>\n        <li class=\"list-group-item\">\n          <b>首次参加比赛并成功提交</b>\n          <span>\n            200\n            <a href=\"/points/\" target=\"_blank\">\n              <img src=\"https://static.leetcode-cn.com/cn-legacy-assets/images/LeetCoin.png\" height=\"15px\" alt=\"LeetCoin\">\n            </a>\n          </span>\n        </li>\n        <li class=\"list-group-item\">\n          <b>一周同时参加双周赛和周赛</b>\n          <span>\n            66\n            <a href=\"/points/\" target=\"_blank\">\n              <img src=\"https://static.leetcode-cn.com/cn-legacy-assets/images/LeetCoin.png\" height=\"15px\" alt=\"LeetCoin\">\n            </a>\n          </span>\n        </li>\n      </ul>\n      <h4 class=\"text-300\">\n        <i class=\"fa fa-star text-yellow\" aria-hidden=\"true\"></i>\n        &nbsp;追加奖励 - 全球排名\n      </h4>\n      <ul class=\"list-group\" style=\"margin-top: 20px\">\n        <li class=\"list-group-item\">\n          <b>全球排名前 10</b>\n          <span>\n            500\n            <a href=\"/points/\" target=\"_blank\">\n              <img src=\"https://static.leetcode-cn.com/cn-legacy-assets/images/LeetCoin.png\" height=\"15px\" alt=\"LeetCoin\">\n            </a>\n          </span>\n        </li>\n        <li class=\"list-group-item\">\n          <b>全球排名 11 - 20</b>\n          <span>\n            300\n            <a href=\"/points/\" target=\"_blank\">\n              <img src=\"https://static.leetcode-cn.com/cn-legacy-assets/images/LeetCoin.png\" height=\"15px\" alt=\"LeetCoin\">\n            </a>\n          </span>\n        </li>\n        <li class=\"list-group-item\">\n          <b>全球排名 21 - 50</b>\n          <span>\n            100\n            <a href=\"/points/\" target=\"_blank\">\n              <img src=\"https://static.leetcode-cn.com/cn-legacy-assets/images/LeetCoin.png\" height=\"15px\" alt=\"LeetCoin\">\n            </a>\n          </span>\n        </li>\n        <li class=\"list-group-item\">\n          <b>全球排名 51 - 100</b>\n          <span>\n            50\n            <a href=\"/points/\" target=\"_blank\">\n              <img src=\"https://static.leetcode-cn.com/cn-legacy-assets/images/LeetCoin.png\" height=\"15px\" alt=\"LeetCoin\">\n            </a>\n          </span>\n        </li>\n      </ul>\n    </div>\n  </div>\n</div>\n<hr>\n<p>\n  <b>想让力扣社区的百万极客们 <i>认识您的公司</i> 吗？\n    那就赶快 <a href=\"mailto:Marketing@leetcode-cn.com\" target=\"_href\">联系我们</a> ，即有可能赞助我们的下一场竞赛哦！</b>\n</p>\n ",
                    "startTime": 1641695400,
                    "duration": 5400,
                    "originStartTime": 1641695400,
                    "isVirtual": false,
                    "company": {
                        "watermark": "https://assets.leetcode-cn.com/aliyun-lc-upload/contest-config/weekly-contest-275/enterprise/watermark.png",
                        "__typename": "SponsorNode"
                    },
                    "__typename": "ContestNode"
                }
                * */

                Calendar calendar = new Calendar();
                calendar.setOdName("LeetCode周赛");
                calendar.setTitle(jsonObject.getString("title"));
                calendar.setStart(new Date(jsonObject.getLong("startTime") * 1000));
                calendar.setEnd(new Date(jsonObject.getLong("startTime") * 1000 + jsonObject.getLong("duration") * 1000 - 1));
                String prelink = "https://leetcode-cn.com/contest/";
                calendar.setUrl(prelink + jsonObject.getString("titleSlug"));
                // 日历图片
                calendar.setPic(jsonObject.getJSONObject("company").getString("watermark"));
                calendar.setMsg(jsonObject.toString());// 保留原始数据
                calendar.setIntro("【竞赛】" + jsonObject.getString("title"));
                calendar.setUpdateTime(new Date());
                calendar.setCreateTime(new Date());
                calendarRepository.save(calendar);

                System.out.println("导入数据" + jsonObject.toString());

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    // 导入牛客上周考过
    private void importNowcoderlastWeekProblem(String stringjson) {
        try {
            JSONObject jsonValue = new JSONObject(stringjson);
            JSONObject nowcodeData = jsonValue.getJSONObject("data");
            JSONArray jsonArray = nowcodeData.getJSONArray("questions");
            for (int i = 0; i < jsonArray.length(); i++) {
                // 日历事件
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                /*
                DEMO样例
                 {
                    "tpId": 188,
                    "questionId": 25283,
                    "questionTitle": "顺时针旋转矩阵",
                    "questionNo": "NC18",
                    "jobs": [{
                        "name": "客户端",
                        "id": 4
                    }, {
                        "name": "研发",
                        "id": 0
                    }],
                    "isNew": false,
                    "tqId": 38565,
                    "tags": [{
                        "name": "数组",
                        "id": 578
                    }],
                    "difficulty": 3,
                    "topicUrl": "/ta/job-code-high-week",
                    "postPage": 1,
                    "ranks": "",
                    "acceptRate": 47.4675,
                    "companys": [{
                        "name": "快手",
                        "id": 898
                    }, {
                        "name": "shopee",
                        "id": 1049
                    }, {
                        "name": "美团",
                        "id": 179
                    }],
                    "postCount": 3,
                    "questionUUid": "2e95333fbdd4451395066957e24909cc"
                }
                * */

                FirstProblem firstProblem = new FirstProblem();
                // 题目名
                firstProblem.setProblemName(jsonObject.getString("questionTitle"));
                // 知识点
                String tags = list2StringSplit(jsonObject.getJSONArray("tags"));
                firstProblem.setTopicTags(tags);
                // 公司
                if (jsonObject.has("companys")) {
                    String company = list2StringSplit(jsonObject.getJSONArray("companys"));
                    firstProblem.setCompanys(company);
                } else {
                    firstProblem.setCompanys("牛客网");
                }
                // 难度
                firstProblem.setDifficulty(jsonObject.getInt("difficulty"));
                // 平台
                firstProblem.setPlaform(PlatformEnum.NOWCODE.getName());
                // 考试次数
                firstProblem.setTestCount((int) jsonObject.getLong("postCount"));
                // 链接
                String preLink = "https://www.nowcoder.com/practice/";
                firstProblem.setLink(preLink + jsonObject.getString("questionUUid"));
                // 第三方id
                firstProblem.setThirdId(jsonObject.getString("questionNo"));
                // 描述
                firstProblem.setIntro("【上周考过】" + firstProblem.getProblemName());
                // 源数据
                firstProblem.setMsg(jsonObject.toString());
                // 创建时间
                firstProblem.setCreateTime(new Date());
                firstProblem.setUpdateTime(new Date());
                try {
                    firstProblemRepository.save(firstProblem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("导入数据" + jsonObject.toString());

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // 导入牛客竞赛日历
    public void importNowcoderACMCalendar(String stringjson) {
        try {
            JSONObject jsonValue = new JSONObject(stringjson);
            JSONArray jsonArray = jsonValue.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                // 日历事件
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                /*
                DEMO样例
                {
                    "contestId": 1026849,
                    "ojName": "NowCoder",
                    "ojId": 0,
                    "link": "https://ac.nowcoder.com/acm/contest/26849?from=acm_calendar",
                    "startTime": 1641096000000,
                    "endTime": 1641114000000,
                    "contestName": "湖南大学2021届ACM新生赛（重现赛）"
                }
                * */

                Calendar calendar = new Calendar();
                calendar.setOdName(jsonObject.getString("ojName"));
                calendar.setTitle(jsonObject.getString("contestName"));
                calendar.setStart(new Date(jsonObject.getLong("startTime")));
                calendar.setEnd(new Date(jsonObject.getLong("endTime")));
                calendar.setUrl(jsonObject.getString("link"));
                calendar.setMsg(jsonObject.toString());// 保留原始数据
                calendar.setIntro("【竞赛】" + jsonObject.getString("contestName"));
                // 牛客竞赛logo
                calendar.setPic("https://static.nowcoder.com/acm/images-acm/logo.png");

                calendar.setUpdateTime(new Date());
                calendar.setCreateTime(new Date());

                calendarRepository.save(calendar);

                System.out.println("导入数据" + jsonObject.toString());

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    // 导入牛客校招笔试日历
    public void importNowcoderCalendar(String stringjson) {
        try {
            JSONObject jsonValue = new JSONObject(stringjson);
            JSONArray jsonArray = jsonValue.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                // 日历事件
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                /*
                DEMO样例
                {
                    "companyTagId": 0,
                    "examTechType": 3,
                    "joinCount": 4,
                    "hasCommunity": false,
                    "creatorId": 719274425,
                    "creatorName": "dorisfy",
                    "examType": 0,
                    "createTime": 1635494232000,
                    "creatorHeadUrl": "https://images.nowcoder.com/head/628m.png?x-oss-process=image/resize,m_mfit,h_100,w_100",
                    "company": "叠纸游戏",
                    "beginTime": 1635937200000,
                    "endTime": 1635944400000,
                    "hasAttend": false,
                    "id": 2527,
                    "place": "",
                    "hasSchoolSchedule": false,
                    "status": 0
                }

                * */

                Calendar calendar = new Calendar();
                calendar.setOdName("校招笔试");
                calendar.setTitle("【校招笔试】" + jsonObject.getString("company"));
                calendar.setStart(new Date(jsonObject.getLong("beginTime")));
                calendar.setEnd(new Date(jsonObject.getLong("endTime")));
                calendar.setUrl("https://www.nowcoder.com/");
                calendar.setMsg(jsonObject.toString());// 保留原始数据
                calendar.setIntro("【校招笔试】" + jsonObject.getString("company"));
                // 牛客网校招logo
                calendar.setPic("https://static.nowcoder.com/fe/file/images/web/recommend/apply-for-job.png");
                calendar.setUpdateTime(new Date());
                calendar.setCreateTime(new Date());

                calendarRepository.save(calendar);

                System.out.println("导入数据" + jsonObject.toString());

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    // 导入ZOJ题目
    public void importZOJProblem(String stringjson) {
        try {
            JSONObject jsonValue = new JSONObject(stringjson);
            JSONArray jsonArray = jsonValue.getJSONArray("problemSetProblems");
            for (int i = 0; i < jsonArray.length(); i++) {
                // 日历事件
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                /*
                DEMO样例
               {
                    "id": "994805147132084224",
                    "label": "L1-001",
                    "score": 5,
                    "deadline": "1970-01-01T00:00:00Z",
                    "acceptCount": 78504,
                    "submitCount": 182977,
                    "title": "Hello World",
                    "type": "PROGRAMMING",
                    "compiler": "NO_COMPILER",
                    "problemStatus": "NO_PROBLEM_STATUS",
                    "problemSetId": "994805046380707840"
                }

                * */
                FirstProblem firstProblem = new FirstProblem();
                // 题目名
                firstProblem.setProblemName(jsonObject.getString("label") + "—" + jsonObject.getString("title"));
                // 知识点 TODO 后续 导入
                //    String tags = list2StringSplit(jsonObject.getJSONArray("tags"));
                //    firstProblem.setTopicTags(tags);
                // 公司 TODO 后续导入 作者+学校
                //String company = list2StringSplit(jsonObject.getJSONArray("companys"));
                //firstProblem.setCompanys(company);
                // 难度
                int score = jsonObject.getInt("score");
                if (score <= 5) {
                    firstProblem.setDifficulty(1);// l1
                } else if (score <= 10) {
                    firstProblem.setDifficulty(2);// l1
                } else if (score <= 20) {
                    firstProblem.setDifficulty(3);// l1
                } else if (score <= 25) {
                    firstProblem.setDifficulty(4);// l2
                } else {
                    firstProblem.setDifficulty(5);// l3
                }
                // 平台
                firstProblem.setPlaform(PlatformEnum.PAT_ZOJ.getName());
                // 考试次数
                firstProblem.setTestCount(1);
                // 链接
                String problemSet = jsonObject.getString("problemSetId");
                String id = jsonObject.getString("id");
                String link = StrFormatter.format("https://zoj.pintia.cn/problem-sets/{}/problems/{}", problemSet, id);
                firstProblem.setLink(link);
                // 第三方id
                firstProblem.setThirdId(jsonObject.getString("id"));
                // 描述
                firstProblem.setIntro("【PAT-ZOJ】" + jsonObject.getString("label") + "—" + jsonObject.getString("title"));
                // 源数据
                firstProblem.setMsg(jsonObject.toString());

                firstProblem.setUpdateTime(new Date());
                firstProblem.setCreateTime(new Date());
                firstProblemRepository.save(firstProblem);
                System.out.println("导入数据" + jsonObject.toString());

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    // 导入PAT天梯赛题目
    public void importPATTeamProblem(String stringjson) {
        try {
            JSONObject jsonValue = new JSONObject(stringjson);
            JSONArray jsonArray = jsonValue.getJSONArray("problemSetProblems");
            for (int i = 0; i < jsonArray.length(); i++) {
                // 日历事件
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                /*
                DEMO样例
               {
                    "id": "994805147132084224",
                    "label": "L1-001",
                    "score": 5,
                    "deadline": "1970-01-01T00:00:00Z",
                    "acceptCount": 78504,
                    "submitCount": 182977,
                    "title": "Hello World",
                    "type": "PROGRAMMING",
                    "compiler": "NO_COMPILER",
                    "problemStatus": "NO_PROBLEM_STATUS",
                    "problemSetId": "994805046380707840"
                }

                * */
                FirstProblem firstProblem = new FirstProblem();
                // 题目名
                firstProblem.setProblemName(jsonObject.getString("label") + "—" + jsonObject.getString("title"));
                // 知识点 TODO 后续 导入
                //    String tags = list2StringSplit(jsonObject.getJSONArray("tags"));
                //    firstProblem.setTopicTags(tags);
                // 公司 TODO 后续导入 作者+学校
                //String company = list2StringSplit(jsonObject.getJSONArray("companys"));
                //firstProblem.setCompanys(company);
                // 难度
                int score = jsonObject.getInt("score");
                if (score <= 5) {
                    firstProblem.setDifficulty(1);// l1
                } else if (score <= 10) {
                    firstProblem.setDifficulty(2);// l1
                } else if (score <= 20) {
                    firstProblem.setDifficulty(3);// l1
                } else if (score <= 25) {
                    firstProblem.setDifficulty(4);// l2
                } else {
                    firstProblem.setDifficulty(5);// l3
                }
                // 平台
                firstProblem.setPlaform(PlatformEnum.PAT.getName());
                // 考试次数
                firstProblem.setTestCount(1);
                // 链接
                String problemSet = jsonObject.getString("problemSetId");
                String id = jsonObject.getString("id");
                String link = StrFormatter.format("https://pintia.cn/problem-sets/{}/problems/{}", problemSet, id);
                firstProblem.setLink(link);
                // 第三方id
                firstProblem.setThirdId(jsonObject.getString("id"));
                // 描述

                firstProblem.setIntro("【天梯赛】" + jsonObject.getString("label") + "—" + jsonObject.getString("title"));
                // 源数据
                firstProblem.setMsg(jsonObject.toString());

                firstProblem.setUpdateTime(new Date());
                firstProblem.setCreateTime(new Date());
                firstProblemRepository.save(firstProblem);
                System.out.println("导入数据" + jsonObject.toString());

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }


    // 导入PAT甲级题目
    public void importPATJIAProblem(String stringjson) {
        try {
            JSONObject jsonValue = new JSONObject(stringjson);
            JSONArray jsonArray = jsonValue.getJSONArray("problemSetProblems");
            for (int i = 0; i < jsonArray.length(); i++) {
                // 日历事件
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                /*
                DEMO样例
               {
                    "id": "994805147132084224",
                    "label": "L1-001",
                    "score": 5,
                    "deadline": "1970-01-01T00:00:00Z",
                    "acceptCount": 78504,
                    "submitCount": 182977,
                    "title": "Hello World",
                    "type": "PROGRAMMING",
                    "compiler": "NO_COMPILER",
                    "problemStatus": "NO_PROBLEM_STATUS",
                    "problemSetId": "994805046380707840"
                }

                * */
                FirstProblem firstProblem = new FirstProblem();
                // 题目名
                firstProblem.setProblemName(jsonObject.getString("title"));
                // 知识点 TODO 后续 导入
                //    String tags = list2StringSplit(jsonObject.getJSONArray("tags"));
                //    firstProblem.setTopicTags(tags);
                // 公司 TODO 后续导入 作者+学校
                //String company = list2StringSplit(jsonObject.getJSONArray("companys"));
                //firstProblem.setCompanys(company);
                // 难度
                int score = jsonObject.getInt("score");
                if (score <= 5) {
                    firstProblem.setDifficulty(1);// l1
                } else if (score <= 10) {
                    firstProblem.setDifficulty(2);// l1
                } else if (score <= 20) {
                    firstProblem.setDifficulty(3);// l1
                } else if (score <= 25) {
                    firstProblem.setDifficulty(4);// l2
                } else {
                    firstProblem.setDifficulty(5);// l3
                }
                // 平台
                firstProblem.setPlaform(PlatformEnum.PAT.getName());
                // 考试次数
                firstProblem.setTestCount(1);
                // 链接
                String problemSet = jsonObject.getString("problemSetId");
                String id = jsonObject.getString("id");
                String link = StrFormatter.format("https://pintia.cn/problem-sets/{}/problems/{}", problemSet, id);
                firstProblem.setLink(link);
                // 第三方id
                firstProblem.setThirdId(jsonObject.getString("id"));
                // 描述
                firstProblem.setIntro("【PAT甲级】" + firstProblem.getProblemName());
                // 源数据
                firstProblem.setMsg(jsonObject.toString());

                firstProblem.setUpdateTime(new Date());
                firstProblem.setCreateTime(new Date());
                firstProblemRepository.save(firstProblem);
                System.out.println("导入数据" + jsonObject.toString());

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    // 导入PAT乙级题目
    public void importPATYiProblem(String stringjson) {
        try {
            JSONObject jsonValue = new JSONObject(stringjson);
            JSONArray jsonArray = jsonValue.getJSONArray("problemSetProblems");
            for (int i = 0; i < jsonArray.length(); i++) {
                // 日历事件
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                /*
                DEMO样例
               {
                    "id": "994805147132084224",
                    "label": "L1-001",
                    "score": 5,
                    "deadline": "1970-01-01T00:00:00Z",
                    "acceptCount": 78504,
                    "submitCount": 182977,
                    "title": "Hello World",
                    "type": "PROGRAMMING",
                    "compiler": "NO_COMPILER",
                    "problemStatus": "NO_PROBLEM_STATUS",
                    "problemSetId": "994805046380707840"
                }

                * */
                FirstProblem firstProblem = new FirstProblem();
                // 题目名
                firstProblem.setProblemName(jsonObject.getString("title"));
                // 知识点 TODO 后续 导入
                //    String tags = list2StringSplit(jsonObject.getJSONArray("tags"));
                //    firstProblem.setTopicTags(tags);
                // 公司 TODO 后续导入 作者+学校
                //String company = list2StringSplit(jsonObject.getJSONArray("companys"));
                //firstProblem.setCompanys(company);
                // 难度
                int score = jsonObject.getInt("score");
                if (score <= 5) {
                    firstProblem.setDifficulty(1);// l1
                } else if (score <= 10) {
                    firstProblem.setDifficulty(2);// l1
                } else if (score <= 20) {
                    firstProblem.setDifficulty(3);// l1
                } else if (score <= 25) {
                    firstProblem.setDifficulty(4);// l2
                } else {
                    firstProblem.setDifficulty(5);// l3
                }
                // 平台
                firstProblem.setPlaform(PlatformEnum.PAT.getName());
                // 考试次数
                firstProblem.setTestCount(1);
                // 链接
                String problemSet = jsonObject.getString("problemSetId");
                String id = jsonObject.getString("id");
                String link = StrFormatter.format("https://pintia.cn/problem-sets/{}/problems/{}", problemSet, id);
                firstProblem.setLink(link);
                // 第三方id
                firstProblem.setThirdId(jsonObject.getString("id"));
                // 描述
                firstProblem.setIntro("【PAT乙级】" + firstProblem.getProblemName());
                // 源数据
                firstProblem.setMsg(jsonObject.toString());

                firstProblem.setUpdateTime(new Date());
                firstProblem.setCreateTime(new Date());
                firstProblemRepository.save(firstProblem);
                System.out.println("导入数据" + jsonObject.toString());

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    // 导入PAT顶级题目
    public void importPATTopProblem(String stringjson) {
        try {
            JSONObject jsonValue = new JSONObject(stringjson);
            JSONArray jsonArray = jsonValue.getJSONArray("problemSetProblems");
            for (int i = 0; i < jsonArray.length(); i++) {
                // 日历事件
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                /*
                DEMO样例
               {
                    "id": "994805147132084224",
                    "label": "L1-001",
                    "score": 5,
                    "deadline": "1970-01-01T00:00:00Z",
                    "acceptCount": 78504,
                    "submitCount": 182977,
                    "title": "Hello World",
                    "type": "PROGRAMMING",
                    "compiler": "NO_COMPILER",
                    "problemStatus": "NO_PROBLEM_STATUS",
                    "problemSetId": "994805046380707840"
                }

                * */
                FirstProblem firstProblem = new FirstProblem();
                // 题目名
                firstProblem.setProblemName(jsonObject.getString("title"));
                // 知识点 TODO 后续 导入
                //    String tags = list2StringSplit(jsonObject.getJSONArray("tags"));
                //    firstProblem.setTopicTags(tags);
                // 公司 TODO 后续导入 作者+学校
                //String company = list2StringSplit(jsonObject.getJSONArray("companys"));
                //firstProblem.setCompanys(company);
                // 难度
                int score = jsonObject.getInt("score");
                if (score <= 5) {
                    firstProblem.setDifficulty(1);// l1
                } else if (score <= 10) {
                    firstProblem.setDifficulty(2);// l1
                } else if (score <= 20) {
                    firstProblem.setDifficulty(3);// l1
                } else if (score <= 25) {
                    firstProblem.setDifficulty(4);// l2
                } else {
                    firstProblem.setDifficulty(5);// l3
                }
                // 平台
                firstProblem.setPlaform(PlatformEnum.PAT.getName());
                // 考试次数
                firstProblem.setTestCount(1);
                // 链接
                String problemSet = jsonObject.getString("problemSetId");
                String id = jsonObject.getString("id");
                String link = StrFormatter.format("https://pintia.cn/problem-sets/{}/problems/{}", problemSet, id);
                firstProblem.setLink(link);
                // 第三方id
                firstProblem.setThirdId(jsonObject.getString("id"));
                // 描述
                firstProblem.setIntro("【PAT顶级】" + firstProblem.getProblemName());
                // 源数据
                firstProblem.setMsg(jsonObject.toString());

                firstProblem.setUpdateTime(new Date());
                firstProblem.setCreateTime(new Date());
                firstProblemRepository.save(firstProblem);
                System.out.println("导入数据" + jsonObject.toString());

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }


    // 导入LeetCode题目
    public void importLeetCodeProblem(String stringjson) {
        try {
            JSONObject jsonValue = new JSONObject(stringjson);
            JSONObject data = jsonValue.getJSONObject("data");
            JSONObject problemsetQuestionList = data.getJSONObject("problemsetQuestionList");
            JSONArray jsonArray = problemsetQuestionList.getJSONArray("questions");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                /*
                DEMO样例
               {
				"__typename": "QuestionLightNode",
				"acRate": 0.5230448886005528,
				"difficulty": "EASY",
				"freqBar": 0,
				"paidOnly": false,
				"status": "NOT_STARTED",
				"frontendQuestionId": "1",
				"isFavor": false,
				"solutionNum": 15969,
				"title": "Two Sum",
				"titleCn": "\u4e24\u6570\u4e4b\u548c",
				"titleSlug": "two-sum",
				"topicTags": [{
					"id": "wg0rh",
					"name": "Array",
					"slug": "array",
					"nameTranslated": "\u6570\u7ec4",
					"__typename": "CommonTagNode"
				}, {
					"id": "wzve3",
					"name": "Hash Table",
					"slug": "hash-table",
					"nameTranslated": "\u54c8\u5e0c\u8868",
					"__typename": "CommonTagNode"
				}],
				"extra": {
					"companyTagNum": 391,
					"hasVideoSolution": true,
					"topCompanyTags": [{
						"imgUrl": "https://pic.leetcode-cn.com/5352d58e4b296e0704f20cfd99ecaec7af71d079b015923c72928650312c55b8-Messages Image(3349252251).png",
						"slug": "amazon",
						"__typename": "CommonTagNode"
					}, {
						"imgUrl": "https://pic.leetcode-cn.com/45a64add888e66ff6d3c551bed948528715996937b877aaf6fdc08eae74789f5-google-logo-png-open-2000.png",
						"slug": "google",
						"__typename": "CommonTagNode"
					}, {
						"imgUrl": "https://pic.leetcode-cn.com/cca55ecdfb504378955a9bf4f2f33897bb84f04f9cbf84ea96321ce5d959ee13-adobe1.png",
						"slug": "adobe",
						"__typename": "CommonTagNode"
					}],
					"__typename": "QuestionExtraInfoNode"
                        }
                    },

                * */
                // UnicodeUtil.toString(str);
                FirstProblem firstProblem = new FirstProblem();
                // 题目名
                String titleCn = UnicodeUtil.toString(jsonObject.getString("titleCn"));
                if (StringUtils.isNotBlank(titleCn)) {
                    firstProblem.setProblemName(titleCn);
                } else {
                    firstProblem.setProblemName(jsonObject.getString("title"));
                }
                //知识点
                JSONArray topicTags = jsonObject.getJSONArray("topicTags");
                String tags = list2StringSplitUniCOde(topicTags, "nameTranslated", "|");
                firstProblem.setTopicTags(tags);
                // 公司
                if (jsonObject.has("extra")) {
                    JSONObject extra = jsonObject.getJSONObject("extra");
                    String company = list2StringSplit(extra.getJSONArray("topCompanyTags"), "slug", "｜");
                    firstProblem.setCompanys(company);
                    // 考试次数
                    firstProblem.setTestCount(extra.getInt("companyTagNum"));
                } else {
                    firstProblem.setTestCount(1);
                }
                // 难度
                String difficulty = jsonObject.getString("difficulty");
                if (difficulty.equals("EASY")) {
                    firstProblem.setDifficulty(1);
                } else if (difficulty.equals("HARD")) {
                    firstProblem.setDifficulty(5);
                } else {
                    firstProblem.setDifficulty(3);// l3
                }
                // 平台
                firstProblem.setPlaform(PlatformEnum.LEETCODE.getName());
                // 链接
                String titleSlug = jsonObject.getString("titleSlug");
                String link = StrFormatter.format("https://leetcode-cn.com/problems/{}/", titleSlug);
                firstProblem.setLink(link);
                // 第三方id
                firstProblem.setThirdId(jsonObject.getString("frontendQuestionId"));
                // 描述
                firstProblem.setIntro("【LeetCode】" + firstProblem.getProblemName());
                // 源数据
                firstProblem.setMsg(jsonObject.toString());

                firstProblem.setUpdateTime(new Date());
                firstProblem.setCreateTime(new Date());
                try {
                    firstProblemRepository.save(firstProblem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("导入数据" + jsonObject.toString());

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    // 导入LintCode题目
    public void importLintCodeProblem(String stringjson) {
        try {
            JSONObject jsonValue = new JSONObject(stringjson);
            JSONArray jsonArray = jsonValue.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                /*
                DEMO样例
                	  {
                        "solution_count": 9,
                        "accepted_rate": 38,
                        "related_problem_count": 0,
                        "problem_type_id": 2,
                        "problem_id": 1899,
                        "title": "取物资II",
                        "status": 4,
                        "level": 2,
                        "fallibility_tags": [],
                        "problem_tags": [{
                            "tag_id": 396,
                            "tag_name": "二分法",
                            "can_hidden": true
                        }],
                        "company_tags": [{
                            "tag_id": 91,
                            "tag_name": "阿里巴巴",
                            "can_hidden": false
                        }],
                        "solution_count_tag": {
                            "tag_id": null,
                            "tag_name": "题解·9",
                            "can_hidden": false
                        },
                        "related_problem_count_tag": {},
                        "problem_type_image": [{
                            "identity": "new_storage_v2/public/staff/unknown/3/31/4a696d42-91ff-11eb-83d2-0242ac1d0002/算法彩色.png",
                            "url": "https://media-cn.lintcode.com/new_storage_v2/public/staff/unknown/3/31/4a696d42-91ff-11eb-83d2-0242ac1d0002/算法彩色.png",
                            "name": "算法彩色.png"
                        }],
                        "user_status": null,
                        "is_locked": true,
                        "is_unlocked": false,
                        "is_favorited": false,
                        "is_completed": true,
                        "is_high_frequency": false,
                        "source": null,
                        "clock_in_problem": false,
                        "tags": [{
                            "tag_id": null,
                            "tag_name": "题解·9",
                            "can_hidden": false
                        }, {
                            "tag_id": 396,
                            "tag_name": "二分法",
                            "can_hidden": true
                        }, {
                            "tag_id": 91,
                            "tag_name": "阿里巴巴",
                            "can_hidden": false
                        }]
                    },
                * */
                // UnicodeUtil.toString(str);
                FirstProblem firstProblem = new FirstProblem();
                // 题目名
                    firstProblem.setProblemName(jsonObject.getString("title"));
                //知识点
                if(jsonObject.has("problem_tags")){
                    JSONArray topicTags = jsonObject.getJSONArray("problem_tags");
                    String tags = list2StringSplitUniCOde(topicTags, "tag_name", "|");
                    firstProblem.setTopicTags(tags);
                }else {
                    firstProblem.setTopicTags("算法");
                }

                // 公司
                if (jsonObject.has("company_tags")) {
                    JSONArray company_tags = jsonObject.getJSONArray("company_tags");
                    String company = list2StringSplit(company_tags, "tag_name", "｜");
                    firstProblem.setCompanys(company);
                    // 考试次数
                    firstProblem.setTestCount(company_tags.length());
                } else {
                    firstProblem.setTestCount(1);
                }
                // 难度
                int difficulty = jsonObject.getInt("level");
                firstProblem.setDifficulty(difficulty);
                // 平台
                firstProblem.setPlaform(PlatformEnum.LintCODE.getName());
                // 链接
                String problem_id = jsonObject.getString("problem_id");
                String link = StrFormatter.format("https://www.lintcode.com/problem/{}/", problem_id);
                firstProblem.setLink(link);
                // 第三方id
                firstProblem.setThirdId(problem_id);
                // 描述
                boolean is_locked = jsonObject.getBoolean("is_locked");
                String pre = "【LintCode】";
                if(is_locked){
                    pre += "[付费]";
                }
                firstProblem.setIntro(pre + firstProblem.getProblemName());
                // 源数据
                firstProblem.setMsg(jsonObject.toString());

                firstProblem.setUpdateTime(new Date());
                firstProblem.setCreateTime(new Date());
                try {
                    firstProblemRepository.save(firstProblem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("导入数据" + jsonObject.toString());

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    // 导入牛客竞赛题目 html
    public void importNowCodeACMProblem(String stringhtml) {
        List<String> problems = ReUtil.findAll("<tr data-problemId=\"(.*?)</tr>", stringhtml, 1);

        /*

        13822">
        <td>
        <span class="ico-todo">未提交</span>
        </td>
        <td>
        <a href="/acm/problem/13822" target="_blank">NC13822</a>
        </td>
        <td class="fn-right" colspan="2">
        <a href="/acm/problem/13822" target="_blank" class="title">Keep In Line</a>
        <a href="javascript:void(0);" class="tag-label js-tag" target="_blank" data-id="1283">枚举</a>
        </td>
        <td>3星</td>
        <td>433/1204</td>
        <td>
        <a href="/acm/problem/13822" target="_blank" class="ico-bank-item ico-bank-code js-nc-title-tips" title="提交代码"></a>
        <a href="javascript:void(0);" data-id="13822" class="js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips" title="添加到题单"></a>
        </td>


        * */

        for(String problem : problems){
            Integer problemID = ReUtil.getFirstNumber(problem);
            String url = "https://ac.nowcoder.com/acm/problem/"+problemID;
            //System.out.println(problem);

            String ss = HtmlUtil.cleanHtmlTag(problem);
            //System.out.println(HtmlUtil.cleanHtmlTag(problem));

            String[] split = ss.split("\\n+|\\t+|\\r+|\\>");
            List<String> stringlist = new ArrayList<>();
            for(int i=0;i<split.length;i++){
                String a = split[i];
                String trim = a.trim();
                if(!trim.isEmpty()){
                    stringlist.add(trim);
                }
            }
            String star =  stringlist.get(stringlist.size()-2).substring(0,1);
            String tihaoID = stringlist.get(2);
            String problemTitle = stringlist.get(3);

            List<String> kpointsList = new ArrayList<>();
            for(int i = 4; i<stringlist.size()-2;i++){
                kpointsList.add(stringlist.get(i));
            }

            String platform = null;

            if(tihaoID.startsWith("NC")){
                platform = "牛客ACM";
            }else if(tihaoID.startsWith("UVALive")){
                platform = "UVALive";
            }else if(tihaoID.startsWith("UVA")){
                platform = "UVA";
            }

            FirstProblem firstProblem = new FirstProblem();
            firstProblem.setDifficulty(Integer.parseInt(star));
            firstProblem.setLink(url);
            firstProblem.setTopicTags(List2StringLableUtils.list2StringSplit(kpointsList,"|"));
            firstProblem.setThirdId(tihaoID);
            firstProblem.setPlaform(platform);
            firstProblem.setProblemName(problemTitle);
            firstProblem.setMsg(problem);
            firstProblem.setCompanys("牛客ACM");
            firstProblem.setTestCount(1);
            firstProblem.setIntro("【牛客竞赛】"+problemTitle);
            firstProblem.setUpdateTime(new Date());
            firstProblem.setCreateTime(new Date());
            try {
                firstProblemRepository.save(firstProblem);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("导入数据" + firstProblem);

        }

    }


    //list转String竖线分割
    public String list2StringSplit(JSONArray jsonArray, String name, String split) {
        String res = "";
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String lablename = jsonObject.getString(name);
                if (i > 0 && !res.isEmpty()) {
                    res = res.concat(split);
                }
                res = res.concat(lablename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    //list转String竖线分割
    public String list2StringSplitUniCOde(JSONArray jsonArray, String name, String split) {
        String res = "";
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String lablename = UnicodeUtil.toString(jsonObject.getString(name));
                if (i > 0 && !res.isEmpty()) {
                    res = res.concat(split);
                }
                res = res.concat(lablename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    //list转String竖线分割
    public String list2StringSplit(JSONArray jsonArray) {
        return list2StringSplit(jsonArray, "name", "|");
    }
}
