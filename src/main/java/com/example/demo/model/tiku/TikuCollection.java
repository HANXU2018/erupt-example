package com.example.demo.model.tiku;/*
 * Copyright © 2020-2035 erupt.xyz All rights reserved.
 * Author: YuePeng (erupts@126.com)
 */

import javax.persistence.*;

import com.example.demo.model.tiku.TikuPlatform;
import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_erupt.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.upms.model.base.HyperModel;
import xyz.erupt.jpa.model.BaseModel;
import java.util.Set;
import java.util.Date;

@Erupt(name = "专辑信息")
@Table(name = "tiku_collection")
@Entity
public class TikuCollection extends BaseModel {

    @EruptField(
            views = @View(
                    title = "平台id", sortable = true
            ),
            edit = @Edit(
                    title = "平台id",
                    type = EditType.TAB_TABLE_REFER, search = @Search, notNull = true
            )
    )
    @ManyToMany
    @JoinTable(name = "tiku_collection_tiku_platform",
            joinColumns = @JoinColumn(name = "tiku_collection_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tiku_platform_id", referencedColumnName = "id"))
    private Set<TikuPlatform> tikuPlatformId;

    @EruptField(
            views = @View(
                    title = "题目专辑名", sortable = true
            ),
            edit = @Edit(
                    title = "题目专辑名",
                    type = EditType.INPUT, search = @Search, notNull = true,
                    inputType = @InputType
            )
    )
    private String tikuCollectionTitle;

    @EruptField(
            views = @View(
                    title = "题目专辑简介", sortable = true
            ),
            edit = @Edit(
                    title = "题目专辑简介",
                    type = EditType.TEXTAREA, search = @Search, notNull = true
            )
    )
    private @Lob String tikuCollectionContent;

}