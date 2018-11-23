package com.api.bean;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
* @author auto create
* @Date 2018/17/23 14:17:34
*/
@Table(name="cash_review")
public class Review implements Serializable{

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
     *用户id
     */
    @Getter
    @Setter
    @Column(name="user_id")
    private String userId;

    /**
     *订单号
     */
    @Getter
    @Setter
    @Column(name="order_no")
    private String orderNo;

    /**
     *真实姓名
     */
    @Getter
    @Setter
    @Column(name="real_name")
    private String realName;

    /**
     *绑定手机
     */
    @Getter
    @Setter
    @Column(name="mobile")
    private String mobile;

    /**
     *支付宝帐号
     */
    @Getter
    @Setter
    @Column(name="alipay_account")
    private String alipayAccount;

    /**
     *身份证
     */
    @Getter
    @Setter
    @Column(name="card_id")
    private String cardId;

    /**
     *提现金额
     */
    @Getter
    @Setter
    @Column(name="money")
    private java.math.BigDecima money;

    /**
     *审核状态
     */
    @Getter
    @Setter
    @Column(name="review_status")
    private String reviewStatus;

    /**
     *审核时间
     */
    @Getter
    @Setter
    @Column(name="review_date")
    private java.util.Date reviewDate;

    /**
     *申请时间
     */
    @Getter
    @Setter
    @Column(name="create_date")
    private java.util.Date createDate;

    /**
     *审核人
     */
    @Getter
    @Setter
    @Column(name="review_name")
    private String reviewName;

    /**
     *审核人id
     */
    @Getter
    @Setter
    @Column(name="review_id")
    private String reviewId;

}
