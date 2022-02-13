package com.example.demo.action;

import org.springframework.stereotype.Service;
import xyz.erupt.tpl.annotation.EruptTpl;
import xyz.erupt.tpl.annotation.TplAction;

import java.util.*;

/**
 * @author YuePeng
* date 2020-02-24
 */
@EruptTpl
@Service
public class FreemarkerAction {

    @TplAction(value = "freemarker.ftl")
    public Map<String, Object> dashboard() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> mp = new LinkedHashMap<>();
        mp.put("annotation", 'E');
        mp.put("core", 'R');
        mp.put("auth", 'U');
        mp.put("web", 'P');
        mp.put("mongodb", 'T');
        mp.put("bi", '-');
        mp.put("job", '-');
        mp.put("tpl", '-');
        mp.put("generator", '-');
        map.put("color", new String[]{
                "#eb776e", "#56aad6", "#69d5e7", "#f686e5", "#29ae94", "#fbd364",
                "#4da1ff", "#ff6e4b", "#ffc524", "#e07de9", "#42e9e1", "#a9f", "#a90",
                "#09f", "#928bff"
        });
        map.put("map", mp);
        return map;
    }

    @TplAction(value = "paiming.ftl")
    public Map<String, Object> paiming() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> mp = new LinkedHashMap<>();
        List<Object> users = new ArrayList<>();
        for(int i= 0 ;i<5;i++){
            Map<String, Object> user = new LinkedHashMap<>();
            user.put("number",i+1);
            user.put("img","https://cdn.jellow.site/FrNoNa-sk788pA-xgH9qrpN6quWh.jpg");
            if(i==0){
                user.put("level","/img/1.png");
                user.put("title","状元");
            }
            if(i==1){
                user.put("level","/img/2.png");
                user.put("title","探花");
            }
            if(i==2){
                user.put("level","/img/3.png");
                user.put("title","榜眼");
            }
            user.put("good","名字"+i);

            user.put("jknum","JK"+i);

            user.put("km",i+1000);

            users.add(user);

            System.out.println("user====>"+user.toString());
        }

        mp.put("annotation", 'E');
        mp.put("core", 'R');

        map.put("color", new String[]{
                "#eb776e", "#56aad6", "#69d5e7", "#f686e5", "#29ae94", "#fbd364",
                "#4da1ff", "#ff6e4b", "#ffc524", "#e07de9", "#42e9e1", "#a9f", "#a90",
                "#09f", "#928bff"
        });
        map.put("map", mp);
        map.put("us", users.toArray());

        map.put("usname", "用户名");

        System.out.println("map====>"+map.toString());
        return map;
    }

}
