package com.example.demo.model;

import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.RowOperation;
import xyz.erupt.annotation.sub_erupt.Tpl;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/10/26 10:40
 */
@Erupt(
        name = "按钮打开模板",
        rowOperation = @RowOperation(
                code = "tpl", title = "模板按钮", type = RowOperation.Type.TPL,
                tpl = @Tpl(
                        path = "/tpl/operator.ftl",     //模板文件路径
                        tplHandler = TestErupt.class,  //数据绑定到模板，可不设置
                        engine = Tpl.Engine.FreeMarker //缺省值
                )
        )
)
@Entity
public class TestErupt extends BaseModel implements Tpl.TplHandler {

    @EruptField(
            views = @View(title = "名称"),
            edit = @Edit(title = "名称")
    )
    private String name;

    @EruptField(
            views = @View(title = "数值"),
            edit = @Edit(title = "数值")
    )
    private Integer number;

    @Override
    public void bindTplData(Map<String, Object> binding, String[] params) {
        binding.put("title", "选中的数据");
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "TestErupt{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}