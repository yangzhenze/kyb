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
@Table(name="sys_log")
public class Log implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    /**
     *系统用户ID
     */
    @Getter
    @Setter
    @Column(name="aid")
    private String aid;

    /**
     *来源 url
     */
    @Getter
    @Setter
    @Column(name="from")
    private String from;

    /**
     *客户端IP
     */
    @Getter
    @Setter
    @Column(name="ip")
    private String ip;

    /**
     *
     */
    @Getter
    @Setter
    @Column(name="url")
    private String url;

    /**
     *记录时间
     */
    @Getter
    @Setter
    @Column(name="date")
    private java.util.Date date;

    /**
     *异常信息
     */
    @Getter
    @Setter
    @Column(name="err_msg")
    private String errMsg;

    /**
     *状态码，0：正常
     */
    @Getter
    @Setter
    @Column(name="err_code")
    private Integer errCode;

    /**
     *controller类名
     */
    @Getter
    @Setter
    @Column(name="class_name")
    private String className;

    /**
     *方法名
     */
    @Getter
    @Setter
    @Column(name="method_name")
    private String methodName;

    /**
     *操作时间
     */
    @Getter
    @Setter
    @Column(name="start_time")
    private java.util.Date startTime;

    /**
     *耗时，毫秒
     */
    @Getter
    @Setter
    @Column(name="spend_time")
    private Long spendTime;

    /**
     *管理员
     */
    @Getter
    @Setter
    @Column(name="admin_name")
    private String adminName;

}
