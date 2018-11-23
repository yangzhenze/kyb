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
@Table(name="sys_dictionary")
public class Dictionary implements Serializable{

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
     *
     */
    @Getter
    @Setter
    @Column(name="dic_parent_id")
    private Integer dicParentId;

    /**
     *
     */
    @Getter
    @Setter
    @Column(name="dic_name")
    private String dicName;

    /**
     *
     */
    @Getter
    @Setter
    @Column(name="dic_code")
    private String dicCode;

    /**
     *
     */
    @Getter
    @Setter
    @Column(name="dic_value")
    private String dicValue;

    /**
     *
     */
    @Getter
    @Setter
    @Column(name="create_name")
    private String createName;

    /**
     *
     */
    @Getter
    @Setter
    @Column(name="create_date")
    private java.util.Date createDate;

    /**
     *
     */
    @Getter
    @Setter
    @Column(name="update_name")
    private String updateName;

    /**
     *
     */
    @Getter
    @Setter
    @Column(name="update_date")
    private java.util.Date updateDate;

}
