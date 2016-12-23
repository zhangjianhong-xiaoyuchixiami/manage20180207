package org.qydata.service.impl;

import org.qydata.config.DatabaseContextHolder;
import org.qydata.config.DatabaseType;
import org.qydata.entity.Api;
import org.qydata.entity.CustomerApi;
import org.qydata.mapper.CustomerApiMapper;
import org.qydata.service.CustomerApiService;
import org.qydata.tools.IpTool;
import org.qydata.tools.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
@Service
public class CustomerApiServiceImpl implements CustomerApiService{

    @Autowired
    private CustomerApiMapper customerApiMapper;
    @Override
    public List<Api> findAllApi(Map<String,Object> map) throws Exception{
        List<Api> apiNotMobileList = customerApiMapper.findAllApiNotMobile(map);
        List<Api> apiMobileList = customerApiMapper.findAllApiMobile(map);
        apiNotMobileList.addAll(apiMobileList);
        return apiNotMobileList;
    }

    @Override
    public boolean insertCustomerApi(String price, String customerId, String apiId, String enabled)throws Exception {

        List<CustomerApi> customerApiList = new ArrayList<>();
        List<String> listPrice = IpTool.spiltStr(price);
        List<String> listApiId = IpTool.spiltStr(apiId);
        List<String> listEnabled = IpTool.spiltStr(enabled);
        for (int i=0;i<listPrice.size();i++){
            CustomerApi customerApi = new CustomerApi();
            customerApi.setPrice(Integer.parseInt(listPrice.get(i)));
            customerApi.setCustomerId(Integer.parseInt(customerId));
            customerApi.setApiId(Integer.parseInt(listApiId.get(i)));
            customerApi.setEnabled(Boolean.parseBoolean(listEnabled.get(i)));
            customerApiList.add(customerApi);
        }
        return customerApiMapper.insertCustomerApi(customerApiList);
    }

    @Override
    public PageModel<CustomerApi> findAllByCustomerId(Map<String, Object> map) throws Exception{
        PageModel<CustomerApi> pageModel = new PageModel<CustomerApi>();
        List<CustomerApi> customerNotMobileApiList = customerApiMapper.findAllByCustomerIdNotMobile(map);
        List<CustomerApi> customerMobileApiList = customerApiMapper.findAllByCustomerIdMobile(map);
        customerNotMobileApiList.addAll(customerMobileApiList);
        pageModel.setList(customerNotMobileApiList);
        pageModel.setCount(customerApiMapper.getAllCountByCustomerId(map));
        return pageModel;
    }

    @Override
    public boolean updateCustomerApiById(String id,String price,String apiId,String enabled)throws Exception {
        CustomerApi customerApi = new CustomerApi();
        customerApi.setId(Integer.parseInt(id));
        customerApi.setPrice(Integer.parseInt(price.trim().replaceAll(",","")));
        customerApi.setApiId(Integer.parseInt(apiId));
        customerApi.setEnabled(Boolean.parseBoolean(enabled));
        return customerApiMapper.updateCustomerApiById(customerApi);
    }



    @Override
    public CustomerApi findById(Integer id) throws Exception{
        CustomerApi customerApiNotMobile = customerApiMapper.findByIdNotMobile(id);
        CustomerApi customerApiMobile = customerApiMapper.findByIdMobile(id);
        if(customerApiNotMobile != null){
            return customerApiNotMobile;
        }else {
            return customerApiMobile;
        }
    }

    @Override
    public List<Api> apiList() {
        DatabaseContextHolder.setDatabaseType(DatabaseType.master);
        return customerApiMapper.apiList();
    }

    @Override
    public List<Api> apiList1() {
        DatabaseContextHolder.setDatabaseType(DatabaseType.slave);
        return customerApiMapper.apiList1();
    }
}
