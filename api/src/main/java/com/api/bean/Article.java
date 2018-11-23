package com.api.bean;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
* @author auto create
* @Date 2018/17/23 14:17:35
*/
@Table(name="help_article")
public class Article implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *id
     */
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
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
