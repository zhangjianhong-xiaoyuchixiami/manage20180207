package org.qydata.service.impl;

import org.qydata.entity.Api;
import org.qydata.entity.ApiType;
import org.qydata.entity.ApiVendor;
import org.qydata.mapper.ApiMapper;
import org.qydata.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/2/28.
 */
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired private ApiMapper apiMapper;

    @Override
    public List<Api> queryApi(Map<String,Object> map) {
        try {
            return apiMapper.queryApi(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ApiType> queryApiType() {
        try {
            return apiMapper.queryApiType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ApiVendor> queryApiVendor() {
        try {
            return apiMapper.queryApiVendor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
