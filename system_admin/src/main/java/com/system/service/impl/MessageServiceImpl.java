package com.system.service.impl;

import com.system.bean.Message;
import com.system.common.util.Page;
import com.system.dao.IMessageDao;
import com.system.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    IMessageDao messageDao;

    @Override
    public boolean add(Message message) {
        message.setCreateDate(new Date());
        return messageDao.save(message);
    }

    @Override
    public boolean delByIds(Integer... ids) {
        return messageDao.delById(ids);
    }

    @Override
    public Message getById(Integer id) {
        return messageDao.findById(id);
    }

    @Override
    public Page<Message> getPage(int page, int pageSize) {
        return messageDao.findPage(page,pageSize);
    }
}
