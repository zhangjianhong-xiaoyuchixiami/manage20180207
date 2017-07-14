//package org.qydata.tools.excel;
//
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.*;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///**
// * Created by jonhn on 2017/7/13.
// */
//public class WriteExcelUtils {
//
//
//
//
//    public static byte [] produceExcel(Map<String,Object> map) {
//
//        if (map != null){
//            //第一步创建workbook
//            HSSFWorkbook wb = new HSSFWorkbook();
//            List<String> sheetList = null;
//            List<String [] > titleList = null;
//            List<List> contentList = null;
//            Set<Map.Entry<String,Object>> set = map.entrySet();
//            Iterator<Map.Entry<String,Object>> it = set.iterator();
//            while (it.hasNext()){
//                Map.Entry<String,Object> me = it.next();
//                if (me.getKey().equals("sheetList")){
//                    sheetList = (List<String>) me.getValue();
//                }
//                if (me.getKey().equals("titleList")){
//                    titleList = (List<String[]>) me.getValue();
//                }
//                if (me.getKey().equals("contentList")){
//                    contentList = (List<List>) me.getValue();
//                }
//            }
//            if (sheetList != null){
//                for (int i = 0; i < sheetList.size() ; i++) {
//                    String sheetName = sheetList.get(i);
//                    Sheet sheet = wb.createSheet(sheetName);
//
//                    CellStyle style = wb.createCellStyle();
//                    Font font = wb.createFont();   // 设置字体加粗
//                    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//                    style.setFont(font);
//                    style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案颜色
//                    style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案背景色
//                    style.setFillPattern(HSSFCellStyle.THIN_FORWARD_DIAG);//设置图案样式
//                    style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
//                    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
//                    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
//                    style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
//
//                    String [] title = titleList.get(i);
//                    for (int j = 0; j < title.length; j++) {
//                        sheet.setColumnWidth(j, 31 * 256);//设置第i列的宽度是31个字符宽度
//                        Row row = sheet.createRow(0);
//                        row.setHeightInPoints(20);//设置行的高度是20个点
//                        Cell cell = row.createCell(j);
//                        cell.setCellValue(title[j]);
//                        cell.setCellStyle(style);
//                    }
//                    List content = contentList.get(i);
//                    for (int j = 0; j < content.size() ; j++) {
//
//                    }
//
//                }
//
//            }
//        }
//
//
//
//
//
//
//        CellStyle style = wb.createCellStyle();
//        Font font = wb.createFont();   // 设置字体加粗
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        style.setFont(font);
//        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案颜色
//        style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案背景色
//        style.setFillPattern(HSSFCellStyle.THIN_FORWARD_DIAG);//设置图案样式
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
//
//        Row row = sheetCount.createRow(0);
//        row.setHeightInPoints(20);//设置行的高度是20个点
//        Cell cell = row.createCell(0);
//        cell.setCellValue("产品");
//        cell.setCellStyle(style);
//
//        cell = row.createCell(1);
//        cell.setCellValue("扣费量");
//        cell.setCellStyle(style);
//
//        cell = row.createCell(2);
//        cell.setCellValue("统计区间");
//        cell.setCellStyle(style);
//
//        style = wb.createCellStyle();
//        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案颜色
//        style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案背景色
//        style.setFillPattern(HSSFCellStyle.THIN_FORWARD_DIAG);//设置图案样式
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
//
//        //第六步将生成excel文件保存到数据库
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        try {
//            wb.write(out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                out.close();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }
//        return out.toByteArray();
//    }
//}
