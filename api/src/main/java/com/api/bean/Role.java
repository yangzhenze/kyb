package com.api.bean;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
* @author auto create
* @Date 2018/17/23 14:17:35
*/
@Table(name="sys_role")
public class Role implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *主键id
     */
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    /**
     *角色名
     */
    @Getter
    @Setter
    @Column(name="role_name")
    private String roleName;

    /**
     *描述
     */
    @Getter
    @Setter
    @Column(name="description")
    private String description;

    /**
     *创建时间
     */
    @Getter
    @Setter
    @Column(name="create_date")
    private java.util.Date createDate;

}
