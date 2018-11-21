package com.system.service.impl;

import com.system.bean.Permission;
import com.system.dao.IPermissionDao;
import com.system.service.IPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zzy
 * @Date 2018/7/11 下午12:25
 */
@Service
//@CacheConfig(cacheNames = "permission")
public class PermissionServiceImpl implements IPermissionService {
    private static final Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);
    @Autowired
    IPermissionDao permissionDao;

    @Override
    //@CacheEvict(value = "permission",allEntries=true)
    public boolean save(Permission permission) {
        permission.setSort(permissionDao.findMaxSort(permission.getParentId()));
        return permissionDao.save(permission);
    }

    @Override
    //@CacheEvict(value = "permission",allEntries=true)
    public boolean update(Permission permission) {
        return permissionDao.update(permission);
    }

    @Override
    //@CacheEvict(value = "permission",allEntries=true)
    public boolean deleteById(Integer id) {
        List<Permission> childrenPer = permissionDao.findByParentId(id);
        Integer ids [] = new Integer[childrenPer.size()];
        for(int i = 0; i < ids.length; i++){
            ids[i] = childrenPer.get(i).getId();
        }

        if(ids.length > 0){
            if(permissionDao.delById(ids)){
               return permissionDao.delById(id);
            }
            return false;
        }
        return permissionDao.delById(id);
    }

    @Override
    public Permission getById(Integer id) {
        return permissionDao.findById(id);
    }

    @Override
    //@Cacheable(value = "permission", key = "#roleId+'-treelist'")
    public List<Map<String, Object>> getTreeList(Integer roleId) {
        log.info("获取角色权限树形数据");
        return permissionDao.findTreeList(null,roleId);
    }

    @Override
    public List<Permission> getRoleFunction(Integer roleId) {
        return permissionDao.findFunctionByRole(roleId);
    }

    @Override
    public boolean checkPath(String path, Integer parentId, Integer id) {
        return permissionDao.findByPath(path,parentId,id);
    }

    @Override
    @Transactional
    public boolean changeSort(Integer parentId, int sort, String flag) {
        Permission permission = permissionDao.findBySort(parentId,sort);

        if(null != permission){
            Permission newPermission = null;
            if("up".equals(flag)){
                newPermission = permissionDao.findBySort(permission.getParentId(),permission.getSort()-1);
            }else if("down".equals(flag)){
                newPermission = permissionDao.findBySort(permission.getParentId(),permission.getSort()+1);
            }

            if(null != newPermission){
                int tempsort = permission.getSort();
                permission.setSort(newPermission.getSort());
                newPermission.setSort(tempsort);

                if(permissionDao.update(permission)){
                    return permissionDao.update(newPermission);
                }
            }
        }

        return false;
    }

    public static void main(String [] args){
        Integer [] i = new Integer[0];
        System.out.println(i.length);
    }
}
