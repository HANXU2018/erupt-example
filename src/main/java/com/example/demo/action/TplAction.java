package com.example.demo.action;

import xyz.erupt.annotation.config.Comment;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/10/26 11:36
 */
@Comment("修饰方法必须返回Map<String,Object>类型")
public @interface TplAction {

    @Comment("tpl目录下文件名称，该值也作为权限特征使用")
    String value();

    @Comment("文件路径，为空则以value值作为文件路径")
    String path() default "";

}