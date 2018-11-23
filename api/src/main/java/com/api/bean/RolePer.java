package com.api.bean;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
* @author auto create
* @Date 2018/17/23 14:17:35
*/
@Table(name="sys_role_per")
public class RolePer implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *角色id
     */
    @Getter
    @Setter
    @Column(name="role_id")
    private Integer roleId;

    /**
     *根权id
     */
    @Getter
    @Setter
    @Column(name="per_id")
    private Integer perId;

}
