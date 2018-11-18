package com.system.bean;

import java.io.Serializable;
import com.system.common.anotation.Table;
import com.system.common.anotation.Column;
import lombok.Getter;
import lombok.Setter;
/**
* @author auto create
* @Date 2018/33/18 13:33:38
*/
@Table(name="product")
public class Product implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *id
     */
    @Getter
    @Setter
    @Column(name="id",isPK = true)
    private Integer id;

    /**
     *产品名称
     */
    @Getter
    @Setter
    @Column(name="product_name")
    private String productName;

    /**
     *产品类型
     */
    @Getter
    @Setter
    @Column(name="product_type")
    private String productType;

    /**
     *额度范围
     */
    @Getter
    @Setter
    @Column(name="money")
    private String money;

    /**
     *申请人数
     */
    @Getter
    @Setter
    @Column(name="application_num")
    private String applicationNum;

    /**
     *月费率
     */
    @Getter
    @Setter
    @Column(name="month_fee_percent")
    private String monthFeePercent;

    /**
     *通过率
     */
    @Getter
    @Setter
    @Column(name="pass_percent")
    private String passPercent;

    /**
     *申请成功标准
     */
    @Getter
    @Setter
    @Column(name="pass_criteria")
    private String passCriteria;

    /**
     *所需类型
     */
    @Getter
    @Setter
    @Column(name="nedd_type")
    private String neddType;

    /**
     *图标
     */
    @Getter
    @Setter
    @Column(name="icon")
    private String icon;

    /**
     *就否独家0否，1是
     */
    @Getter
    @Setter
    @Column(name="is_exclusive")
    private String isExclusive;

    /**
     *申请条件
     */
    @Getter
    @Setter
    @Column(name="application_criteria")
    private String applicationCriteria;

    /**
     *申请详情
     */
    @Getter
    @Setter
    @Column(name="application_desc")
    private String applicationDesc;

    /**
     *下款数量
     */
    @Getter
    @Setter
    @Column(name="deal_num")
    private Integer dealNum;

    /**
     * 第三方连接
     */
    @Getter
    @Setter
    @Column(name="url")
    private String url;

    /**
     *佣金率
     */
    @Getter
    @Setter
    @Column(name="commission_percent")
    private String commissionPercent;

    /**
     *创建时间
     */
    @Getter
    @Setter
    @Column(name="create_date")
    private java.util.Date createDate;

}
