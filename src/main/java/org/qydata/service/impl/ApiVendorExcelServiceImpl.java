package org.qydata.service.impl;


import org.apache.poi.ss.usermodel.Workbook;
import org.qydata.service.ApiVendorExcelService;
import org.qydata.tools.excel.ReadExcelUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/6/19.
 */
@Service
public class ApiVendorExcelServiceImpl implements ApiVendorExcelService {


    @Override
    public List readExcelFile(String path) {
        try {
            Workbook wb = ReadExcelUtils.readExcel(path);
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {//获取每个Sheet表
                // 对读取Excel表格标题测试
                String[] title = ReadExcelUtils.readExcelTitle(wb, i);
                System.out.println("获得Excel表格的标题:");
                for (String s : title) {
                    System.out.print(s + " ");
                }

                // 对读取Excel表格内容测试
                Map<Integer, Map<Integer, Object>> map = ReadExcelUtils.readExcelContent(wb, i);
                System.out.println("获得Excel表格的内容:");
                for (int j = 1; j <= map.size(); j++) {
                    System.out.println(map.get(j));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
