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
@Table(name="user")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *用户ID
     */
    @Getter
    @Setter
    @Column(name="id",isPK = true)
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
