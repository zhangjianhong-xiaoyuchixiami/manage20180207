package org.qydata.service.impl;

import org.qydata.entity.Company;
import org.qydata.mapper.CompanyMapper;
import org.qydata.service.CompanyService;
import org.qydata.tools.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by jonhn on 2016/12/15.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Company findById(String id) throws Exception {
        return companyMapper.findById(Integer.parseInt(id));
    }

    @Override
    public PageModel<Company> findAllCompany(Map<String, Object> map) throws Exception {
        PageModel<Company> pageModel = new PageModel<>();
        pageModel.setList(companyMapper.findAllCompany(map));
        pageModel.setCount(companyMapper.getAllCountCompany(map));
        return pageModel;
    }
}
