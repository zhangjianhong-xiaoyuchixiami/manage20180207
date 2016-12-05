package org.qydata.service.impl;

import org.qydata.entity.CustomerIp;
import org.qydata.mapper.CustomerIpMapper;
import org.qydata.service.CustomerIpService;
import org.qydata.tools.IpTool;
import org.qydata.tools.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.PrimitiveIterator;

/**
 * Created by jonhn on 2016/11/8.
 */
@Service
public class CustomerIpServiceImpl implements CustomerIpService{
    @Autowired
    private CustomerIpMapper customerIpMapper;
    @Override
    public boolean insertCustomerIp(String beginIp, String endIp, String customerId) throws Exception {

        List<Long> listA = IpTool.spiltIp(beginIp);
        List<Long> listB = IpTool.spiltIp(endIp);
        List<String> listC = IpTool.spiltStr(beginIp);
        List<String> listD = IpTool.spiltStr(endIp);
        CustomerIp customerIp = new CustomerIp();
        boolean flag = false;
        for (int i=0;i<listA.size();i++){
            customerIp.setBeginIp(listA.get(i));
            customerIp.setEndIp(listB.get(i));
            customerIp.setBeginIpRaw(listC.get(i));
            customerIp.setEndIpRaw(listD.get(i));
            customerIp.setCustomerId(Integer.parseInt(customerId));
            flag = customerIpMapper.insertCustomerIp(customerIp);
        }
        return flag;
    }

    @Override
    public PageModel<CustomerIp> findAllIpByCustomerId(Map<String, Object> map) {
        PageModel<CustomerIp> pageModel = new PageModel<CustomerIp>();
        pageModel.setCount(customerIpMapper.getAllCountByCustomerId(map));
        pageModel.setList(customerIpMapper.findAllIpByCustomerId(map));
        return pageModel;
    }

    @Override
    public boolean deleteIpById(Integer id) throws Exception {
        return customerIpMapper.deleteIpById(id);
    }
}
