package com.example.demo.model;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/10/25 15:07
 */

/*
 * Copyright © 2020-2035 erupt.xyz All rights reserved.
 * Author: YuePeng (erupts@126.com)
 */

import javax.persistence.*;

import com.example.demo.model.blog.BlogTagHandler;
import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_erupt.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.upms.model.base.HyperModel;
import xyz.erupt.jpa.model.BaseModel;
import java.util.Set;
import java.util.Date;

@Erupt(name = "题库")
@Table(name = "problem")
@Entity
public class Problem extends BaseModel {

    @EruptField(
            views = @View(
                    title = "题目名"
            ),
            edit = @Edit(
                    title = "题目名",
                    type = EditType.INPUT, search = @Search, notNull = true,
                    inputType = @InputType
            )
    )
    private String title;


    @EruptField(
            views = @View(title = "知识点", template = "value&&value.replace(/\\|/g,'<span class=\"text-red\"> | </span>')"),
            edit = @Edit(title = "知识点", notNull = true, type = EditType.TAGS,
                    tagsType = @TagsType(fetchHandler = BlogTagHandler.class)
            )
    )
    private String knowledgePoint;

    @EruptField(
            views = @View(
                    title = "题目难度", sortable = true
            ),
            edit = @Edit(
                    title = "题目难度",
                    type = EditType.SLIDER, search = @Search,
                    sliderType = @SliderType(max = 5)
            )
    )
    private Integer difficulty;

    @EruptField(
            views = @View(
                    title = "做题时间"
            ),
            edit = @Edit(
                    title = "做题时间",
                    type = EditType.DATE, search = @Search, notNull = true,
                    dateType = @DateType(type = DateType.Type.TIME)
            )
    )
    private String costTime;

    @EruptField(
            views = @View(
                    title = "题目链接"
            ),
            edit = @Edit(
                    title = "题目链接",
                    type = EditType.INPUT, search = @Search, notNull = true,
                    inputType = @InputType
            )
    )
    private String url;

}