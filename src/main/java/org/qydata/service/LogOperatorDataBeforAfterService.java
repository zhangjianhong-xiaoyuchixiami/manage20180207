package org.qydata.service;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.ApiVendorBalance;

/**
 * Created by jonhn on 2017/3/28.
 */
public interface LogOperatorDataBeforAfterService {

    /**
     * 根据Id查询供应商余额记录
     * @param id
     * @return
     */
    @SystemServiceLog(description = "根据Id查询供应商余额记录")
    public ApiVendorBalance queryApiVendorBalanceById(Integer id);


}
