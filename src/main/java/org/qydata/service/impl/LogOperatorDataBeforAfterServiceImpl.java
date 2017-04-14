package org.qydata.service.impl;

import org.qydata.config.annotation.DataSourceService;
import org.qydata.entity.ApiVendorBalance;
import org.qydata.mapper.LogOperatorDataBeforAfterMapper;
import org.qydata.service.LogOperatorDataBeforAfterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jonhn on 2017/3/28.
 */
@Service
public class LogOperatorDataBeforAfterServiceImpl implements LogOperatorDataBeforAfterService {

    @Autowired private LogOperatorDataBeforAfterMapper logOperatorDataBeforAfterMapper;

    @Override
    @DataSourceService
    public ApiVendorBalance queryApiVendorBalanceById(Integer id) {
        return logOperatorDataBeforAfterMapper.queryApiVendorBalanceById(id);
    }
}
