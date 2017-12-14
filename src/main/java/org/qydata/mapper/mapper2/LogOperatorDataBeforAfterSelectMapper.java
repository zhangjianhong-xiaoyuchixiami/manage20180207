package org.qydata.mapper.mapper2;

import org.qydata.entity.ApiVendorBalance;

/**
 * Created by jonhn on 2017/3/28.
 */
public interface LogOperatorDataBeforAfterSelectMapper {

    /**
     * 根据Id查询供应商余额记录
     * @param id
     * @return
     */
    public ApiVendorBalance queryApiVendorBalanceById(Integer id);

}
