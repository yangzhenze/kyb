package com.system.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.system.common.anotation.Table;
import com.system.common.anotation.Column;
import lombok.Getter;
import lombok.Setter;
/**
* @author auto create
* @Date 2018/33/18 13:33:38
*/
@Table(name="proxy_fee_review")
public class FeeReview implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *id
     */
    @Getter
    @Setter
    @Column(name="id",isPK = true)
    private Integer id;

    /**
     *获得金额的用户id
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
     *获得金额
     */
    @Getter
    @Setter
    @Column(name="money")
    private BigDecimal money;

    /**
     *代理级别1一级2二级
     */
    @Getter
    @Setter
    @Column(name="proxy_level")
    private String proxyLevel;

    /**
     *成交用户id
     */
    @Getter
    @Setter
    @Column(name="deal_user_id")
    private String dealUserId;

    /**
     *成交用户绑定手机
     */
    @Getter
    @Setter
    @Column(name="deal_mobile")
    private String dealMobile;

    /**
     *产品id
     */
    @Getter
    @Setter
    @Column(name="product_id")
    private Integer productId;

    /**
     *产品名称
     */
    @Getter
    @Setter
    @Column(name="product_name")
    private String productName;

    /**
     *产品图标
     */
    @Getter
    @Setter
    @Column(name="product_icon")
    private String productIcon;

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
     *备注
     */
    @Getter
    @Setter
    @Column(name="remark")
    private String remark;

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
