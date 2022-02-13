package com.example.demo.model.imports;
import javax.persistence.*;

import com.example.demo.handler.JsonImportOperationHandlerImpl;
import com.example.demo.handler.OperationHandlerImpl;
import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_erupt.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.upms.model.base.HyperModel;
import xyz.erupt.jpa.model.BaseModel;
import java.util.Set;
import java.util.Date;

@Erupt(name = "Json数据导入",
        rowOperation = {
        @RowOperation(
                title = "导入json",
                code = "MULTI",
                mode = RowOperation.Mode.MULTI,
                operationHandler = JsonImportOperationHandlerImpl.class)
        }
)
@Table(name = "jsonimport")
@Entity
public class JsonImport extends BaseModel {

    @EruptField(
            views = @View(
                    title = "导入内容", sortable = true
            ),
            edit = @Edit(
                    title = "导入内容",
                    type = EditType.CHOICE, search = @Search, notNull = true,
                    choiceType = @ChoiceType(vl = {@VL(value = "牛客校招笔试日历", label = "牛客校招笔试日历")
                            ,@VL(value = "牛客竞赛日历", label = "牛客竞赛日历")
                            ,@VL(value = "牛客上周考过", label = "牛客上周考过")
                            ,@VL(value = "牛客竞赛题库", label = "牛客竞赛题库")
                            ,@VL(value = "牛客公司LOGO", label = "牛客公司LOGO")
                            ,@VL(value = "Lintcode题库", label = "Lintcode题库")
                            ,@VL(value = "Leetcode题库", label = "Leetcode题库")
                            ,@VL(value = "Leetcode知识点", label = "Leetcode知识点")
                            ,@VL(value = "Leetcode题目集合", label = "Leetcode题目集合")
                            ,@VL(value = "Leetcode周赛日历", label = "Leetcode周赛日历")
                            ,@VL(value = "PAT天梯赛", label = "PAT天梯赛")
                            ,@VL(value = "PAT乙级", label = "PAT乙级")
                            ,@VL(value = "PAT甲级", label = "PAT甲级")
                            ,@VL(value = "PAT顶级", label = "PAT顶级")
                            ,@VL(value = "PATZOJ", label = "PATZOJ")
                            ,@VL(value = "赛码网校招笔试日历", label = "赛码网校招笔试日历")
                            ,@VL(value = "赛码网校招官网广告", label = "赛码网校招官网广告")
                            ,@VL(value = "其他", label = "其他")
                    })
            )
    )
    private String event="其他";


    @EruptField(
            views = @View(
                    title = "导入状态", sortable = true
            ),
            edit = @Edit(
                    title = "导入状态",
                    type = EditType.CHOICE, search = @Search, notNull = true,
                    choiceType = @ChoiceType(vl = {@VL(value = "未开始", label = "未开始")
                            ,@VL(value = "成功", label = "成功")
                            , @VL(value = "失败", label = "失败")})
            )
    )
    private String status="未开始";

    @EruptField(
            views = @View(
                    title = "Json数据", sortable = true
            ),
            edit = @Edit(
                    title = "Json数据",
                    type = EditType.CODE_EDITOR, search = @Search, notNull = true,
                    codeEditType = @CodeEditorType(language = "json")
            )
    )
    private @Lob String jsonData;

    @EruptField(
            views = @View(
                    title = "备注"
            ),
            edit = @Edit(
                    title = "备注",
                    type = EditType.TEXTAREA, search = @Search,
                    inputType = @InputType
            )
    )
    private String msg;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonImport{" +
                "event='" + event + '\'' +
                ", jsonData='" + jsonData + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}