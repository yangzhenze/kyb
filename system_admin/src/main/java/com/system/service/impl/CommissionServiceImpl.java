package com.system.service.impl;

import com.system.bean.Commission;
import com.system.dao.ICommissionDao;
import com.system.service.ICommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommissionServiceImpl implements ICommissionService {
    @Autowired
    ICommissionDao commissionDao;
    @Override
    public Commission get() {
        return commissionDao.getCommission();
    }

    @Override
    public boolean update(Commission commission) {
        return commissionDao.update(commission);
    }
}
