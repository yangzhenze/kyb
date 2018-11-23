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
@Table(name="sys_admin")
public class Admin implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *用户id
     */
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    /**
     *头像
     */
    @Getter
    @Setter
    @Column(name="head_portrait")
    private String headPortrait;

    /**
     *管理员帐号
     */
    @Getter
    @Setter
    @Column(name="account")
    private String account;

    /**
     *管理员姓名
     */
    @Getter
    @Setter
    @Column(name="name")
    private String name;

    /**
     *管理员密码
     */
    @Getter
    @Setter
    @Column(name="password")
    private String password;

    /**
     *性别
     */
    @Getter
    @Setter
    @Column(name="gender")
    private String gender;

    /**
     *手机号
     */
    @Getter
    @Setter
    @Column(name="phone")
    private String phone;

    /**
     *邮箱
     */
    @Getter
    @Setter
    @Column(name="email")
    private String email;

    /**
     *是否启用
     */
    @Getter
    @Setter
    @Column(name="is_enable")
    private String isEnable;

    /**
     *管理员类型
     */
    @Getter
    @Setter
    @Column(name="admin_type")
    private Integer adminType;

    /**
     *描述
     */
    @Getter
    @Setter
    @Column(name="description")
    private String description;

    /**
     *登录次数
     */
    @Getter
    @Setter
    @Column(name="login_num")
    private Integer loginNum;

    /**
     *最后登录时间
     */
    @Getter
    @Setter
    @Column(name="last_login")
    private java.util.Date lastLogin;

    /**
     *最后登录IP
     */
    @Getter
    @Setter
    @Column(name="last_login_ip")
    private String lastLoginIp;

    /**
     *创建时间
     */
    @Getter
    @Setter
    @Column(name="create_date")
    private java.util.Date createDate;

}
