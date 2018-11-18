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
@Table(name="feedback")
public class Feedback implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *id
     */
    @Getter
    @Setter
    @Column(name="id",isPK = true)
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
