package org.qydata.service.impl;

import org.apache.commons.collections.map.HashedMap;
import org.qydata.entity.Api;
import org.qydata.entity.Company;
import org.qydata.entity.Customer;
import org.qydata.entity.CustomerApi;
import org.qydata.mapper.CompanyMapper;
import org.qydata.mapper.CustomerApiMapper;
import org.qydata.service.CompanyService;
import org.qydata.tools.IpTool;
import org.qydata.tools.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jonhn on 2016/12/15.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private CustomerApiMapper customerApiMapper;

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

    @Override
    public List<Api> findAllApi(String companyId) throws Exception{
        List<Customer> customerList = companyMapper.findAllCustomerByCompanyId(Integer.parseInt(companyId));
        List customerIdList = new ArrayList();
        if(customerList != null){
            for (int i=0;i<customerList.size();i++){
                customerIdList.add(customerList.get(i).getId());
            }
            Map<String,Object> map = new HashedMap();
            map.put("customerIdList",customerIdList);
            List<Api> apiNotMobileList = companyMapper.findAllApiNotMobile(map);
            List<Api> apiMobileList = companyMapper.findAllApiMobile(map);
            apiNotMobileList.addAll(apiMobileList);
            return apiNotMobileList;
        }
        return null;
    }

    @Override
    public boolean insertCustomerApi(String companyId, String price, String apiId, String enabled)throws Exception {
        try {
            List<Customer> customerList = companyMapper.findAllCustomerByCompanyId(Integer.parseInt(companyId));
            if (customerList != null) {
                for (int i = 0; i < customerList.size(); i++) {
                    CustomerApi customerApi = new CustomerApi();
                    List<CustomerApi> customerApiList = new ArrayList<>();
                    List<String> listPrice = IpTool.spiltStr(price);
                    List<String> listApiId = IpTool.spiltStr(apiId);
                    List<String> listEnabled = IpTool.spiltStr(enabled);
                    for (int j = 0; j < listPrice.size(); j++) {
                        customerApi.setPrice(Integer.parseInt(listPrice.get(j)));
                        customerApi.setCustomerId(customerList.get(i).getId());
                        customerApi.setApiId(Integer.parseInt(listApiId.get(j)));
                        customerApi.setEnabled(Boolean.parseBoolean(listEnabled.get(j)));
                        customerApiList.add(customerApi);
                    }
                    customerApiMapper.insertCustomerApi(customerApiList);
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PageModel<CustomerApi> findAllCustomerApiByCompanyId(Map<String, Object> map) throws Exception{
        Set<Map.Entry<String,Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        Integer companyId = null;
        Integer beginIndex = null;
        Integer lineSize = null;
        while (it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if(me.getKey().equals("companyId")){
                companyId = (Integer) me.getValue();
            }
            if(me.getKey().equals("beginIndex")){
                beginIndex = (Integer) me.getValue();
            }
            if(me.getKey().equals("lineSize")){
                lineSize = (Integer) me.getValue();
            }
        }
        List<Customer> customerList = companyMapper.findAllCustomerByCompanyId(companyId);
        List customerIdList = new ArrayList();
        if(customerList != null) {
            for (int i = 0; i < customerList.size(); i++) {
                customerIdList.add(customerList.get(i).getId());
            }
            Map<String, Object> mapA = new HashedMap();
            mapA.put("beginIndex", beginIndex);
            mapA.put("lineSize", lineSize);
            mapA.put("customerIdList", customerIdList);
            PageModel<CustomerApi> pageModel = new PageModel<CustomerApi>();
            List<CustomerApi> customerNotMobileApiList = companyMapper.findAllByCustomerIdNotMobile(mapA);
            List<CustomerApi> customerMobileApiList = companyMapper.findAllByCustomerIdMobile(mapA);
            customerNotMobileApiList.addAll(customerMobileApiList);
            pageModel.setList(customerNotMobileApiList);
            pageModel.setCount(companyMapper.getAllCountByCustomerId(mapA));
            return pageModel;
        }
        return null;
    }

    @Override
    public boolean updateCustomerApiById(String id,String price,String apiId,String enabled)throws Exception {
        CustomerApi customerApi = new CustomerApi();
        customerApi.setId(Integer.parseInt(id));
        customerApi.setPrice(Integer.parseInt(price.trim().replaceAll(",","")));
        customerApi.setApiId(Integer.parseInt(apiId));
        customerApi.setEnabled(Boolean.parseBoolean(enabled));
        return companyMapper.updateCustomerApiById(customerApi);
    }

    @Override
    public CustomerApi findById(Integer id) throws Exception{
        CustomerApi customerApiNotMobile = companyMapper.findByIdNotMobile(id);
        CustomerApi customerApiMobile = companyMapper.findByIdMobile(id);
        if(customerApiNotMobile != null){
            return customerApiNotMobile;
        }else {
            return customerApiMobile;
        }
    }
}
