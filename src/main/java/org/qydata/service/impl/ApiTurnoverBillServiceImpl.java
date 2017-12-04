package org.qydata.service.impl;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.dst.ApiTurnoverBill;
import org.qydata.dst.ApiTurnoverBillTrend;
import org.qydata.mapper.ApiTurnoverBillMapper;
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

    @Override
    @SystemServiceLog(description = "查询各产品进出帐单")
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
        List<ApiTurnoverBill> vendorBillList = billMapper.queryApiConsumeVendor(param);
        List<ApiTurnoverBill> customerBillList =  billMapper.queryApiCustomerConsume(param);
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
    @SystemServiceLog(description = "查询消费月份")
    public List<String> queryAllConsumeTime() {
        return billMapper.queryAllConsumeTime();
    }

    @Override
    @SystemServiceLog(description = "查询消费类型")
    public List<ApiTurnoverBill> queryConsumeType() {
        List<ApiTurnoverBill> vendorTypeList = billMapper.queryVendorConsumeType();
        List<ApiTurnoverBill> customerTypeList = billMapper.queryCustomerConsumeType();
        List<ApiTurnoverBill> billTypeList = ApiTurnoverBillUtils.packageBillType(customerTypeList,vendorTypeList);
        return billTypeList;
    }

    @Override
    @SystemServiceLog(description = "查询各产品进出帐单走势")
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
        List<ApiTurnoverBillTrend> vendorTrendList =  billMapper.queryVendorConsumeByTypeId(param);
        List<ApiTurnoverBillTrend> customerTrendList =  billMapper.queryCustomerConsumeByTypeId(param);
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
    @SystemServiceLog(description = "查询各产品进出帐单走势")
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
        List<ApiTurnoverBillTrend> vendorTrendList =  billMapper.queryVendorConsumeByTypeIdData(param);
        List<ApiTurnoverBillTrend> customerTrendList =  billMapper.queryCustomerConsumeByTypeIdData(param);
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
