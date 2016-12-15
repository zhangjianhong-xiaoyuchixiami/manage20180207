package org.qydata.service;

import org.qydata.entity.Company;
import org.qydata.tools.PageModel;

import java.util.Map;

/**
 * Created by jonhn on 2016/12/15.
 */
public interface CompanyService {


    /**
     * 根据Id查找指定的客户信息
     * @param id
     * @return
     * @throws Exception
     */
    public Company findById(String id)throws Exception;

    /**
     * 查询全部客户并分页显示
     * @param map
     * @return
     * @throws Exception
     */
    public PageModel<Company> findAllCompany(Map<String,Object> map)throws Exception;
}
