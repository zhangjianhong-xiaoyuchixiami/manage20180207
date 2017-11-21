package org.qydata.service.impl;

import org.qydata.dst.customer.CustomerReqLog;
import org.qydata.mapper.SearchMapper;
import org.qydata.service.SearchService;
import org.qydata.tools.customer.CustomerDataParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/11/16.
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchMapper mapper;

    @Override
    public List<CustomerReqLog> queryCustomerReqLog(Map<String, Object> map) {
        List<CustomerReqLog> logList = mapper.queryCustomerReqLog(map);
        if (logList == null || logList.size() <= 0){
            return null;
        }
        for (CustomerReqLog log : logList) {
            if (log == null){
                continue;
            }
            if (log.getVname_pname_aname() == "" || "".equals(log.getVname_pname_aname())){
                log.setVname_pname_aname(null);
            }
            if (log.getAmount() != null){
                log.setIsCost("是");
            }else {
                log.setIsCost("否");
            }
            if (log.getDataSourceId() != null || log.getC0cacheId() != null){
                log.setIsCache("是");
            }else {
                log.setIsCache("否");
            }
            if (log.getDur() != null){
                log.setDurName(log.getDur() + "ms");
            }
            if (log.getReqContent() != null){
                log.setReqContent(CustomerDataParamUtil.reqParam(log.getReqContent()));
            }
            if (log.getRespContent() != null){
                log.setResult(CustomerDataParamUtil.respParam(log.getRespContent()));
            }
            if (log.getRespContent() != null){
                log.setPhoto(CustomerDataParamUtil.photoParam(log.getRespContent()));
            }
            if (log.getRespContent() != null){
                log.setRespContent(CustomerDataParamUtil.deleteRespPhoto(log.getRespContent()));
            }
        }
        return logList;
    }


}
