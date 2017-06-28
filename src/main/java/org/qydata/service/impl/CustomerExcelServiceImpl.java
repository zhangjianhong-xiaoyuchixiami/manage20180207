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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

                String [] to = null;
                if (email != null){
                    to = new String[]{email};
                }else {
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
                String title = companyName+"对账单";
                String content = "您好，附件是贵司"+CalendarUtil.getCurrentDateLastMonthYear()+"年"+CalendarUtil.getCurrentDateLastMonthMonth()+"月"+"数据调用情况，请您核对。如有问题，请返回贵司统计结果，若对我司统计结果无异议，请邮件回复确认，谢谢！";
                String url = "http://192.168.111.148:7779/download-consume-check?customerId="+customerId[i]+
                        "&year="+ CalendarUtil.getCurrentDateLastMonthYear() +
                        "&month="+CalendarUtil.getCurrentDateLastMonthMonth() +
                        "&companyName="+ URLEncoder.encode(companyName);
                //String name = CalendarUtil.getCurrentDateLastMonthYear()+"-"+CalendarUtil.getCurrentDateLastMonthMonth()+companyName+".xls";
                String name = "xxxx.xls";
                String  fileName = null; // 解决中文附件乱码
                try {
                    fileName = MimeUtility.encodeText(name);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    SendEmail.sendMail(to,title,content,url,fileName);
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
