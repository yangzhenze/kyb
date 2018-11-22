package com.system.service;

import com.system.bean.Commission;

public interface ICommissionService {

    Commission get();

    boolean update(Commission commission);
}
