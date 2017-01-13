package org.qydata.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.qydata.entity.User;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/13.
 */
public class ViewExcel extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> obj, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
// map的key，在对应的controller中设置
        List<User> list = (List<User>) obj.get("list");
        HSSFSheet sheet = workbook.createSheet("用户信息");
        sheet.setDefaultColumnWidth((short) 12);
        HSSFCell cell = getCell(sheet, 0, 0);
        setText(cell, "ID");
        cell = getCell(sheet, 0, 1);
        setText(cell, "姓名");
        cell = getCell(sheet, 0, 2);
        setText(cell, "用户名");
        cell = getCell(sheet, 0, 3);
        setText(cell, "电话");

        for (short i = 0; i < list.size(); i++) {
            HSSFRow sheetRow = sheet.createRow(i+1);
            User user= list.get(i);
            sheetRow.createCell(0).setCellValue(user.getId());
            sheetRow.createCell(1).setCellValue(user.getName());
            sheetRow.createCell(2).setCellValue(user.getUsername());
            sheetRow.createCell(3).setCellValue(user.getTel());
        }
        //设置下载时客户端Excel的名称
        String filename = new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xls";
        //处理中文文件名
        filename = MyUtils.encodeFilename(filename, request);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }
}
