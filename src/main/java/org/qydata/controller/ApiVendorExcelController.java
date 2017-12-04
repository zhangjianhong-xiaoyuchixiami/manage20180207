package org.qydata.controller;

import org.qydata.config.annotation.SystemControllerLog;
import org.qydata.service.ApiVendorExcelService;
import org.qydata.tools.excel.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jonhn on 2017/6/19.
 */
@Controller
public class ApiVendorExcelController {

    @Autowired
    private ApiVendorExcelService apiVendorExcelService ;

    @RequestMapping("/file-upload")
    @SystemControllerLog(description = "文件上传")
    public String fileUpload(){

        return "/excel/api_vendor_excel";

    }

    @RequestMapping("/file-upload-service")
    @SystemControllerLog(description = "excel文件上传")
    public void readExcelFile(@RequestParam(value = "file") MultipartFile file,HttpServletRequest request) {
        String filePath =  FileUploadUtils.uploadExcelFile(request,file);
        System.out.println("你好");
        if (filePath != null){
            //ReadExcelUtils.readExcelSheet(filePath);
            apiVendorExcelService.readExcelFile(filePath);

        }

    }


}
