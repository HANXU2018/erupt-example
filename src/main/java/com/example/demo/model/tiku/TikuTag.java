package com.example.demo.model.tiku;/*
 * Copyright © 2020-2035 erupt.xyz All rights reserved.
 * Author: YuePeng (erupts@126.com)
 */

import javax.persistence.*;
import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_erupt.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.upms.model.base.HyperModel;
import xyz.erupt.jpa.model.BaseModel;
import java.util.Set;
import java.util.Date;

@Erupt(name = "知识标签")
@Table(name = "tiku_tag")
@Entity
public class TikuTag extends BaseModel {

    @EruptField(
            views = @View(
                    title = "知识点", sortable = true
            ),
            edit = @Edit(
                    title = "知识点",
                    type = EditType.INPUT, search = @Search, notNull = true,
                    inputType = @InputType
            )
    )
    private String tagName;

    @EruptField(
            views = @View(
                    title = "状态"
            ),
            edit = @Edit(
                    title = "状态",
                    type = EditType.CHOICE, search = @Search, notNull = true,
                    choiceType = @ChoiceType(vl = {@VL(value = "xxx", label = "审核通过"), @VL(value = "yyy", label = "审核中")})
            )
    )
    private String status;

}