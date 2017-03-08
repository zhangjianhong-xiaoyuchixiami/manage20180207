package org.qydata.service.impl;

import org.qydata.entity.*;
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

    @Override
    public List<ApiVendor> queryApiVendorByApiTypeId(Integer id) {
        try {
            return apiMapper.queryApiVendorByApiTypeId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CompanyApi> queryApiByCompanyId(Map<String, Object> map) {
        try {
            return apiMapper.queryApiByCompanyId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Company> queryCompany() {
        try {
            return apiMapper.queryCompany();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
