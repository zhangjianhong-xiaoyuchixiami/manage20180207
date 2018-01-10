package org.qydata.tools;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.qydata.dst.customer.CustomerCurrDayConsume;
import org.qydata.mapper.mapper1.CustomerFinanceMapper;
import org.qydata.mapper.mapper2.CompanySelectMapper;
import org.qydata.mapper.mapper2.CustomerCacheFinanceSelectMapper;
import org.qydata.mapper.mapper2.CustomerFinanceSelectMapper;
import org.qydata.tools.https.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class CustomerCurrendDayTotalAmountUtils {

//    @Autowired
//    private RedisUtils redisUtils;
    @Autowired
    private CustomerFinanceMapper customerFinanceMapper;
    @Autowired
    private CustomerFinanceSelectMapper customerFinanceSelectMapper;
    @Autowired
    private CompanySelectMapper companySelectMapper;

    public Double getCurrentDayTotalAmount(String customerId, String currentDate){

        //调用接口获得客户请求的服务和对应的扣费次数
        String authKey = companySelectMapper.queryAuthKey("admin.k");
        String s = HttpClientUtil.httpRequest(customerId, currentDate, authKey);
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

                String[] split = key.split("-");
                Integer apiTypeId = Integer.valueOf(split[0]);
                Integer subTypeId = Integer.valueOf(split[1]);
                Integer custId = Integer.valueOf(customerId);
//                System.out.println(apiTypeId);
//                System.out.println(subTypeId);

                //根据customerId、apiTypeId、subTypeId查询请求服务的customerCurrDayConsume对象
                CustomerCurrDayConsume customerCurrDayConsume = getPriceByType(custId, apiTypeId, subTypeId);
                if(customerCurrDayConsume == null){
                    return null;
                }
                Double price = customerCurrDayConsume.getPrice();
                //计算此服务总消费金额
                int count = Integer.valueOf(value);
                amount = count * price;
//                System.out.println("单项服务费用"+amount);
                //计算客户所有服务消费金额
                totalAmount = totalAmount + amount;
//                System.out.println("所有服务费用"+totalAmount);
            }
            return -totalAmount/100;
        }
        return 0.0;
    }

    /**
     * 查询客户产品价格
     * @param customerId
     * @param apiTypeId
     * @param subTypeId
     * @return
     */
    public CustomerCurrDayConsume getPriceByType(Integer customerId, Integer apiTypeId, Integer subTypeId) {
        Map<String,Integer>typeMap = new HashMap<String,Integer>();
        typeMap.put("customerId", customerId);
        typeMap.put("apiTypeId", apiTypeId);
        typeMap.put("subTypeId", subTypeId);
        CustomerCurrDayConsume customerCurrDayConsume = customerFinanceSelectMapper.getPriceByType(typeMap);
        return customerCurrDayConsume;
    }

}
