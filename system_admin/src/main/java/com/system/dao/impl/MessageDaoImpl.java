package com.system.dao.impl;

import com.system.bean.Message;
import com.system.common.util.Page;
import com.system.dao.BaseDao;
import com.system.dao.IMessageDao;

import java.io.Serializable;

/**
 * @author zzy
 * @Date 2018/11/19 9:03 PM
 */
public class MessageDaoImpl extends BaseDao<Message> implements IMessageDao {
    @Override
    public boolean save(Message message) {
        return super.insertEntity(message);
    }

    @Override
    public boolean delete(Message message) {
        return false;
    }

    @Override
    public boolean delById(Serializable... ids) {
        return super.deleteById(ids);
    }

    @Override
    public boolean update(Message message) {
        return super.updateEntity(message);
    }

    @Override
    public Message findById(Serializable id) {
        return super.findEntityById(id);
    }

    @Override
    public Page<Message> findPage(int page, int pageSize, Object... args) {
        String sql = "order by create_date desc";
        return super.paginate(page,pageSize,sql,args);
    }
}
