package org.qydata.service;

import java.util.Map;

/**
 * Created by jonhn on 2017/7/7.
 */
public interface ExcelService {

    /**
     * 财务对账
     * @param map
     * @return
     */
    public Map<String,Object> queryExtraAccount(Map<String,Object> map);

}
