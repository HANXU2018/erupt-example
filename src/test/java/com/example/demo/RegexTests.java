package com.example.demo;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HtmlUtil;
import com.example.demo.dao.ArticleRepository;
import com.example.demo.model.Article;
import com.example.demo.model.firstTiku.FirstProblem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class RegexTests {

    @Test
    void getProblList(){
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
        String problem = "13822\">\n" +
                "        <td>\n" +
                "        <span class=\"ico-todo\">未提交</span>\n" +
                "        </td>\n" +
                "        <td>\n" +
                "        <a href=\"/acm/problem/13822\" target=\"_blank\">NC13822</a>\n" +
                "        </td>\n" +
                "        <td class=\"fn-right\" colspan=\"2\">\n" +
                "        <a href=\"/acm/problem/13822\" target=\"_blank\" class=\"title\">Keep In Line</a>\n" +
                "        <a href=\"javascript:void(0);\" class=\"tag-label js-tag\" target=\"_blank\" data-id=\"1283\">枚举</a>\n" +
                "        </td>\n" +
                "        <td>3星</td>\n" +
                "        <td>433/1204</td>\n" +
                "        <td>\n" +
                "        <a href=\"/acm/problem/13822\" target=\"_blank\" class=\"ico-bank-item ico-bank-code js-nc-title-tips\" title=\"提交代码\"></a>\n" +
                "        <a href=\"javascript:void(0);\" data-id=\"13822\" class=\"js-collect-question ico-bank-item ico-bank-copy js-nc-title-tips\" title=\"添加到题单\"></a>\n" +
                "        </td>";
            Integer problemID = ReUtil.getFirstNumber(problem);
            String url = "https://ac.nowcoder.com/acm/problem/"+problemID;
            System.out.println(problem);

        System.out.println("============>");
        String ss = HtmlUtil.cleanHtmlTag(problem);
        System.out.println(HtmlUtil.cleanHtmlTag(problem));

        String[] split = ss.split("\\n+|\\t+|\\r+|\\>");
        List<String> stringlist = new ArrayList<>();
        for(int i=0;i<split.length;i++){
            String a = split[i];
            String trim = a.trim();
            if(!trim.isEmpty()){
                stringlist.add(trim);
            }
        }
        System.out.println(stringlist);

        System.out.println(split);
        System.out.println(ss);
        FirstProblem firstProblem = new FirstProblem();
        firstProblem.setMsg(ss);

            //List<String> all = ReUtil.findAll("<td>(.*?)星</td>", problem, 1);
            //
            //String star = ReUtil.findAll("</a></td><td>(.*?)星</td>", problem, 1).get(0);
            //
            //List<String> strings = ReUtil.findAll("target=\"_blank\"(.*?)</a>", problem, 1);
            //String tihaoID = strings.get(0).substring(1);
            //String problemTitle = ReUtil.findAll("class=\"title\">(.*?)</a>", strings.get(1), 1).get(0);
            //
            //List<String> kpointsList = new ArrayList<>();
            //for(int i = 2; i<strings.size()-1;i++){
            //    String kpoint = ReUtil.findAll(">(.*?)</a>", strings.get(1), 1).get(0);
            //    kpointsList.add(kpoint);
            //}
            //
            //String platform = null;
            //
            //if(tihaoID.startsWith("NC")){
            //    platform = "牛客ACM";
            //}else if(tihaoID.startsWith("UVALive")){
            //    platform = "UVALive";
            //}else if(tihaoID.startsWith("UVA")){
            //    platform = "UVA";
            //}
            //
            //FirstProblem firstProblem = new FirstProblem();
            //firstProblem.setDifficulty(Integer.parseInt(star));
            //firstProblem.setLink(url);
            //firstProblem.setTopicTags(kpointsList.toString());
            //firstProblem.setThirdId(tihaoID);
            //firstProblem.setPlaform("牛客ACM");
            //firstProblem.setProblemName(problemTitle);
            //firstProblem.setCompanys("牛客ACM");

            //System.out.println(firstProblem);


    }

}
