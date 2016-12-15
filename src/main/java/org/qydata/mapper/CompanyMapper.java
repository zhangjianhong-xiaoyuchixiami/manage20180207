package org.qydata.mapper;

import org.qydata.entity.Company;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/12/15.
 */
public interface CompanyMapper {
    /**
     * 新增客户
     * @param company
     * @return
     * @throws Exception
     */
    public boolean addCompany(Company company)throws Exception;

    /**
     * 根据Id查找指定客户信息
     * @param id
     * @return
     * @throws Exception
     */
    public Company findById(Integer id)throws Exception;

    /**
     * 根据条件查找客户信息
     * @param map
     * @return
     * @throws Exception
     */
    public List<Company> findAllCompany(Map<String,Object> map)throws Exception;

    /**
     * 根据条件统计数据量
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getAllCountCompany(Map<String,Object> map)throws Exception;
}
