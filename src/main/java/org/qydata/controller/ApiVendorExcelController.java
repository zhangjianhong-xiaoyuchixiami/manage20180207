package org.qydata.controller;

import org.qydata.service.ApiVendorExcelService;
import org.qydata.tools.excel.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public String fileUpload(){

        return "/excel/api_vendor_excel";

    }

    @RequestMapping("/file-upload-service")
    @ResponseBody
    public String readExcelFile(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request) {
        String filePath =  FileUploadUtils.uploadExcelFile(request,file);
        if (filePath != null){
            //ReadExcelUtils.readExcelSheet(filePath);
            apiVendorExcelService.readExcelFile(filePath);
        }
        return "/excel/api_vendor_excel";
    }


}
