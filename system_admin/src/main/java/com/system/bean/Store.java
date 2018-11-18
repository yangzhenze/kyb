package com.system.bean;

import java.io.Serializable;
import com.system.common.anotation.Table;
import com.system.common.anotation.Column;
import lombok.Getter;
import lombok.Setter;
/**
* @author auto create
* @Date 2018/33/18 13:33:39
*/
@Table(name="store")
public class Store implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *ID
     */
    @Getter
    @Setter
    @Column(name="id",isPK = true)
    private Integer id;

    /**
     *用户ID
     */
    @Getter
    @Setter
    @Column(name="user_id")
    private String userId;

    /**
     *商店名称
     */
    @Getter
    @Setter
    @Column(name="store_name")
    private String storeName;

    /**
     *宣传语
     */
    @Getter
    @Setter
    @Column(name="propaganda")
    private String propaganda;

    /**
     *背景图
     */
    @Getter
    @Setter
    @Column(name="image")
    private String image;

    /**
     *创建时间
     */
    @Getter
    @Setter
    @Column(name="create_date")
    private java.util.Date createDate;

}
