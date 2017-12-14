package org.qydata.mapper.mapper2;

import org.qydata.entity.CompanyApi;
import org.qydata.entity.agency.AgencyBillDetail;
import org.qydata.entity.agency.AgencyCustomer;
import org.qydata.entity.agency.RebateAgency;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/9/14.
 */
public interface AgencySelectMapper {

    /**
     * 查询代理人
     * @return
     */
    public List<RebateAgency> queryAgency();

    /**
     * 根据代理人Id查找返佣规则
     * @param id
     * @return
     */
    public String queryRebateRuleById(Integer id);

    /**
     * 查询代理人账单详情
     * @param map
     * @return
     */
    public List<AgencyBillDetail> queryAgencyBillDetailUserOur(Map<String, Object> map);

    /**
     * 查询代理人账单详情
     * @param map
     * @return
     */
    public List<AgencyBillDetail> queryAgencyBillDetailUserOpposite(Map<String, Object> map);

    /**
     *缓存调用
     * @param map
     * @return
     */
    public List<AgencyBillDetail> queryAgencyBillDetailCache(Map<String, Object> map);

    /**
     * 查询代理的客户列表
     * @param map
     * @return
     */
    public List<AgencyCustomer> queryAgencyCustomer(Map<String, Object> map);

    /**
     * 查询周期
     * @return
     */
    public List<String> queryConsumeCycle();

    /**
     * 查询产品列表
     * @return
     */
    public List<CompanyApi> queryConsumeApiType();

}
