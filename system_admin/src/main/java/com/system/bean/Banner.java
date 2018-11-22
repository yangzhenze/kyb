package com.system.bean;

import java.io.Serializable;
import com.system.common.anotation.Table;
import com.system.common.anotation.Column;
import lombok.Getter;
import lombok.Setter;
/**
* @author auto create
* @Date 2018/45/22 14:45:55
*/
@Table(name="banner")
public class Banner implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *id
     */
    @Getter
    @Setter
    @Column(name="id",isPK = true)
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
