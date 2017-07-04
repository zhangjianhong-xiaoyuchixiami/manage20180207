package org.qydata.service.impl;

import org.qydata.entity.CustomerCompanyEmail;
import org.qydata.entity.CustomerConsumeExcel;
import org.qydata.mapper.CustomerExcelMapper;
import org.qydata.mapper.CustomerFinanceMapper;
import org.qydata.service.CustomerExcelService;
import org.qydata.tools.date.CalendarUtil;
import org.qydata.tools.email.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeUtility;
import java.util.*;

/**
 * Created by jonhn on 2017/6/21.
 */
@Service
public class CustomerExcelServiceImpl implements CustomerExcelService {

    @Autowired
    private CustomerExcelMapper customerExcelMapper;
    @Autowired
    private CustomerFinanceMapper customerFinanceMapper;

    @Override
    public Map<String, Object> queryCustomerFinanceAccountExcel(Map<String, Object> map) {
        Map<String,Object> mapResu = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        String [] customerId = null;
        String email = null;
        Set<Map.Entry<String,Object>> set = map.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> me = it.next();
            if (me.getKey().equals("customerId")) {
                customerId = (String[]) me.getValue();
            }
            if (me.getKey().equals("email")) {
                email = (String) me.getValue();
            }
        }
        if (customerId != null){
            for (int i = 0; i < customerId.length ; i++) {
                String companyName = customerFinanceMapper.queryCustomerCompanyNameById(Integer.parseInt(customerId[i]));

                String [] copyTo = null;
                String [] to = null;
                if (email != null){
                    to = new String[]{email};
                }else {
                    copyTo = new String[]{"ld@qianyandata.com","it@qianyandata.com","accounting@qianyandata.com"};
                    Map<String,Object> mapParam = new HashMap<>();
                    mapParam.put("companyId",customerFinanceMapper.queryCompanyIdByCustomerId(Integer.parseInt(customerId[i])));
                    List<CustomerCompanyEmail> customerCompanyEmailList = customerFinanceMapper.queryCustomerEmail(mapParam);
                    if (customerCompanyEmailList != null){
                        to = new String[customerCompanyEmailList.size()];
                        for (int j = 0; j < customerCompanyEmailList.size() ; j++) {
                            CustomerCompanyEmail customerCompanyEmail = customerCompanyEmailList.get(j);
                            to[j] = customerCompanyEmail.getEmail();
                        }
                    }
                }
                String title = "【"+companyName+"】"+"对账单";
                String url = "https://b.qianyandata.com/download-consume-check?customerId="+customerId[i]+ "&year="+ CalendarUtil.getCurrentDateLastMonthYear() + "&month="+CalendarUtil.getCurrentDateLastMonthMonth() + "&companyName="+ companyName;
                String name = CalendarUtil.getCurrentDateLastMonthYear()+"-"+CalendarUtil.getCurrentDateLastMonthMonth();
                try {
                    String fileName = MimeUtility.encodeText(name);
                    SendEmail.sendMail(to,copyTo,title,url,fileName);
                } catch (Exception e) {
                    sb.append(companyName+"，");
                    mapResu.put("fail",sb+"邮件发送失败其余发送正常");
                    e.printStackTrace();
                }
            }
            mapResu.put("success","邮件已发送");
        }
        return mapResu;
    }

    @Override
    public CustomerConsumeExcel queryCustomerConsumeExcelByCustomerId(Map<String, Object> map) {
        return customerExcelMapper.queryCustomerConsumeExcelByCustomerId(map);
    }
}
