package com.example.demo.model.calendar;

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

@Erupt(name = "竞赛日历",
        filter = @Filter("deleted = false"),
        power = @Power(importable = true, export = true))
@Table(name = "calendar")
@Entity
@SQLDelete(sql="update calendar set deleted = true, delete_time = now() where id = ?")
public class Calendar extends HyperModel {

    @EruptField(
            views = @View(
                    title = "竞赛名", sortable = true
            ),
            edit = @Edit(
                    title = "竞赛名",
                    type = EditType.INPUT, search = @Search, notNull = true,
                    inputType = @InputType
            )
    )
    private String title;

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
    private String odName;

    @EruptField(
            views = @View(
                    title = "开始时间",sortable = true
            ),
            edit = @Edit(
                    title = "开始时间",
                    type = EditType.DATE, search = @Search, notNull = true,
                    dateType = @DateType(type = DateType.Type.DATE_TIME)
            )
    )
    private Date start;

    @EruptField(
            views = @View(
                    title = "结束时间", sortable = true
            ),
            edit = @Edit(
                    title = "结束时间",
                    type = EditType.DATE, search = @Search, notNull = true,
                    dateType = @DateType(type = DateType.Type.DATE_TIME)
            )
    )
    private Date end;

    @EruptField(
            views = @View(
                    title = "链接", type = ViewType.LINK ,sortable = true
            ),
            edit = @Edit(
                    title = "链接",
                    type = EditType.INPUT, search = @Search, notNull = true,
                    inputType = @InputType
            )
    )
    private String url;

    @EruptField(
            views = @View(title = "图片"),
            edit = @Edit(title = "图片", type = EditType.ATTACHMENT,
                    attachmentType = @AttachmentType(type = AttachmentType.Type.IMAGE, maxLimit = 3))
    )
    private String pic;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOdName() {
        return odName;
    }

    public void setOdName(String odName) {
        this.odName = odName;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "title='" + title + '\'' +
                ", odName='" + odName + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", url='" + url + '\'' +
                ", pic='" + pic + '\'' +
                ", msg='" + msg + '\'' +
                ", intro='" + intro + '\'' +
                ", deleteTime=" + deleteTime +
                ", deleted=" + deleted +
                '}';
    }
}