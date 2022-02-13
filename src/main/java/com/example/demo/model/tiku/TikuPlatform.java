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

@Erupt(name = "平台信息",
        drills = {
                @Drill(code = "drill",
                        title = "数据钻取",
                        //最终生成的表达式为：EruptTest.id = DrillErupt.eruptTest.id
                        link = @Link(linkErupt = TikuCollection.class,  //关联表
                                joinColumn = "tikuPlatformId.id"))  //关联表达式
        })
@Table(name = "tiku_platform")
@Entity
public class TikuPlatform extends BaseModel {

    @EruptField(
            views = @View(
                    title = "平台名", sortable = true
            ),
            edit = @Edit(
                    title = "平台名",
                    type = EditType.INPUT, search = @Search, notNull = true,
                    inputType = @InputType
            )
    )
    private String tikuplatformTitle;

    @EruptField(
            views = @View(
                    title = "平台简介"
            ),
            edit = @Edit(
                    title = "平台简介",
                    type = EditType.TEXTAREA, search = @Search, notNull = true
            )
    )
    private @Lob String tikuPlatformContent;

}