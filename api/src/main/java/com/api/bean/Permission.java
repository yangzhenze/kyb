package com.api.bean;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
* @author auto create
* @Date 2018/11/23 18:11:13
*/
@Entity
@Table(name="sys_permission")
public class Permission implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *主键
     */
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    /**
     *权限名
     */
    @Getter
    @Setter
    @Column(name="permission_name")
    private String permissionName;

    /**
     *权限类型0.菜单，1.功能
     */
    @Getter
    @Setter
    @Column(name="permission_type")
    private Integer permissionType;

    /**
     *访问路径
     */
    @Getter
    @Setter
    @Column(name="visit_url")
    private String visitUrl;

    /**
     *访问方式
     */
    @Getter
    @Setter
    @Column(name="method")
    private String method;

    /**
     *图标
     */
    @Getter
    @Setter
    @Column(name="icon")
    private String icon;

    /**
     *排序
     */
    @Getter
    @Setter
    @Column(name="sort")
    private Integer sort;

    /**
     *父级id
     */
    @Getter
    @Setter
    @Column(name="parent_id")
    private Integer parentId;

    /**
     *创建人
     */
    @Getter
    @Setter
    @Column(name="create_name")
    private String createName;

    /**
     *创建时间
     */
    @Getter
    @Setter
    @Column(name="create_date")
    private java.util.Date createDate;

    /**
     *更新人
     */
    @Getter
    @Setter
    @Column(name="update_name")
    private String updateName;

}
