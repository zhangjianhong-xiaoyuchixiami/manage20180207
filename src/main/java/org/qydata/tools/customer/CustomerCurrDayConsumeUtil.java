//package org.qydata.tools.customer;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.lang.StringUtils;
//import org.qydata.dst.customer.CustomerCurrDayConsume;
//import org.qydata.service.CustomerFinanceService;
//import org.qydata.tools.DateUtils;
//import org.qydata.tools.JsonUtils;
//import org.qydata.tools.https.HttpClientUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//public class CustomerCurrDayConsumeUtil {
//
//    @Autowired
//    private CustomerFinanceService service;
//
//    public static Double customerCurrDayConsume(Integer cid){
//        //调用接口获得客户请求的服务和对应的次数
//        String result = HttpClientUtil.httpRequest(String.valueOf(cid), String.valueOf(DateUtils.formatCurrentDate()));
//        //去掉接口返回值为null,""," ","{}","null","NULL"
//        if (StringUtils.isBlank(result) || result.replace(" ","").length() == 2 || result.equalsIgnoreCase("null")){
//            return null;
//        }
//        try {
//            //非json格式字符串会报错
//           JSONObject.parseObject(result);
//        } catch (Exception e) {
//            return null;
//        }
//        //将json转换成map
//        Map<String, String> jsonMap = JsonUtils.jsonToMap(result);
//        //定义记录该客户当天消费金额
//        Double totalAmount = 0.0;
//        if (jsonMap != null){
//            for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
//                //定义记录单项服务的消费金额
//                Double amount = 0.0;
//                String key = entry.getKey();
//                String value = entry.getValue();
//                String[] split = key.split("-");
//                Integer apiTypeId = Integer.valueOf(split[0]);
//                Integer subTypeId = Integer.valueOf(split[1]);
//                Integer custId = Integer.valueOf(cid);
//                //根据customerId、apiTypeId、subTypeId查询请求服务的compnyApi对象
//                CustomerCurrDayConsume consume = service.getPriceByType(custId, apiTypeId, subTypeId);
//                if(consume == null){
//                    return null;
//                }
//                Double price = consume.getPrice();
//                //计算此服务总消费金额
//                int count = Integer.valueOf(value);
//                amount = count * price;
//                System.out.println("单项服务费用"+amount);
//                //封装客户此服务当天消费金额
//                consume.setSumAmount(amount);
//                //计算客户所有服务消费金额
//                totalAmount = totalAmount + amount;
//                System.out.println("所有服务费用"+totalAmount);
//                //封装客户当天服务消费次数
//                consume.setCountSuccess(count);
//                consume.setApiTypeId(apiTypeId);
//                consume.setStid(subTypeId);
//                consume.setCustomerId(custId);
//                consumeList.add(customerCurrDayConsume);
//            }
//        }
//
//    }
//
//}
