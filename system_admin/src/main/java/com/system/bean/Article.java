package com.system.bean;

import java.io.Serializable;
import com.system.common.anotation.Table;
import com.system.common.anotation.Column;
import lombok.Getter;
import lombok.Setter;
/**
* @author auto create
* @Date 2018/33/18 13:33:38
*/
@Table(name="help_article")
public class Article implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *id
     */
    @Getter
    @Setter
    @Column(name="id",isPK = true)
    private Integer id;

    /**
     *标题
     */
    @Getter
    @Setter
    @Column(name="title")
    private String title;

    /**
     *内容
     */
    @Getter
    @Setter
    @Column(name="content")
    private String content;

    /**
     *创建时间
     */
    @Getter
    @Setter
    @Column(name="create_date")
    private java.util.Date createDate;

}
