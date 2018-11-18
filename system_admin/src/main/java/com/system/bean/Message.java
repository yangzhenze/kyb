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
@Table(name="message")
public class Message implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *id
     */
    @Getter
    @Setter
    @Column(name="id",isPK = true)
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
