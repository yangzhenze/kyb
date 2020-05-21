package com.system.dao;

import com.system.bean.Permission;
import com.zzy.db.helper.IBaseDao;

import java.util.List;
import java.util.Map;

public interface IPermissionDao extends IBaseDao<Permission,Integer> {

    int findMaxSort(Integer parentId);

    List<Map<String,Object>> findTreeList(Integer id, Integer roleId);

    List<Permission> findByParentId(Integer id);

    List<Permission> findFunctionByRole(Integer roleId);

    boolean findByPath(String path, Integer parentId, Integer id);

    Permission findBySort(Integer parentId, int sort);
}
