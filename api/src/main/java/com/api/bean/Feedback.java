package com.api.bean;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
* @author auto create
* @Date 2018/17/23 14:17:35
*/
@Table(name="feedback")
public class Feedback implements Serializable{

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
     *意见
     */
    @Getter
    @Setter
    @Column(name="feedback")
    private String feedback;

    /**
     *状态0.未读1.已读
     */
    @Getter
    @Setter
    @Column(name="feedback_status")
    private String feedbackStatus;

    /**
     *反馈时间
     */
    @Getter
    @Setter
    @Column(name="create_date")
    private java.util.Date createDate;

}
