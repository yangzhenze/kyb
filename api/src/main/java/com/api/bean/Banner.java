package com.api.bean;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
* @author auto create
* @Date 2018/11/23 18:11:12
*/
@Entity
@Table(name="banner")
public class Banner implements Serializable{

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
     *图片
     */
    @Getter
    @Setter
    @Column(name="image")
    private String image;

    /**
     *路径
     */
    @Getter
    @Setter
    @Column(name="url")
    private String url;

    /**
     *排序
     */
    @Getter
    @Setter
    @Column(name="sort")
    private Integer sort;

    /**
     *创建时间
     */
    @Getter
    @Setter
    @Column(name="create_date")
    private java.util.Date createDate;

}
