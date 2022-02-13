package com.example.demo.model.tiku;/*
 * Copyright © 2020-2035 erupt.xyz All rights reserved.
 * Author: YuePeng (erupts@126.com)
 */

import javax.persistence.*;

import com.example.demo.model.tiku.TikuCollection;
import com.example.demo.model.tiku.TikuPlatform;
import com.example.demo.model.tiku.TikuTag;
import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_erupt.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.upms.model.base.HyperModel;
import xyz.erupt.jpa.model.BaseModel;
import java.util.Set;
import java.util.Date;

@Erupt(name = "题目信息")
@Table(name = "tiku_problem")
@Entity
public class TikuProblem extends BaseModel {

    @EruptField(
            views = @View(
                    title = "题目标题", sortable = true
            ),
            edit = @Edit(
                    title = "题目标题",
                    type = EditType.INPUT, search = @Search, notNull = true,
                    inputType = @InputType
            )
    )
    private String tikuProblemTitle;

    @EruptField(
            views = @View(
                    title = "题目链接", sortable = true, type = ViewType.LINK
            ),
            edit = @Edit(
                    title = "题目链接",
                    type = EditType.INPUT, search = @Search, notNull = true,
                    inputType = @InputType
            )
    )
    private String tikuProblemUrl;

    @EruptField(
            views = @View(
                    title = "题目内容", sortable = true
            ),
            edit = @Edit(
                    title = "题目内容",
                    type = EditType.HTML_EDITOR, search = @Search, notNull = true,
                    htmlEditorType = @HtmlEditorType(HtmlEditorType.Type.UEDITOR)
            )
    )
    private @Lob String tikuProblemContent;

    @EruptField(
            views = @View(
                    title = "来源平台", sortable = true
            ),
            edit = @Edit(
                    title = "来源平台",
                    type = EditType.TAB_TABLE_REFER, search = @Search, notNull = true
            )
    )
    @ManyToMany
    @JoinTable(name = "tiku_problem_tiku_platform",
            joinColumns = @JoinColumn(name = "tiku_problem_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tiku_platform_id", referencedColumnName = "id"))
    private Set<TikuPlatform> tikuPlatform;

    @EruptField(
            views = @View(
                    title = "专辑", sortable = true
            ),
            edit = @Edit(
                    title = "专辑",
                    type = EditType.TAB_TABLE_REFER, search = @Search, notNull = true
            )
    )
    @ManyToMany
    @JoinTable(name = "tiku_problem_tiku_collection",
            joinColumns = @JoinColumn(name = "tiku_problem_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tiku_collection_id", referencedColumnName = "id"))
    private Set<TikuCollection> tikuCollection;

    @EruptField(
            views = @View(
                    title = "知识点", sortable = true
            ),
            edit = @Edit(
                    title = "知识点",
                    type = EditType.TAB_TABLE_REFER, search = @Search, notNull = true
            )
    )
    @ManyToMany
    @JoinTable(name = "tiku_problem_tiku_tag",
            joinColumns = @JoinColumn(name = "tiku_problem_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tiku_tag_id", referencedColumnName = "id"))
    private Set<TikuTag> tikuTag;

    @EruptField(
            views = @View(
                    title = "题目难度", sortable = true
            ),
            edit = @Edit(
                    title = "题目难度",
                    type = EditType.SLIDER, search = @Search, notNull = true,
                    sliderType = @SliderType(max = 5,min = 1)
            )
    )
    private Integer tikuProblemRating;

    @EruptField(
            views = @View(
                    title = "点赞", sortable = true
            ),
            edit = @Edit(
                    title = "点赞",
                    type = EditType.NUMBER, search = @Search, notNull = true,
                    numberType = @NumberType
            )
    )
    private Integer tikuProblemStar;
}