package com.system.service;

import com.system.bean.Message;
import com.system.common.util.Page;

public interface IMessageService {

    boolean add(Message message);

    boolean delByIds(Integer... ids);

    Message getById(Integer id);

    Page<Message> getPage(int page, int pageSize);
}
