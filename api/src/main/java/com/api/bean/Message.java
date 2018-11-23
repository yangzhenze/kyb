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
@Table(name="message")
public class Message implements Serializable{

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
     *消息标题
     */
    @Getter
    @Setter
    @Column(name="title")
    private String title;

    /**
     *消息内容
     */
    @Getter
    @Setter
    @Column(name="content")
    private String content;

    /**
     *发送状态0.未发送1.已发送
     */
    @Getter
    @Setter
    @Column(name="status")
    private String status;

    /**
     *创建时间
     */
    @Getter
    @Setter
    @Column(name="create_date")
    private java.util.Date createDate;

}
