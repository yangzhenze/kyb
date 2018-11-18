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
@Table(name="commission")
public class Commission implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *一级代理
     */
    @Getter
    @Setter
    @Column(name="one_level_agent")
    private String oneLevelAgent;

    /**
     *二级代理
     */
    @Getter
    @Setter
    @Column(name="two_level_agent")
    private String twoLevelAgent;

    /**
     *加盟费
     */
    @Getter
    @Setter
    @Column(name="initail_free")
    private double initailFree;

}
