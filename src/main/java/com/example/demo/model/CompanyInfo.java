package com.example.demo.model;

/*
 * Copyright © 2020-2035 erupt.xyz All rights reserved.
 * Author: YuePeng (erupts@126.com)
 */

import javax.persistence.*;

import lombok.Data;
import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_erupt.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.upms.model.base.HyperModel;
import xyz.erupt.jpa.model.BaseModel;
import java.util.Set;
import java.util.Date;

@Erupt(name = "公司信息")
@Table(name = "company_info")
@Entity
@Data
public class CompanyInfo  extends HyperModel  {

    @EruptField(
            views = @View(
                    title = "公司名"
            ),
            edit = @Edit(
                    title = "公司名",
                    type = EditType.INPUT, search = @Search, notNull = true,
                    inputType = @InputType
            )
    )
    private String companyName;

    @EruptField(
            views = @View(
                    title = "公司介绍"
            ),
            edit = @Edit(
                    title = "公司介绍",
                    type = EditType.TEXTAREA, search = @Search, notNull = true
            )
    )
    private @Lob String info;

    @EruptField(
            views = @View(
                    title = "公司logo"
            ),
            edit = @Edit(
                    title = "公司logo",
                    type = EditType.ATTACHMENT, search = @Search, notNull = true,
                    attachmentType = @AttachmentType(type = AttachmentType.Type.IMAGE)
            )
    )
    private String imageurl;

    @EruptField(
            views = @View(
                    title = "原数据"
            ),
            edit = @Edit(
                    title = "原数据",
                    type = EditType.TEXTAREA, search = @Search
            )
    )
    private @Lob String msg;

}