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
@Table(name="message_detail")
public class Detail implements Serializable{

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
     *消息状态1已读2.未读
     */
    @Getter
    @Setter
    @Column(name="status")
    private String status;

    /**
     *消息类型0.系统、1.编辑人员
     */
    @Getter
    @Setter
    @Column(name="type")
    private String type;

    /**
     *创建时间
     */
    @Getter
    @Setter
    @Column(name="create_date")
    private java.util.Date createDate;

}
