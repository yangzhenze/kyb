package com.system.dao;

import com.system.bean.Admin;
import com.zzy.db.helper.IBaseDao;

public interface IAdminDao extends IBaseDao<Admin,Integer> {

    Admin getByAccAndPwd(String account, String password);

    Admin getByAccAndId(String account, Integer id);


}
