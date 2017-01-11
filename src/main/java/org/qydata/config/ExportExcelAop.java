package org.qydata.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by jonhn on 2017/1/11.
 */
@Aspect
@Component
@Slf4j
public class ExportExcelAop {

    private final Logger log = Logger.getLogger(this.getClass());

//
//    @After("execution(* org.qydata.exportexcelcontroller.*.find*(..)) " )
//    public void exportExcel() throws IOException {
//
//        log.info("执行导出Excel");
//    }



}
