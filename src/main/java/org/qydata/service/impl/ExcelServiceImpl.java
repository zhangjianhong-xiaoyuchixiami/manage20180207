package org.qydata.service.impl;

import org.qydata.entity.excel.ExportExcel;
import org.qydata.mapper.ExcelMapper;
import org.qydata.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/7/7.
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private ExcelMapper excelMapper;

    @Override
    public Map<String, Object> queryExtraAccount(Map<String, Object> map) {

        Integer pid = (Integer) map.get("pid");
        Integer obj = (Integer) map.get("obj");
        Integer wid = (Integer) map.get("wid");
        Integer cid = (Integer) map.get("cid");
        List<ExportExcel> exportExcelList = null;
        if (obj != null){
            if (obj == 1){
                if (wid != null){
                    if (wid == 1){
                        exportExcelList = excelMapper.queryPartnerUserOurBySum(map);
                    }
                    if (wid == 2){
                        exportExcelList = excelMapper.queryPartnerUserOurByMonth(map);
                    }
                    if (wid == 3){
                        exportExcelList = excelMapper.queryPartnerUserOurByDay(map);
                    }
                }
            }
            if (obj == 2){
                if (wid != null){
                    if (wid == 1){
                        exportExcelList = excelMapper.queryOurUserPartnerBySum(map);
                    }
                    if (wid == 2){
                        exportExcelList = excelMapper.queryOurUserPartnerByMonth(map);
                    }
                    if (wid == 3){
                        exportExcelList = excelMapper.queryOurUserPartnerByDay(map);
                    }
                }
            }
            if (obj == 3){
                if (wid != null){
                    if (wid == 1){

                    }
                    if (wid == 2){

                    }
                    if (wid == 3){

                    }
                }
            }
        }

        return null;
    }
}
