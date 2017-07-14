package org.qydata.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.qydata.entity.Partner;
import org.qydata.entity.excel.ExportExcel;
import org.qydata.service.ExcelService;
import org.qydata.tools.excel.ExportExcelIoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/3/15.
 */
@Aspect
@Component
@Slf4j
public class ExportExcelAop {

    @Autowired
    private ExcelService excelService;
    @Autowired
    private HttpServletResponse response;

    /**
     * Aop测试导出Excel
     * @param point
     */
    @Around("execution(* org.qydata.controller.ExcelController.extraAccount(..))")
    public Object userTestExportExcel(ProceedingJoinPoint point) throws Throwable {
        Object args [] = point.getArgs();
        System.out.println("************* 判断是否是执行导出操作 *************");
        if (args[0] != null && args[0].getClass() == String.class && args[0].equals("true")) {
            System.out.println("************* 执行导出操作 *************");
            Map<String,Object> map = new HashMap<>();
            if(args[1] != null && args[1] != ""){
                map.put("pid",args[1]);
            }
            if(args[2] != null){
                map.put("wid",args[2]);
            }
            if(args[3] != null){
                map.put("cid",args[3]);
            }
            if (args[4] != null && args[4] != "" ) {
                map.put("beginDate",args[4] );
            }
            if(args[5] != null && args[5] != ""){
                map.put("endDate", args[5]);
            }

            Map<String,Object> mapResu = excelService.queryExtraAccount(map);
            List<ExportExcel> exportExcelListPartnerUserOur = (List<ExportExcel>) mapResu.get("exportExcelListPartnerUserOur");
            List<ExportExcel> exportExcelListOurUserPartner = (List<ExportExcel>) mapResu.get("exportExcelListOurUserPartner");
            List<ExportExcel> exportExcelListPartnerUserOurSell = (List<ExportExcel>) mapResu.get("exportExcelListPartnerUserOurSell");
            Partner partner = (Partner) mapResu.get("partner");
            String partnerName = "对方";
            if (partner != null){
                partnerName = partner.getName();
            }
            Map<String,Object> mapExcel = new HashMap<>();
            mapExcel.put("exportExcelListPartnerUserOur",exportExcelListPartnerUserOur);
            mapExcel.put("exportExcelListOurUserPartner",exportExcelListOurUserPartner);
            mapExcel.put("exportExcelListPartnerUserOurSell",exportExcelListPartnerUserOurSell);
            mapExcel.put("partnerName",partnerName);
            String fileName = partnerName + "千眼对账单";
            ExportExcelIoUtils.exportExcelIo(mapExcel,fileName,response);
            return null;
        }
        System.out.println("************* 未执行导出操作 *************");
        return point.proceed(args);
    }

}
