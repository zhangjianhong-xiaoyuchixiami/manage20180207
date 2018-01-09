package org.qydata.service.impl;

import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.qydata.dst.*;
import org.qydata.dst.customer.CustomerConsume;
import org.qydata.dst.customer.CustomerCurrDayConsume;
import org.qydata.dst.customer.CustomerCurrDayConsumeDetail;
import org.qydata.entity.*;
import org.qydata.mapper.mapper1.CustomerFinanceMapper;
import org.qydata.mapper.mapper2.CustomerFinanceSelectMapper;
import org.qydata.service.CustomerFinanceService;
import org.qydata.tools.CalendarTools;
import org.qydata.tools.CustomerCurrendDayTotalAmountUtils;
import org.qydata.tools.DateUtils;
import org.qydata.tools.JsonUtils;
import org.qydata.tools.chartdate.ChartCalendarUtil;
import org.qydata.tools.date.CalendarUtil;
import org.qydata.tools.date.CalendarUtil_2;
import org.qydata.tools.date.CalendarUtil_3;
import org.qydata.tools.https.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jonhn on 2017/1/8.
 */
@Service
public class CustomerFinanceServiceImpl implements CustomerFinanceService {

    @Autowired
    private CustomerFinanceMapper customerFinanceMapper;

    @Autowired
    private CustomerFinanceSelectMapper customerFinanceSelectMapper;

//    @Autowired
//    private RedisUtils redisUtils;

    @Autowired
    private CustomerCurrendDayTotalAmountUtils customerCurrendDayTotalAmountUtils;


    @Override
    public Map<String,Object> queryCompanyCustomerOverAllFinance(Map<String, Object> map)throws Exception{
        Map<String,Object> param = new HashMap<>();
        Map<String,Object> typeParam = new HashMap<>();
        Map<String,Object> mapTotleParam = new HashMap<>();
        Map<String,Object> mapEmailParam = new HashMap<>();
        Map<String,Object> mapExcelParam = new HashMap<>();
        Set<Map.Entry<String,Object>> set = map.entrySet();
        Iterator<Map.Entry<String,Object>> iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry<String,Object> me = iterator.next();
            if("content".equals(me.getKey())){
                param.put("content",me.getValue());
            }
            if("partnerId".equals(me.getKey())){
                param.put("partnerId",me.getValue());
            }
            if("pid".equals(me.getKey())){
                if ((Integer)me.getValue() == -100 || "-100".equals(me.getValue())){
                    param.put("nullPid",me.getValue());
                }else {
                    param.put("pid", me.getValue());
                }
            }
            if("beginDate".equals(me.getKey())){
                typeParam.put("beginDate",me.getValue());
                mapTotleParam.put("beginDate",me.getValue());
            }
            if("endDate".equals(me.getKey())){
                typeParam.put("endDate",me.getValue());
                mapTotleParam.put("endDate",me.getValue());
            }
            if("statusList".equals(me.getKey())){
                param.put("statusList",me.getValue());
            }
            if("deptIdList".equals(me.getKey())){
                param.put("deptIdList",me.getValue());
            }
        }
        mapExcelParam.put("years",CalendarTools.getYearMonthCount(1));
        mapExcelParam.put("months",CalendarTools.getMonthCount(1));
        typeParam.put("currDayTime",CalendarTools.getCurrentDate()+ " " + "00:00:00");
        mapTotleParam.put("currDayTime",CalendarTools.getCurrentDate()+ " " + "00:00:00");
        mapTotleParam.put("currMonthTime",CalendarTools.getCurrentMonthFirstDay() + " " + "00:00:00");
        //财务账单客户列表
        List<CustomerFinance> list = customerFinanceSelectMapper.queryCompanyCustomer(param);

        if (list == null){
            return null;
        }
        //各类型
        List<CustomerFinance> typeConsumeList = customerFinanceSelectMapper.queryCustomerApiTypeConsume(typeParam);
        //客户当天消费
        List<CustomerFinance> currDayConsList = customerFinanceSelectMapper.queryCustomerCurrDayTotle(mapTotleParam);
        //客户充值（至昨天）
        List<CustomerFinance> chargeList = customerFinanceSelectMapper.queryCustomerChargeTotle(mapTotleParam);
        //客户充值（当天）
        List<CustomerFinance> currDayChargeList = customerFinanceSelectMapper.queryCustomerChargeCurrDay(mapTotleParam);
        //客户消费（至昨天）
        List<CustomerFinance> consumeList = customerFinanceSelectMapper.queryCustomerConsumeTotle(mapTotleParam);
        //客户本月消费（至昨天）
        List<CustomerFinance> currMonthConsList = customerFinanceSelectMapper.queryCustomerCurrMonthTotle(mapTotleParam);
        //查询客户邮箱
        List<CustomerCompanyEmail> customerCompanyEmailList = customerFinanceSelectMapper.queryCustomerEmail(mapEmailParam);
        //查询客户上月账单
        List<CustomerConsumeExcel> customerConsumeExcelList = customerFinanceSelectMapper.queryCustomerAccountExcel(mapExcelParam);
        //上周充值
        List<CustomerConsume> lastWeekChargeList = customerFinanceSelectMapper.queryCustomerLastWeekCharge();
        //上周消费
        List<CustomerConsume> lastWeekConsumeList = customerFinanceSelectMapper.queryCustomerLastWeekConsume();
        //上月充值
        List<CustomerConsume> lastMonthChargeList = customerFinanceSelectMapper.queryCustomerLastMonthCharge();
        //上月消费
        List<CustomerConsume> lastMonthConsumeList = customerFinanceSelectMapper.queryCustomerLastMonthConsume(CalendarUtil_2.getCurrentLastMonth());
        //昨日消费
        List<CustomerConsume> yesterdayConsumeList = customerFinanceSelectMapper.queryCustomerYesterdayConsume(CalendarUtil_3.getCurrentPreviousTime());
        // 历史总消费金额(至上月)
        List<CustomerConsume> historyConsumeList = customerFinanceSelectMapper.queryHistoryTotalAmount();

        double lastWeekChargeTot = 0.0;
        double lastWeekConsumeTot = 0.0;
        double lastMonthChargeTot = 0.0;
        double lastMonthConsumeTot = 0.0;
        double totleChargeAmount = 0.0;
        double totleConsumeAmount = 0.0;
        double currMonthTotleConsumeAmount = 0.0;
        double currDayTotleConsumeAmount = 0.0;
        double kaolaTheoryBalance = 0.0;
        //   List<CustomerFinance> customerFinanceList = new ArrayList<>();
        Iterator<CustomerFinance> it = list.iterator();
        while (it.hasNext()) {

            CustomerFinance customerFinance = it.next();

            customerFinance.setBalance(customerFinance.getBalance()/100.0);
            if (customerFinance.getFloor() != null && customerFinance.getFloor() != 0){
                customerFinance.setFloor(-customerFinance.getFloor()/100.0);
            }
            customerFinance.setSurplusFloor(((customerFinance.getFloor()) + customerFinance.getBalance()));
            /*封装各类型消费*/
            if (typeConsumeList != null) {
                for (CustomerFinance finance : typeConsumeList) {
                    List<CompanyApi> companyApiList = finance.getCompanyApiList();
                    if (customerFinance.getId() == finance.getId() || customerFinance.getId().equals(finance.getId())) {
                        customerFinance.setCompanyApiList(companyApiList);
                    }
                }
            }
            //上周充值
            if (lastWeekChargeList != null){
                for (CustomerConsume consume : lastWeekChargeList) {
                    if (customerFinance.getId() == consume.getCustomerId()
                            || customerFinance.getId().equals(consume.getCustomerId())){
                        if (consume.getLastWeekCharge() != null){
                            customerFinance.setLastWeekCharge(consume.getLastWeekCharge()/100.0);
                        }
                    }
                }
            }
            //上周消费
            if (lastWeekConsumeList != null){
                for (CustomerConsume consume : lastWeekConsumeList) {
                    if (customerFinance.getId() == consume.getCustomerId()
                            || customerFinance.getId().equals(consume.getCustomerId())){
                        if (consume.getLastWeekConsume() != null){
                            customerFinance.setLastWeekConsume(-consume.getLastWeekConsume()/100.0);
                        }
                    }
                }
            }
            //上月充值
            if (lastMonthChargeList != null){
                for (CustomerConsume consume : lastMonthChargeList) {
                    if (customerFinance.getId() == consume.getCustomerId()
                            || customerFinance.getId().equals(consume.getCustomerId())){
                        if (consume.getLastMonthCharge() != null){
                            customerFinance.setLastMonthCharge(consume.getLastMonthCharge()/100.0);
                        }
                    }
                }
            }
            //上月消费
            if (lastMonthConsumeList != null){
                for (CustomerConsume consume : lastMonthConsumeList) {
                    if (customerFinance.getId() == consume.getCustomerId()
                            || customerFinance.getId().equals(consume.getCustomerId())){
                        if (consume.getLastMonthConsume() != null){
                            customerFinance.setLastMonthConsume(consume.getLastMonthConsume()/100.0);
                        }
                    }
                }
            }

            //昨天消费
            if (yesterdayConsumeList != null){
                for (CustomerConsume consume : yesterdayConsumeList) {
                    if (customerFinance.getId() == consume.getCustomerId()
                            || customerFinance.getId().equals(consume.getCustomerId())){
                        if (consume.getYesterdayConsume() != null){
                            customerFinance.setYesterdayConsume(-consume.getYesterdayConsume()/100.0);
                        }
                    }
                }
            }

            //历史消费金额至上月
            if(historyConsumeList != null){
                for (CustomerConsume consume : historyConsumeList) {
                    if (customerFinance.getId() == consume.getCustomerId()
                            || customerFinance.getId().equals(consume.getCustomerId())) {
                        if (consume.getHistoryConsume() != null) {
                            customerFinance.setHistoryTotalAmount(consume.getHistoryConsume() / 100.0);
                        }
                    }
                }
            }

                /*封装当天消费总额*/
            if (currDayConsList != null){
                for (CustomerFinance finance : currDayConsList) {
                    if (customerFinance.getId() == finance.getId()
                            || customerFinance.getId().equals(finance.getId())){
                        if (finance.getCurrDayAmount() != null){
                            customerFinance.setCurrDayAmount(-finance.getCurrDayAmount()/100.0);
                        }
                    }
                }
            }

                /*封装当月消费总额（至昨天）*/
            if (currMonthConsList != null){
                for (CustomerFinance finance : currMonthConsList) {
                    if (customerFinance.getId() == finance.getId()
                            ||customerFinance.getId().equals(finance.getId())){
                        if (finance.getCurrMonthAmount() != null){
                            customerFinance.setCurrMonthAmount(-finance.getCurrMonthAmount()/100.0);
                        }
                    }
                }
            }

                 /*封装当月消费总额（昨天 + 今天）*/
            if (customerFinance.getCurrMonthAmount() != null){
                if (customerFinance.getCurrDayAmount() != null) {
                    customerFinance.setCurrMonthAmount(customerFinance.getCurrMonthAmount() + customerFinance.getCurrDayAmount());
                }
            }else {
                if (customerFinance.getCurrDayAmount() != null) {
                    customerFinance.setCurrMonthAmount(customerFinance.getCurrDayAmount());
                }
            }

                /*封装充值总额（至昨天）*/
            if (chargeList != null){
                for (CustomerFinance finance : chargeList) {
                    if (customerFinance.getId() == finance.getId() ||customerFinance.getId().equals(finance.getId())){
                        if (finance.getChargeTotleAmount() != null){
                            customerFinance.setChargeTotleAmount(finance.getChargeTotleAmount()/100.0);
                        }
                    }
                }
            }

                 /*封装充值总额（今天）*/
            if (currDayChargeList != null){
                for (CustomerFinance finance : currDayChargeList) {
                    if (customerFinance.getId() == finance.getId() || customerFinance.getId().equals(finance.getId())){
                        if (finance.getCurrDayChargeAmount() != null){
                            customerFinance.setCurrDayChargeAmount(finance.getCurrDayChargeAmount()/100.0);
                        }
                    }
                }
            }

                 /*封装充值总额（昨天 + 今天）*/
            if (customerFinance.getChargeTotleAmount() != null){
                if (customerFinance.getCurrDayChargeAmount() != null) {
                    customerFinance.setChargeTotleAmount(customerFinance.getChargeTotleAmount() + customerFinance.getCurrDayChargeAmount());
                }else {
                    customerFinance.setChargeTotleAmount(customerFinance.getChargeTotleAmount());
                }
            }else {
                if (customerFinance.getCurrDayChargeAmount() != null) {
                    customerFinance.setChargeTotleAmount(customerFinance.getCurrDayChargeAmount());
                }
            }

            //封装系统余额(充值总额 - 消费总额)
            //历史总充值
            if (customerFinance.getChargeTotleAmount() != null){
                if (customerFinance.getHistoryTotalAmount() != null){
                    if (customerFinance.getCurrMonthAmount() != null){
                        customerFinance.setSystemBalance(customerFinance.getChargeTotleAmount() - (customerFinance.getHistoryTotalAmount() + customerFinance.getCurrMonthAmount()));
                    }else{
                        customerFinance.setSystemBalance(customerFinance.getChargeTotleAmount() - (customerFinance.getHistoryTotalAmount()));
                    }
                }else{
                    //本月之前为空 判断当月是否为空
                    if (customerFinance.getCurrMonthAmount() != null){
                        //当月不为空
                        customerFinance.setSystemBalance(customerFinance.getChargeTotleAmount() - ( customerFinance.getCurrMonthAmount()));
                    }else{
                        //当月消费为空
                        customerFinance.setSystemBalance(customerFinance.getChargeTotleAmount());
                    }
                }
            }else{
                //历史总充值为空
                if (customerFinance.getHistoryTotalAmount() != null){
                    if (customerFinance.getCurrMonthAmount() != null){
                        customerFinance.setSystemBalance( - (customerFinance.getHistoryTotalAmount() + customerFinance.getCurrMonthAmount()));
                    }else{
                        //当月消费为空
                        customerFinance.setSystemBalance( - (customerFinance.getHistoryTotalAmount()));
                    }
                }else{
                    //本月之前为空 判断当月是否为空
                    if (customerFinance.getCurrMonthAmount() != null){
                        //当月不为空
                        customerFinance.setSystemBalance( - (customerFinance.getCurrMonthAmount()));
                    }else{
                        //当月消费为空
                        customerFinance.setSystemBalance(0.0);
                    }
                }
            }

                /*封装消费总额（至昨天）*/
            if (consumeList != null){
                for (CustomerFinance finance : consumeList) {
                    if (customerFinance.getId() == finance.getId() || customerFinance.getId().equals(finance.getId())){
                        if (finance.getConsumeTotleAmount() != null){
                            customerFinance.setConsumeTotleAmount(-finance.getConsumeTotleAmount()/100.0);
                        }
                    }
                }
            }

                 /*封装消费总额（至昨天 + 今天）*/
            if (map.get("endDate") == null || CalendarUtil.getCurrTimeAfterDay().equals(map.get("endDate")))
            {
                if (customerFinance.getConsumeTotleAmount() != null){
                    if (customerFinance.getCurrDayAmount() != null){
                        customerFinance.setConsumeTotleAmount(customerFinance.getConsumeTotleAmount() + customerFinance.getCurrDayAmount());
                    }
                }else {
                    if (customerFinance.getCurrDayAmount() != null){
                        customerFinance.setConsumeTotleAmount(customerFinance.getCurrDayAmount());
                    }
                }
            }

                /*封装邮箱*/
            if (customerCompanyEmailList != null){
                for (int i = 0; i < customerCompanyEmailList.size() ; i++) {
                    CustomerCompanyEmail customerCompanyEmail = customerCompanyEmailList.get(i);
                    if (customerCompanyEmail.getEmail() != null && customerCompanyEmail.getEmail().length() > 0){
                        if (customerFinance.getCompanyId() == customerCompanyEmail.getCompanyId() || customerFinance.getCompanyId().equals(customerCompanyEmail.getCompanyId())){
                            customerFinance.setEmail(customerCompanyEmail.getEmail());
                        }
                    }
                }
            }

                /*封装账单*/
            if(customerConsumeExcelList != null){
                for (int i = 0; i < customerConsumeExcelList.size(); i++) {
                    CustomerConsumeExcel customerConsumeExcel = customerConsumeExcelList.get(i);
                    if (customerFinance.getId() == customerConsumeExcel.getCustomerId() || customerFinance.getId().equals(customerConsumeExcel.getCustomerId())){
                        customerFinance.setConsuTime(customerConsumeExcel.getConsuTime());
                    }
                }
            }
            if (customerFinance.getId() == 81 || "81".equals(customerFinance.getId())
                    || customerFinance.getId() == 154 || "154".equals(customerFinance.getId())){
                kaolaTheoryBalance += customerFinance.getSystemBalance();
            }

            if (customerFinance.getLastWeekCharge() != null) {
                lastWeekChargeTot += customerFinance.getLastWeekCharge();
            }
            if (customerFinance.getLastWeekConsume() != null) {
                lastWeekConsumeTot += customerFinance.getLastWeekConsume();
            }
            if (customerFinance.getLastMonthCharge() != null) {
                lastMonthChargeTot += customerFinance.getLastMonthCharge();
            }
            if (customerFinance.getLastMonthConsume() != null) {
                lastMonthConsumeTot += customerFinance.getLastMonthConsume();
            }
            if (customerFinance.getChargeTotleAmount() != null) {
                totleChargeAmount += customerFinance.getChargeTotleAmount();
            }
            if (customerFinance.getConsumeTotleAmount() != null) {
                totleConsumeAmount += customerFinance.getConsumeTotleAmount();
            }
            if (customerFinance.getCurrMonthAmount() != null) {
                currMonthTotleConsumeAmount += customerFinance.getCurrMonthAmount();
            }
            if (customerFinance.getCurrDayAmount() != null) {
                currDayTotleConsumeAmount += customerFinance.getCurrDayAmount();
            }

            //   customerFinanceList.add(customerFinance);
        }
        if(list != null){
            for (CustomerFinance finance : list) {
                if (finance.getId() == 81 || "81".equals(finance.getId())
                        || finance.getId() == 154 || "154".equals(finance.getId())){
                    finance.setSystemBalance(kaolaTheoryBalance);
                }
            }
        }

        Map<String,Object> mapTran = new HashMap<>();
        mapTran.put("lastWeekChargeTot",lastWeekChargeTot);
        mapTran.put("lastWeekConsumeTot",lastWeekConsumeTot);
        mapTran.put("lastMonthChargeTot",lastMonthChargeTot);
        mapTran.put("lastMonthConsumeTot",lastMonthConsumeTot);
        mapTran.put("totleChargeAmount",totleChargeAmount);
        mapTran.put("totleConsumeAmount",totleConsumeAmount);
        mapTran.put("currMonthTotleConsumeAmount",currMonthTotleConsumeAmount);
        mapTran.put("currDayTotleConsumeAmount",currDayTotleConsumeAmount);
        mapTran.put("customerFinanceList",list);
        return mapTran;
    }

    /**
     * 客户当天消费情况
     * @param map
     * @return
     */
    @Override
    public List<CustomerCurrDayConsume> queryCustomerCurrDayApiTypeConsume(Map<String, Object> map) {
//        List<CustomerCurrDayConsumeUtil> consumeList = customerFinanceSelectMapper.queryCustomerCurrDayApiTypeConsume(map);

        //获取当前日期
        String currentDate = DateUtils.formatCurrentDate() + "";
        //获取客户id
        String customerId = String.valueOf (map.get("customerId"));
        //调用接口获得客户请求的服务和对应的次数
        String s = HttpClientUtil.httpRequest(customerId, currentDate);
        //去掉接口返回值为null,""," ","{}","null","NULL"
        if (StringUtils.isBlank(s) || s.replace(" ","").length() == 2 || s.equalsIgnoreCase("null")){
            return null;
        }
        try {
            //非json格式字符串会报错
            JSONObject jsonObject = JSONObject.parseObject(s);
        } catch (Exception e) {
            return null;
        }
//        System.out.println(s);
        List<CustomerCurrDayConsume> consumeList = new ArrayList<CustomerCurrDayConsume>();
        //将json转换成map
        Map<String, String> jsonMap = JsonUtils.jsonToMap(s);
        Set<Map.Entry<String, String>> entries = jsonMap.entrySet();
        //定义记录该客户的消费金额
        Double totalAmount = 0.0;
        for (Map.Entry<String, String> entry : entries) {
            //定义记录单项服务的消费金额
            Double amount = 0.0;
            String key = entry.getKey();
            String value = entry.getValue();

//            System.out.println(value);

            String[] split = key.split("-");
            Integer apiTypeId = Integer.valueOf(split[0]);
            Integer subTypeId = Integer.valueOf(split[1]);
            Integer custId = Integer.valueOf(customerId);
//            System.out.println(apiTypeId);
//            System.out.println(subTypeId);

            //根据customerId、apiTypeId、subTypeId查询请求服务的compnyApi对象
            CustomerCurrDayConsume customerCurrDayConsume = customerCurrendDayTotalAmountUtils.getPriceByType(custId, apiTypeId, subTypeId);
            if(customerCurrDayConsume == null){
                return null;
            }
            Double price = customerCurrDayConsume.getPrice();
            //计算此服务总消费金额
            int count = Integer.valueOf(value);
            amount = count * price;
//            System.out.println("单项服务费用"+amount);
            //封装客户此服务当天消费金额
            customerCurrDayConsume.setSumAmount(amount);
            //计算客户所有服务消费金额
            totalAmount = totalAmount + amount;
//            System.out.println("所有服务费用"+totalAmount);
            //封装客户当天服务消费次数
            customerCurrDayConsume.setCountSuccess(count);
            customerCurrDayConsume.setApiTypeId(apiTypeId);
            customerCurrDayConsume.setStid(subTypeId);
            customerCurrDayConsume.setCustomerId(custId);
            consumeList.add(customerCurrDayConsume);
        }

        if (consumeList == null) {
            return null;
        }
        for (CustomerCurrDayConsume consume : consumeList) {
            if (consume != null){
                if (consume.getSubTypeName() != null){
                    consume.setApiTypeName(consume.getApiTypeName() + "--" + consume.getSubTypeName());
                }
                if (consume.getSumAmount() != null){
                    consume.setSumAmount(consume.getSumAmount()/100.0);
                }
                if (consume.getPrice() != null){
                    consume.setPrice(consume.getPrice()/100.0);
                }
            }
        }
        return consumeList;
    }

    /**
     * 查询客户产品价格
     * @param customerId
     * @param apiTypeId
     * @param subTypeId
     * @return
     */
   /* @Override
    public CustomerCurrDayConsume getPriceByType(Integer customerId, Integer apiTypeId, Integer subTypeId) {
        Map<String,Integer>typeMap = new HashMap<String,Integer>();
        typeMap.put("customerId", customerId);
        typeMap.put("apiTypeId", apiTypeId);
        typeMap.put("subTypeId", subTypeId);
        CustomerCurrDayConsume customerCurrDayConsume = customerFinanceSelectMapper.getPriceByType(typeMap);
        return customerCurrDayConsume;
    }*/

    /**
     *查询客户当天消费金额
     * @param //customerId
     * @param //currentDate
     * @return
     */
   /* public Double getCurrentDayTotalAmount(String customerId, String currentDate){

        //调用接口获得客户请求的服务和对应的扣费次数
        String s = HttpClientUtil.httpRequest(customerId, currentDate);
        System.out.println(s);
        //去掉接口返回值为null,""," ","{}","null","NULL"
        if (StringUtils.isNotBlank(s) && s.replace(" ","").length() != 2 && !s.equalsIgnoreCase("null")){

            try {
                //非json格式字符串会报错
                JSONObject jsonObject = JSONObject.parseObject(s);
            } catch (Exception e) {
                return null;
            }
            System.out.println(s);
            //定义记录该客户的消费金额
            Double totalAmount = 0.0;
            //将json转换成map
            Map<String, String> jsonMap = JsonUtils.jsonToMap(s);
            Set<Map.Entry<String, String>> entries = jsonMap.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                //定义记录单项服务的消费金额
                Double amount = 0.0;
                //获取键即为对应的服务类型
                String key = entry.getKey();
                //值对应的是消费次数
                String value = entry.getValue();

                String apiPrice = redisUtils.getApiPrice(customerId, key);
                if (apiPrice != null){
                    amount = Double.valueOf(apiPrice) * Integer.valueOf(value);
                }else{
                    String[] split = key.split("-");
                    Integer apiTypeId = Integer.valueOf(split[0]);
                    Integer subTypeId = Integer.valueOf(split[1]);
                    Integer custId = Integer.valueOf(customerId);
                    System.out.println(apiTypeId);
                    System.out.println(subTypeId);

                    //根据customerId、apiTypeId、subTypeId查询请求服务的customerCurrDayConsume对象
                    CustomerCurrDayConsume customerCurrDayConsume = getPriceByType(custId, apiTypeId, subTypeId);
                    if(customerCurrDayConsume == null){
                        return null;
                    }
                    Double price = customerCurrDayConsume.getPrice();
                    //计算此服务总消费金额
                    int count = Integer.valueOf(value);
                    amount = count * price;
                }
                System.out.println("单项服务费用"+amount);
                //计算客户所有服务消费金额
                totalAmount = totalAmount + amount;
                System.out.println("所有服务费用"+totalAmount);
                return -totalAmount/100;
            }
        }
        return 0.0;
    }*/


    @Override
    public List<CustomerCurrDayConsumeDetail> queryCustomerCurrDayConsumeDetail(Map<String, Object> map) {
        List<CustomerCurrDayConsumeDetail> detailList = customerFinanceSelectMapper.queryCustomerCurrDayConsumeDetail(map);
        if (detailList != null){
            for (CustomerCurrDayConsumeDetail detail : detailList) {
                if (detail != null){
                    detail.setCacheCount(detail.getResultCount() - detail.getResultCostCount());
                }
            }
        }
        return detailList;
    }

    /**
     * 客户近期消费情况折线图
     * @param cid
     * @return
     */
    @Override
    public Map<String, Object> queryNearlyWeekTrend(Integer cid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<CustomerConsume> consumeList = customerFinanceSelectMapper.queryNearlyWeekTrend(cid);
        Double currentDayTotalAmount = customerCurrendDayTotalAmountUtils.getCurrentDayTotalAmount(cid + "", DateUtils.formatCurrentDate() + "");
        CustomerConsume customerConsume = new CustomerConsume();
        customerConsume.setConsuTime(CalendarTools.getCurrentDate());
        customerConsume.setConsume(currentDayTotalAmount * 100);
        List<CustomerConsume> consumeList1 = new ArrayList<CustomerConsume>();
        consumeList1.add(customerConsume);
        if (consumeList != null && consumeList.size() > 0) {
            for (CustomerConsume consume : consumeList) {
                consumeList1.add(consume);
            }
        }

        List<String> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();
        Map<Date,Object> proMap = new TreeMap<>();
        for (int i = 29; i >= 0 ; i--) {
            try {
                String yearMonth = ChartCalendarUtil.getStatetime(i);
                proMap.put(sdf.parse(yearMonth),0.0);
                if (consumeList1 != null && consumeList1.size() > 0){
                    for (CustomerConsume consume : consumeList1) {
                        if (yearMonth.equals(consume.getConsuTime())){
                            if (consume.getConsume() != null){
                                proMap.put(sdf.parse(yearMonth),-consume.getConsume()/100.0);
                            }
                            break;
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        if (proMap != null){
            for (Map.Entry<Date,Object> me : proMap.entrySet()) {
                xList.add(sdf.format(me.getKey()) + CalendarTools.getWeekOfDate(sdf.format(me.getKey())));
                yList.add((Double) me.getValue());
            }
        }

        Map mapSeries = new HashedMap();
        mapSeries.put("name","消费走势");
        mapSeries.put("data",yList);
        JSONArray jsonArrayX = JSONArray.fromObject(xList);
        JSONArray jsonArrayS = JSONArray.fromObject(mapSeries);
        Map<String,Object> resu = new HashMap();
        resu.put("jsonArrayX",jsonArrayX);
        resu.put("jsonArrayS",jsonArrayS);
        return resu;
    }

    @Override
    public String queryCustomerCompanyNameById(Integer id) {
        return customerFinanceSelectMapper.queryCustomerCompanyNameById(id);
    }

    @Override
    public Map<String,Object> queryCompanyCustomerRechargeRecordByCustomerId(Map<String, Object> map){
        Map<String,Object> param = new HashMap<>();
        if (map != null){
            for (Map.Entry<String,Object> me : map.entrySet()) {
                if("beginDate".equals(me.getKey())){
                    param.put("beginDate",me.getValue());
                }
                if("endDate".equals(me.getKey())){
                    param.put("beginDate",me.getValue());
                }
                if("reasonIdList".equals(me.getKey())){
                    param.put("reasonIdList",me.getValue());
                }
                if("customerId".equals(me.getKey())){
                    param.put("customerId",me.getValue());
                }
            }
        }
        List<CustomerBalanceLog> logList = customerFinanceSelectMapper.queryCompanyCustomerRechargeRecordByCustomerId(map);
        Double chargeTot = 0.0;
        if (logList != null){
            for (CustomerBalanceLog log : logList) {
                if (log.getAmount() != null){
                    chargeTot += log.getAmount();
                }
            }
        }
        Map<String,Object> mapResult = new HashMap<>();
        mapResult.put("logList",logList);
        mapResult.put("chargeTot",chargeTot);
        return mapResult;
    }

//    @Override
//    public Map<String,Object> queryCompanyCustomerApiConsumeRecordByCustomerId(Map<String, Object> map){
//        Map<String,Object> mapResult = new HashMap<>();
//        try {
//            List<ApiType> apiTypeList = customerFinanceSelectMapper.queryCompanyCustomerApiConsumeRecordByCustomerId(map);
//            List<CustomerApiType> customerApiTypeList = new ArrayList<>();
//            if (apiTypeList != null) {
//                for (int i = 0; i < apiTypeList.size(); i++) {
//                    ApiType apiType = apiTypeList.get(i);
//                    List<CustomerApiVendor> customerApiVendorList = apiType.getCustomerApiVendorList();
//                    long totlePrice = 0;
//                    for (int j = 0; j < customerApiVendorList.size(); j++) {
//                        CustomerApiVendor customerApiVendor = customerApiVendorList.get(j);
//                        totlePrice = totlePrice + customerApiVendor.getTotlePrice();
//                    }
//                    CustomerApiType customerApiType = new CustomerApiType();
//                    customerApiType.setApiTypeId(apiType.getId());
//                    customerApiType.setApiTypeName(apiType.getName());
//                    customerApiType.setTotlePrice(totlePrice);
//                    customerApiType.setCustomerApiVendors(apiType.getCustomerApiVendorList());
//                    customerApiTypeList.add(customerApiType);
//                }
//            }
//            mapResult.put("queryCompanyCustomerApiConsumeRecordByCustomerId",customerApiTypeList);
//            mapResult.put("getCountCompanyCustomerApiConsumeRecordByCustomerId",customerFinanceSelectMapper.getCountCompanyCustomerApiConsumeRecordByCustomerId(map));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return mapResult;
//    }
//
//    @Override
//    public Map<String,Object> queryCompanyCustomerApiDetailConsumeRecordByCustomerId(Map<String, Object> map){
//        Map<String,Object> mapTran = new HashMap<>();
//
//        try {
//            mapTran.put("queryCompanyCustomerApiDetailConsumeRecordByCustomerId",customerFinanceSelectMapper.queryCompanyCustomerApiDetailConsumeRecordByCustomerId(map));
//            mapTran.put("getCountCompanyCustomerApiDetailConsumeRecordByCustomerId",customerFinanceSelectMapper.getCountCompanyCustomerApiDetailConsumeRecordByCustomerId(map));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return mapTran;
//    }
//
//    @Override
//    public Map<String,Object> queryCompanyCustomerWeekMonthRecordByCustomerId(Map<String, Object> map){
//        Map<String,Object> mapTran = new HashMap<>();
//        try {
//            mapTran.put("queryCompanyCustomerWeekMonthRecordByCustomerId",customerFinanceSelectMapper.queryCompanyCustomerWeekMonthRecordByCustomerId(map));
//            mapTran.put("getCountCompanyCustomerWeekMonthRecordByCustomerId",customerFinanceSelectMapper.getCountCompanyCustomerWeekMonthRecordByCustomerId(map));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return mapTran;
//    }
//
//    @Override
//    public List<Integer> queryCompanyCustomerYearsByCustomerId(Map<String, Object> map){
//        try {
//            return customerFinanceSelectMapper.queryCompanyCustomerYearsByCustomerId(map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public List<Integer> queryCompanyCustomerMonthsByCustomerId(Map<String, Object> map){
//        try {
//            return customerFinanceSelectMapper.queryCompanyCustomerMonthsByCustomerId(map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public List<Integer> queryCompanyCustomerWeeksByCustomerId(Map<String, Object> map){
//        try {
//            return customerFinanceSelectMapper.queryCompanyCustomerWeeksByCustomerId(map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public Map<String, List> queryMonthChargeConsumeToward(Map<String, Object> map) {
//        Set<Map.Entry<String,Object>> set = map.entrySet();
//        Iterator<Map.Entry<String,Object>> it = set.iterator();
//        Integer customerId = null;
//        Integer tableId = null;
//        Integer result = null;
//        while(it.hasNext()){
//            Map.Entry<String,Object> me = it.next();
//            if(me.getKey().equals("customerId")){
//                customerId = (Integer)me.getValue();
//            }
//            if(me.getKey().equals("tableId") ){
//                tableId = (Integer)me.getValue();
//            }
//            if(me.getKey().equals("result") ){
//                result = (Integer)me.getValue();
//            }
//        }
//        List<Long> yList = new ArrayList<>();
//        List<String> xList = new ArrayList<>();
//        Map<String, List> stringListMap = new HashedMap();
//
//        for (int i = 12; i >0; i--) {
//            WeekMonthAmount weekMonthAmount = customerFinanceSelectMapper.queryMonthChargeConsumeToward(customerId, tableId, (result+i));
//
//            if (weekMonthAmount != null) {
//                if (weekMonthAmount.getTotleAmount()>0){
//                    yList.add((weekMonthAmount.getTotleAmount()/100));
//                }else{
//                    yList.add(-(weekMonthAmount.getTotleAmount()/100));
//                }
//                xList.add(weekMonthAmount.getYears() + "年" + weekMonthAmount.getMonths() + "月");
//            } else {
//                yList.add(0L);
//                xList.add(CalendarTools.getYearMonthCount(result+i) + "年" + CalendarTools.getMonthCount(result+i) + "月");
//            }
//        }
//        stringListMap.put("xPort",xList);
//        stringListMap.put("type",yList);
//        return stringListMap;
//    }
//
//    @Override
//    public List<CustomerApiVendor> queryCustomerConsumeByVendor(Map<String, Object> map) {
//        try {
//            return customerFinanceSelectMapper.queryCustomerConsumeByVendor(map);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public List<ApiType> queryApiTypeByCustomerId(Map<String, Object> map) {
//        try {
//            return customerFinanceSelectMapper.queryApiTypeByCustomerId(map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    @DataSourceService
//    public List<ApiVendor> queryApiVendorByCustomerId(Map<String, Object> map) {
//        try {
//            return customerFinanceSelectMapper.queryApiVendorByCustomerId(map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
