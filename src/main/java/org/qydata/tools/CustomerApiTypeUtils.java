package org.qydata.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomerApiTypeUtils {

    public static CustomerApiType getCustomerApi(String customerId,String json){
        Map<String, String> map = JsonUtils.jsonToMap(json);
        CustomerApiType customerApiType = new CustomerApiType();
        List<String>list = new ArrayList<String>();
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            list.add(key);

        }

        return customerApiType;
    }

//    public static

}
