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
@Table(name="poster")
public class Poster implements Serializable{

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
     *海报
     */
    @Getter
    @Setter
    @Column(name="poster")
    private String poster;

    /**
     *海报类型0.分享1.店铺
     */
    @Getter
    @Setter
    @Column(name="poster_status")
    private String posterStatus;

    /**
     *创建时间
     */
    @Getter
    @Setter
    @Column(name="create_date")
    private java.util.Date createDate;

}
