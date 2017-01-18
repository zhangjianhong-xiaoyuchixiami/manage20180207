package org.qydata.service.impl;

import org.qydata.dst.ApiFinance;
import org.qydata.mapper.ApiFinanceMapper;
import org.qydata.service.ApiFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/17.
 */
@Service
public class ApiFinanceServiceImpl implements ApiFinanceService {

    @Autowired private ApiFinanceMapper apiFinanceMapper;

    @Override
    public List<ApiFinance> queryApiOverAllFinance(Map<String, Object> map){
        try {
            return apiFinanceMapper.queryApiOverAllFinance(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ApiFinance> queryApiVendorAndApi(Map<String, Object> map) {
        try {
            return apiFinanceMapper.queryApiVendorAndApi(map);
        } catch (Exception e) {
            e.printStackTrace();
        return null;
        }
    }
}
