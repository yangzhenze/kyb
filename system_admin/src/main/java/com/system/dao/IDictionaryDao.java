package com.system.dao;

import com.system.bean.Dictionary;
import com.zzy.db.helper.IBaseDao;
import com.zzy.db.helper.Page;

import java.util.List;
import java.util.Map;

/**
 * @author zzy
 * @Date 2018/7/26 下午4:12
 */
public interface IDictionaryDao extends IBaseDao<Dictionary,Integer> {

    List<Dictionary> findByParentId(Integer id);

    List<Dictionary> findByCode(String code);

    Dictionary findByCode(String code, Integer id);

    Dictionary findByValue(String value, Integer parentId, Integer id);

    Page<Map<String,Object>> findMapPage(int page, int pageSize, Object... args);

    String findName(String code, String value);




}
