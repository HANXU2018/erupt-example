package com.example.demo.model.firstTiku;
import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_erupt.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.upms.model.base.HyperModel;
import xyz.erupt.jpa.model.BaseModel;
import java.util.Set;
import java.util.Date;
/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2022/1/7 20:30
 */
@Erupt(name = "题目列表",
        filter = @Filter("deleted = false"),
        power = @Power(importable = true, export = true))
@Table(name = "first_problem")
@Entity
@SQLDelete(sql="update first_problem set deleted = true, delete_time = now() where id = ?")
public class FirstProblem extends HyperModel {

    @EruptField(
            views = @View(
                    title = "题目名", sortable = true
            ),
            edit = @Edit(
                    title = "题目名",
                    type = EditType.INPUT, search = @Search, notNull = true,
                    inputType = @InputType
            )
    )
    private String problemName;

    @EruptField(
            views = @View(
                    title = "难度", sortable = true
            ),
            edit = @Edit(
                    title = "难度",
                    type = EditType.SLIDER, search = @Search,
                    sliderType = @SliderType(max = 5)
            )
    )
    private Integer difficulty = 0;

    @EruptField(
            views = @View(
                    title = "平台", sortable = true
            ),
            edit = @Edit(
                    title = "平台",
                    type = EditType.CHOICE, search = @Search, notNull = true,
                    choiceType = @ChoiceType(vl = {
                            @VL(value = "其他", label = "其他")
                            , @VL(value = "牛客", label = "牛客")
                            , @VL(value = "牛客ACM", label = "牛客ACM")
                            , @VL(value = "LeetCode", label = "LeetCode")
                            , @VL(value = "LintCode", label = "LintCode")
                            , @VL(value = "PAT", label = "PAT")
                            , @VL(value = "PAT_ZOJ", label = "PAT_ZOJ")
                    })
            )
    )
    private String plaform = "其他";

    @EruptField(
            views = @View(
                    title = "知识点", sortable = true
            ),
            edit = @Edit(
                    title = "知识点",
                    type = EditType.TAGS, search = @Search,
                    tagsType = @TagsType
            )
    )
    private String topicTags;

    @EruptField(
            views = @View(
                    title = "公司"
            ),
            edit = @Edit(
                    title = "公司",
                    type = EditType.TAGS, search = @Search,
                    tagsType = @TagsType
            )
    )
    private String companys;

    @EruptField(
            views = @View(
                    title = "链接地址",
                    type = ViewType.LINK
            ),
            edit = @Edit(
                    title = "链接地址",
                    type = EditType.INPUT, search = @Search, notNull = true,
                    inputType = @InputType
            )
    )
    private String link;

    @EruptField(
            views = @View(
                    title = "考察次数", sortable = true
            ),
            edit = @Edit(
                    title = "考察次数",
                    type = EditType.NUMBER, search = @Search,
                    numberType = @NumberType
            )
    )
    private Integer testCount;

    @EruptField(
            views = @View(
                    title = "第三方ID", sortable = true
            ),
            edit = @Edit(
                    title = "第三方ID",
                    type = EditType.INPUT, search = @Search,
                    inputType = @InputType
            )
    )
    private String thirdId;

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

    @EruptField(
            views = @View(
                    title = "描述"
            ),
            edit = @Edit(
                    title = "描述",
                    type = EditType.TEXTAREA, search = @Search,
                    inputType = @InputType
            )
    )
    private String intro;

    private Date deleteTime;

    private Boolean deleted = false;

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getPlaform() {
        return plaform;
    }

    public void setPlaform(String plaform) {
        this.plaform = plaform;
    }

    public String getTopicTags() {
        return topicTags;
    }

    public void setTopicTags(String topicTags) {
        this.topicTags = topicTags;
    }

    public String getCompanys() {
        return companys;
    }

    public void setCompanys(String companys) {
        this.companys = companys;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getTestCount() {
        return testCount;
    }

    public void setTestCount(Integer testCount) {
        this.testCount = testCount;
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    // 不对外暴露源数据
    //public String getMsg() {
    //    return msg;
    //}

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "FirstProblem{" +
                "problemName='" + problemName + '\'' +
                ", difficulty=" + difficulty +
                ", plaform='" + plaform + '\'' +
                ", topicTags='" + topicTags + '\'' +
                ", companys='" + companys + '\'' +
                ", link='" + link + '\'' +
                ", testCount=" + testCount +
                ", thirdId='" + thirdId + '\'' +
                ", msg='" + msg + '\'' +
                ", intro='" + intro + '\'' +
                ", deleteTime=" + deleteTime +
                ", deleted=" + deleted +
                '}';
    }
}