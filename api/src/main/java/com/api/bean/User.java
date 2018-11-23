package com.api.bean;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
/**
* @author auto create
* @Date 2018/11/23 18:11:13
*/
@Entity
@Table(name="user")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *用户ID
     */
    @Getter
    @Setter
    /*@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")*/
    @Column(name="id")
    private String id;

    /**
     *唯一码
     */
    @Getter
    @Setter
    @Column(name="user_code")
    private Integer userCode;

    /**
     *帐号
     */
    @Getter
    @Setter
    @Column(name="user_name")
    private String userName;

    /**
     *密码
     */
    @Getter
    @Setter
    @Column(name="pwd")
    private String pwd;

    /**
     *呢称
     */
    @Getter
    @Setter
    @Column(name="nick_name")
    private String nickName;

    /**
     *头像
     */
    @Getter
    @Setter
    @Column(name="icon")
    private String icon;

    /**
     *用户状态0正常1冻结
     */
    @Getter
    @Setter
    @Column(name="user_status")
    private String userStatus;

    /**
     *帐户类型0普通，1代理
     */
    @Getter
    @Setter
    @Column(name="user_type")
    private String userType;

    /**
     *真实姓名
     */
    @Getter
    @Setter
    @Column(name="real_name")
    private String realName;

    /**
     *支付宝帐号
     */
    @Getter
    @Setter
    @Column(name="alipay")
    private String alipay;

    /**
     *手机号
     */
    @Getter
    @Setter
    @Column(name="mobile")
    private String mobile;

    /**
     *身份证
     */
    @Getter
    @Setter
    @Column(name="id_card")
    private String idCard;

    /**
     *总收入
     */
    @Getter
    @Setter
    @Column(name="total_income")
    private java.math.BigDecimal totalIncome;

    /**
     *余额
     */
    @Getter
    @Setter
    @Column(name="balance")
    private java.math.BigDecimal balance;

    /**
     *所属代理
     */
    @Getter
    @Setter
    @Column(name="agent")
    private Integer agent;

    /**
     *注册时间
     */
    @Getter
    @Setter
    @Column(name="create_date")
    private java.util.Date createDate;

}
