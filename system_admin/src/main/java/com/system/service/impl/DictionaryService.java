package com.system.service.impl;

import com.system.bean.Dictionary;
import com.system.dao.IDictionaryDao;
import com.system.service.IDictionaryService;
import com.zzy.db.helper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

/**
 * @author zzy
 * @Date 2018/7/27 上午10:32
 */
@Service
public class DictionaryService implements IDictionaryService {
    @Autowired
    private IDictionaryDao dictionaryDao;

    @Override
    public Page<Map<String, Object>> getPage(int page, int pageSize) {
        return dictionaryDao.findMapPage(page,pageSize);
    }

    @Override
    public boolean save(Dictionary dictionary) {
        return dictionaryDao.save(dictionary);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public boolean update(Dictionary dictionary) {
        if(dictionaryDao.update(dictionary)){
            List<Dictionary> dictionaryList = dictionaryDao.findByParentId(dictionary.getId());
            for(Dictionary d:dictionaryList){
                d.setDicCode(dictionary.getDicCode());
                try{
                    if(!dictionaryDao.update(d)){
                        throw new RuntimeException("更新异常！");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//关键
                    return false;
                }
            }
        }


        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public boolean delete(Integer [] ids) {

        for (Integer id : ids) {
            List<Dictionary> dictionaryList = dictionaryDao.findByParentId(id);
            Integer [] idArray  = new Integer[dictionaryList.size()];

            for(int i = 0; i < idArray.length; i++){
                idArray[i] = dictionaryList.get(i).getId();
            }

            if(idArray.length > 0){
                dictionaryDao.delById(idArray);
            }

            if(!dictionaryDao.delById(id)){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//关键
                return false;
            }
        }
        return true;
    }

    @Override
    public Dictionary getById(Integer id) {
        return dictionaryDao.findById(id);
    }

    @Override
    public List<Dictionary> getByCode(String code) {
        return dictionaryDao.findByCode(code);
    }

    @Override
    public Dictionary getByCode(String code, Integer id) {
        return dictionaryDao.findByCode(code,id);
    }

    @Override
    public Dictionary getByValue(String value, Integer parentId, Integer id) {
        return dictionaryDao.findByValue(value,parentId,id);
    }

    @Override
    public String getName(String code, String value) {
        return dictionaryDao.findName(code,value);
    }
}
