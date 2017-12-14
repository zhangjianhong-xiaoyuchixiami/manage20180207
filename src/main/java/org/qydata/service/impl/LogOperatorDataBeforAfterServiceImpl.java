package org.qydata.service.impl;

import org.qydata.entity.ApiVendorBalance;
import org.qydata.mapper.mapper1.LogOperatorDataBeforAfterMapper;
import org.qydata.mapper.mapper2.LogOperatorDataBeforAfterSelectMapper;
import org.qydata.service.LogOperatorDataBeforAfterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jonhn on 2017/3/28.
 */
@Service
public class LogOperatorDataBeforAfterServiceImpl implements LogOperatorDataBeforAfterService {

    @Autowired
    private LogOperatorDataBeforAfterMapper logOperatorDataBeforAfterMapper;
    @Autowired
    private LogOperatorDataBeforAfterSelectMapper logOperatorDataBeforAfterSelectMapper;

    @Override
    public ApiVendorBalance queryApiVendorBalanceById(Integer id) {
        return logOperatorDataBeforAfterSelectMapper.queryApiVendorBalanceById(id);
    }
}
