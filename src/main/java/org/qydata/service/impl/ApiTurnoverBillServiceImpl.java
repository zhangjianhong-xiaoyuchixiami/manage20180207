package org.qydata.service.impl;

import org.qydata.dst.ApiTurnoverBill;
import org.qydata.dst.ApiTurnoverBillTrend;
import org.qydata.mapper.mapper1.ApiTurnoverBillMapper;
import org.qydata.mapper.mapper2.ApiTurnoverBillSelectMapper;
import org.qydata.service.ApiTurnoverBillService;
import org.qydata.tools.RegexUtil;
import org.qydata.tools.finance.ApiTurnoverBillUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jonhn on 2017/9/5.
 */
@Service
public class ApiTurnoverBillServiceImpl implements ApiTurnoverBillService {

    @Autowired
    private ApiTurnoverBillMapper billMapper;
    @Autowired
    private ApiTurnoverBillSelectMapper billSelectMapper;

    @Override
    public Map<String, Object> queryApiTurnoverBill(Map<String, Object> map) {

        Map<String,Object> param = new HashMap<>();
        if (map != null){
            for (Map.Entry<String, Object> me : map.entrySet()) {
                if (me.getKey().equals("cyc")) {
                    param.put("cyc",me.getValue());
                }
                if (me.getKey().equals("tid")) {
                    String [] tid = (String[]) me.getValue();
                    List<String> typeIdList = new ArrayList<>();
                    List<String> stidList = new ArrayList<>();
                    if (tid != null){
                        for (String s : tid) {
                            if (!RegexUtil.isPoint(s)){
                                String [] type_stid = s.split("-");
                                typeIdList.add(type_stid[0]);
                                stidList.add(type_stid[1]);
                            }else {
                                typeIdList.add(s);
                            }
                        }
                    }
                    param.put("typeIdList",typeIdList);
                    param.put("stidList",stidList);
                }
            }
        }
        List<ApiTurnoverBill> vendorBillList = billSelectMapper.queryApiConsumeVendor(param);
        List<ApiTurnoverBill> customerBillList =  billSelectMapper.queryApiCustomerConsume(param);
        List <ApiTurnoverBill> billList = ApiTurnoverBillUtils.packageBill(customerBillList,vendorBillList);
        Double vendorTot = 0.0;
        Double customerTot = 0.0;
        Double profitTot = 0.0;
        if (billList != null){
            for (ApiTurnoverBill bill : billList) {
                if (bill.getVendorConsume() != null){
                    vendorTot += bill.getVendorConsume();
                }
                if (bill.getCustomerConsume() != null){
                    customerTot += bill.getCustomerConsume();
                }
                if (bill.getProfit() != null){
                    profitTot += bill.getProfit();
                }
            }
        }
        Map<String,Object> resu = new HashMap<>();
        resu.put("billList",billList);
        resu.put("vendorTot",vendorTot);
        resu.put("customerTot",customerTot);
        resu.put("profitTot",profitTot);
        return resu;
    }

    @Override
    public List<String> queryAllConsumeTime() {
        return billSelectMapper.queryAllConsumeTime();
    }

    @Override
    public List<ApiTurnoverBill> queryConsumeType() {
        List<ApiTurnoverBill> vendorTypeList = billSelectMapper.queryVendorConsumeType();
        List<ApiTurnoverBill> customerTypeList = billSelectMapper.queryCustomerConsumeType();
        List<ApiTurnoverBill> billTypeList = ApiTurnoverBillUtils.packageBillType(customerTypeList,vendorTypeList);
        return billTypeList;
    }

    @Override
    public Map<String, Object> queryApiTurnoverBillTrend(Map<String, Object> map) {
        Map<String,Object> param = new HashMap<>();
        if (map != null){
            for (Map.Entry<String,Object> me : map.entrySet()) {
                if (me.getKey().equals("tid")){
                    String tid = (String) me.getValue();
                    if (tid.contains("-")){
                        String tids [] = tid.split("-");
                        param.put("apiTypeId",tids[0]);
                        param.put("stid",tids[1]);
                    }
                }
                if (me.getKey().equals("cyc")){
                    param.put("cyc",me.getValue());
                }
            }
        }
        List<ApiTurnoverBillTrend> vendorTrendList =  billSelectMapper.queryVendorConsumeByTypeId(param);
        List<ApiTurnoverBillTrend> customerTrendList =  billSelectMapper.queryCustomerConsumeByTypeId(param);
        if (vendorTrendList != null){
            for (ApiTurnoverBillTrend trend : vendorTrendList) {
                if (trend.getCost() !=null){
                    trend.setCost(trend.getCost()/100.0);
                }
                if (trend.getConsume() !=null){
                    trend.setConsume(trend.getConsume()/100.0);
                }
            }
        }
        if (customerTrendList != null){
            for (ApiTurnoverBillTrend trend : customerTrendList) {
                if (trend.getCost() !=null){
                    trend.setCost(trend.getCost()/100.0);
                }
                if (trend.getConsume() !=null){
                    trend.setConsume(trend.getConsume()/100.0);
                }
            }
        }
        Map<String,Object> resu = new HashMap<>();
        resu.put("vendorTrendList",vendorTrendList);
        resu.put("customerTrendList",customerTrendList);
        return resu;
    }

    @Override
    public Map<String, Object> queryApiTurnoverBillTrendData(Map<String, Object> map) {
        Map<String,Object> param = new HashMap<>();
        if (map != null){
            for (Map.Entry<String,Object> me : map.entrySet()) {
                if (me.getKey().equals("tid")){
                    String tid = (String) me.getValue();
                    if (tid.contains("-")){
                        String tids [] = tid.split("-");
                        param.put("apiTypeId",tids[0]);
                        param.put("stid",tids[1]);
                    }
                }
                if (me.getKey().equals("cyc")){
                    param.put("cyc",me.getValue());
                }
            }
        }
        List<ApiTurnoverBillTrend> vendorTrendList =  billSelectMapper.queryVendorConsumeByTypeIdData(param);
        List<ApiTurnoverBillTrend> customerTrendList =  billSelectMapper.queryCustomerConsumeByTypeIdData(param);
        Map<String,Integer> vendorMap = new HashMap<>();
        if (vendorTrendList != null){
            for (ApiTurnoverBillTrend trend : vendorTrendList) {
                vendorMap.put(trend.getVendorPartnerName(),trend.getAmount());
            }
        }
        Map<String,Integer> customerMap = new HashMap<>();
        if (customerTrendList != null){
            for (ApiTurnoverBillTrend trend : customerTrendList) {
                customerMap.put(trend.getCompanyPartnerName(),trend.getAmount());
            }
        }
        Map<String,Object> resu = new HashMap<>();
        resu.put("vendorMap",vendorMap);
        resu.put("customerMap",customerMap);
        return resu;
    }

}
