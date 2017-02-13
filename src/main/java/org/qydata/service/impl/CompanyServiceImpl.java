package org.qydata.service.impl;

import org.qydata.dst.CustomerCompanyPartner;
import org.qydata.entity.*;
import org.qydata.mapper.CompanyMapper;
import org.qydata.mapper.CustomerApiMapper;
import org.qydata.mapper.CustomerDeptMapper;
import org.qydata.mapper.CustomerMapper;
import org.qydata.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/12/15.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired private CompanyMapper companyMapper;

    @Autowired private CustomerApiMapper customerApiMapper;

    @Autowired  private CustomerDeptMapper customerDeptMapper;

    @Autowired  private CustomerMapper customerMapper;

    @Override
    public List<CustomerCompanyPartner> findAllCompany(Map<String, Object> map) {
        try {
            return companyMapper.findAllCompany(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CustomerCompanyPartner> findAllCompanyByDeptId(Map<String, Object> map) {
        try {
            return companyMapper.findAllCompanyByDeptId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addCompanyCustomer(String companyName,String authId,Integer partnerId,Integer deptId) {
        try {
            Company company = new Company();
            company.setName(companyName.trim());
            company.setPartnerId(partnerId);
            companyMapper.addCompany(company);

            //向客户表中插入数据
            Customer customerB = new Customer();
            customerB.setAuthId(authId.trim() + "_test");
            customerB.setCompanyId(company.getId());
            customerMapper.insertCustomerTest(customerB);

            Customer customerA = new Customer();
            customerA.setAuthId(authId.trim());
            customerA.setCompanyId(company.getId());
            customerMapper.insertCustomer(customerA);

            //向部门客户映射表中插入数据
            CustomerDept customerDeptA = new CustomerDept();
            customerDeptA.setCustomerId(customerA.getId());
            customerDeptA.setDeptId(deptId);
            customerDeptMapper.insertCustomerDept(customerDeptA);

            CustomerDept customerDeptB = new CustomerDept();
            customerDeptB.setCustomerId(customerB.getId());
            customerDeptB.setDeptId(deptId);
            return customerDeptMapper.insertCustomerDept(customerDeptB);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Partner> findAllPartner() {
        try {
            return companyMapper.findAllPartner();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addCustomer(String authId, Integer companyId) {
        try {
            Integer deptId = companyMapper.findDeptIdByCompanyId(companyId);
            //向客户表中插入数据
            Customer customerB = new Customer();
            customerB.setAuthId(authId.trim() + "_test");
            customerB.setCompanyId(companyId);
            customerMapper.insertCustomerTest(customerB);

            Customer customerA = new Customer();
            customerA.setAuthId(authId.trim());
            customerA.setCompanyId(companyId);
            customerMapper.insertCustomer(customerA);
            if (deptId != null){
                //向部门客户映射表中插入数据
                CustomerDept customerDeptA = new CustomerDept();
                customerDeptA.setCustomerId(customerA.getId());
                customerDeptA.setDeptId(deptId);
                customerDeptMapper.insertCustomerDept(customerDeptA);

                CustomerDept customerDeptB = new CustomerDept();
                customerDeptB.setCustomerId(customerB.getId());
                customerDeptB.setDeptId(deptId);
                customerDeptMapper.insertCustomerDept(customerDeptB);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CustomerBalanceModifyReason> findBalanceReason(List<Integer> list) {
        try {
            return companyMapper.findBalanceReason(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateCustomerBalance(Integer customerId, Integer reason, Long amount) {
        try {
            CustomerBalanceLog customerBalanceLog = new CustomerBalanceLog();
            customerBalanceLog.setCustomerId(customerId);
            customerBalanceLog.setReasonId(reason);
            Long balance = companyMapper.findCustomerBalanceByCustomerId(customerId);
            if (reason < 0){
                companyMapper.updateCustomerBalance(customerId, (balance-(amount*100)));
                customerBalanceLog.setAmount((0-amount*100));
                companyMapper.addCustomerBalanceLog(customerBalanceLog);
            }else {
                companyMapper.updateCustomerBalance(customerId, (balance+(amount*100)));
                customerBalanceLog.setAmount(amount*100);
                companyMapper.addCustomerBalanceLog(customerBalanceLog);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


//    @Override
//    public List<Api> findAllApi(String companyId) throws Exception{
//        List<Customer> customerList = companyMapper.findAllCustomerByCompanyId(Integer.parseInt(companyId));
//        List customerIdList = new ArrayList();
//        if(customerList != null){
//            for (int i=0;i<customerList.size();i++){
//                customerIdList.add(customerList.get(i).getId());
//            }
//            Map<String,Object> map = new HashedMap();
//            map.put("customerIdList",customerIdList);
//            map.put("companyId",companyId);
//            List<Api> apiNotMobileList = companyMapper.findAllApiNotMobile(map);
//            List<Api> apiMobileList = companyMapper.findAllApiMobile(map);
//            apiNotMobileList.addAll(apiMobileList);
//            return apiNotMobileList;
//        }
//        return null;
//    }
//
//    @Override
//    public boolean insertCustomerApi(String companyId, String price, String apiId, String enabled)throws Exception {
//        try {
//            List<Customer> customerList = companyMapper.findAllCustomerByCompanyId(Integer.parseInt(companyId));
//            if (customerList != null) {
//                List<CustomerApi> customerApiList = new ArrayList<>();
//                List<String> listPrice = IpTool.spiltStr(price);
//                List<String> listApiId = IpTool.spiltStr(apiId);
//                List<String> listEnabled = IpTool.spiltStr(enabled);
//                List customerIdList = new ArrayList();
//                for(int i=0;i<customerList.size();i++){
//                    customerIdList.add(customerList.get(i).getId());
//                }
//                Map<String,Object> map = new HashedMap();
//                map.put("apiIdList",listApiId);
//                map.put("customerIdList",customerIdList);
//                companyMapper.removeCustomerApiByApiIdAndCustomerId(map);
//                for (int i = 0; i < customerList.size(); i++) {
//                    for (int j = 0; j < listPrice.size(); j++) {
//                        CustomerApi customerApi = new CustomerApi();
//                        customerApi.setPrice(Integer.parseInt(listPrice.get(j)));
//                        customerApi.setCustomerId(customerList.get(i).getId());
//                        customerApi.setApiId(Integer.parseInt(listApiId.get(j)));
//                        customerApi.setEnabled(Boolean.parseBoolean(listEnabled.get(j)));
//                        customerApiList.add(customerApi);
//                    }
//                }
//                customerApiMapper.insertCustomerApi(customerApiList);
//            }
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public PageModel<CustomerApi> findAllCustomerApiByCompanyIdOne(Map<String, Object> map) throws Exception{
//        Set<Map.Entry<String,Object>> set = map.entrySet();
//        Iterator<Map.Entry<String, Object>> it = set.iterator();
//        Integer companyId = null;
//        Integer beginIndex = null;
//        Integer lineSize = null;
//        while (it.hasNext()){
//            Map.Entry<String,Object> me = it.next();
//            if(me.getKey().equals("companyId")){
//                companyId = Integer.parseInt( (String) me.getValue());
//            }
//            if(me.getKey().equals("beginIndex")){
//                beginIndex = (Integer) me.getValue();
//            }
//            if(me.getKey().equals("lineSize")){
//                lineSize = (Integer) me.getValue();
//            }
//        }
//        List<Customer> customerList = companyMapper.findAllCustomerByCompanyId(companyId);
//        List customerIdList = new ArrayList();
//        if(customerList != null) {
//            for (int i = 0; i < customerList.size(); i++) {
//                customerIdList.add(customerList.get(i).getId());
//            }
//            Map<String, Object> mapA = new HashedMap();
//            mapA.put("beginIndex", beginIndex);
//            mapA.put("lineSize", lineSize);
//            mapA.put("customerIdList", customerIdList);
//            PageModel<CustomerApi> pageModel = new PageModel<>();
//            List<CustomerApi> customerNotMobileApiList = companyMapper.findAllByCustomerIdNotMobile(mapA);
//            List<CustomerApi> customerMobileApiList = companyMapper.findAllByCustomerIdMobile(mapA);
//            customerNotMobileApiList.addAll(customerMobileApiList);
//            pageModel.setList(customerNotMobileApiList);
//            pageModel.setCount(companyMapper.getAllCountByCustomerId(mapA));
//            return pageModel;
//        }
//        return null;
//    }
//
//    @Override
//    public CustomerApi findById(String apiId,String companyId) throws Exception {
//        List<Customer> customerList = companyMapper.findAllCustomerByCompanyId(Integer.parseInt(companyId));
//        List customerIdList = new ArrayList();
//        if (customerList != null) {
//            for (int i = 0; i < customerList.size(); i++) {
//                customerIdList.add(customerList.get(i).getId());
//            }
//        }
//        Map<String, Object> map = new HashMap<>();
//        map.put("customerIdList", customerIdList);
//        map.put("apiId", apiId);
//        List<CustomerApi> customerApiNotMobileList = companyMapper.findByIdNotMobile(map);
//        List<CustomerApi> customerApiMobileList = companyMapper.findByIdMobile(map);
//        if (customerApiNotMobileList != null) {
//            return customerApiNotMobileList.get(0);
//        }else {
//            return customerApiMobileList.get(0);
//        }
//    }
//
//    @Override
//    public boolean updateCustomerApiById(String beforApiId,String companyId,String price,String afterApiId,String enabled)throws Exception {
//        List<Customer> customerList = companyMapper.findAllCustomerByCompanyId(Integer.parseInt(companyId));
//        List customerIdList = new ArrayList();
//        Map<String,Object> map = new HashedMap();
//        if(customerList != null) {
//            for (int i = 0; i < customerList.size(); i++) {
//                customerIdList.add(customerList.get(i).getId());
//            }
//            map.put("customerIdList",customerIdList);
//            map.put("beforApiId",beforApiId);
//            map.put("price",Integer.parseInt(price.trim().replaceAll(",", "")));
//            map.put("afterApiId",Integer.parseInt(afterApiId));
//            map.put("enabled",Boolean.parseBoolean(enabled));
//            return companyMapper.updateCustomerApiById(map);
//        }
//        return false;
//    }
//
//    @Override
//    public PageModel<CustomerApiInfo> findAllCustomerApiByCompanyId(Map<String, Object> map) throws Exception {
//        Set<Map.Entry<String,Object>> set = map.entrySet();
//        Iterator<Map.Entry<String, Object>> it = set.iterator();
//        Integer companyId = null;
//        Integer beginIndex = null;
//        Integer lineSize = null;
//        while (it.hasNext()){
//            Map.Entry<String,Object> me = it.next();
//            if(me.getKey().equals("companyId")){
//                companyId = Integer.parseInt( (String) me.getValue());
//            }
//            if(me.getKey().equals("beginIndex")){
//                beginIndex = (Integer) me.getValue();
//            }
//            if(me.getKey().equals("lineSize")){
//                lineSize = (Integer) me.getValue();
//            }
//        }
//        List<Customer> customerList = companyMapper.findAllCustomerByCompanyId(companyId);
//        List customerIdList = new ArrayList();
//        if(customerList != null) {
//            for (int i = 0; i < customerList.size(); i++) {
//                customerIdList.add(customerList.get(i).getId());
//            }
//            Map<String, Object> mapA = new HashedMap();
//            mapA.put("beginIndex", beginIndex);
//            mapA.put("lineSize", lineSize);
//            mapA.put("customerIdList", customerIdList);
//            PageModel<CustomerApiInfo> pageModel = new PageModel<>();
//            pageModel.setList(companyMapper.findAllCustomerApiByCompanyId(mapA));
//            pageModel.setCount(companyMapper.getAllCustomerApiCountByCompanyId(mapA));
//            return pageModel;
//        }
//        return null;
//    }


}
