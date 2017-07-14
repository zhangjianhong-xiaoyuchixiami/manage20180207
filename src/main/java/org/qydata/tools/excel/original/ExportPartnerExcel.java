package org.qydata.tools.excel.original;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.qydata.entity.excel.ExportExcel;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jonhn on 2017/7/14.
 */
public class ExportPartnerExcel {

    public static HSSFWorkbook createExcel(Map<String,Object> map) {

        List<ExportExcel> exportExcelListPartnerUserOur = null;
        List<ExportExcel> exportExcelListOurUserPartner = null;
        List<ExportExcel> exportExcelListPartnerUserOurSell = null;
        String partnerName = null;
        Set<Map.Entry<String,Object>> set = map.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while (it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if (me.getKey().equals("exportExcelListPartnerUserOur")){
                exportExcelListPartnerUserOur = (List<ExportExcel>) me.getValue();
            }
            if (me.getKey().equals("exportExcelListOurUserPartner")){
                exportExcelListOurUserPartner = (List<ExportExcel>) me.getValue();
            }
            if (me.getKey().equals("exportExcelListPartnerUserOurSell")){
                exportExcelListPartnerUserOurSell = (List<ExportExcel>) me.getValue();
            }
            if (me.getKey().equals("partnerName")){
                partnerName = (String) me.getValue();
            }
        }

        //第一步创建workbook
        HSSFWorkbook wb = new HSSFWorkbook();

        Sheet sheetOne = wb.createSheet(partnerName+"消费我方源");

        sheetOne.setColumnWidth(0, 31 * 256);//设置第0列的宽度是31个字符宽度
        sheetOne.setColumnWidth(1, 31 * 256);//设置第1列的宽度是31个字符宽度
        sheetOne.setColumnWidth(2, 31 * 256);//设置第1列的宽度是31个字符宽度
        sheetOne.setColumnWidth(3, 31 * 256);//设置第0列的宽度是31个字符宽度
        sheetOne.setColumnWidth(4, 31 * 256);//设置第1列的宽度是31个字符宽度
        sheetOne.setColumnWidth(5, 31 * 256);//设置第1列的宽度是31个字符宽度

        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();   // 设置字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案颜色
        style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案背景色
        style.setFillPattern(HSSFCellStyle.THIN_FORWARD_DIAG);//设置图案样式
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        Row row = sheetOne.createRow(0);
        row.setHeightInPoints(20);//设置行的高度是20个点
        Cell cell = row.createCell(0);
        cell.setCellValue("产品类型");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("供应商");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("进货价");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("使用量");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("金额");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("统计区间");
        cell.setCellStyle(style);

        style = wb.createCellStyle();
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案颜色
        style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案背景色
        style.setFillPattern(HSSFCellStyle.THIN_FORWARD_DIAG);//设置图案样式
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        if (exportExcelListPartnerUserOur != null){
            for (int i = 0; i < exportExcelListPartnerUserOur.size(); i++) {
                ExportExcel exportExcel = exportExcelListPartnerUserOur.get(i);
                row = sheetOne.createRow(i + 1);
                //创建单元格并且添加数据
                cell = row.createCell(0);
                cell.setCellStyle(style);
                if(exportExcel.getApiTypeName_stidName() != null){
                    cell.setCellValue(exportExcel.getApiTypeName_stidName());
                }
                cell = row.createCell(1);
                cell.setCellStyle(style);
                if (exportExcel.getVendorName() != null) {
                    cell.setCellValue(exportExcel.getVendorName());
                }
                cell = row.createCell(2);
                cell.setCellStyle(style);
                if (exportExcel.getCost() != null) {
                    cell.setCellValue(exportExcel.getCost());
                }
                cell = row.createCell(3);
                cell.setCellStyle(style);
                if (exportExcel.getSumCount() != null) {
                    cell.setCellValue(exportExcel.getSumCount());
                }
                cell = row.createCell(4);
                cell.setCellStyle(style);
                if (exportExcel.getSumCount() != null) {
                    cell.setCellFormula("product(C"+(i+2)+",D"+(i+2)+")");
                }
                cell = row.createCell(5);
                cell.setCellStyle(style);
                if (exportExcel.getConsuTime() != null) {
                    cell.setCellValue(exportExcel.getConsuTime());
                }
            }
            row = sheetOne.createRow(exportExcelListPartnerUserOur.size() + 1);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue("总计");

            cell = row.createCell(1);
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellStyle(style);
            cell.setCellFormula("sum(E2:E"+(exportExcelListPartnerUserOur.size() + 1)+")");

            cell = row.createCell(5);
            cell.setCellStyle(style);

        }

        Sheet sheetTwo = wb.createSheet("我方消费" + partnerName + "源");

        sheetTwo.setColumnWidth(0, 31 * 256);//设置第0列的宽度是31个字符宽度
        sheetTwo.setColumnWidth(1, 31 * 256);//设置第1列的宽度是31个字符宽度
        sheetTwo.setColumnWidth(2, 31 * 256);//设置第1列的宽度是31个字符宽度
        sheetTwo.setColumnWidth(3, 31 * 256);//设置第0列的宽度是31个字符宽度
        sheetTwo.setColumnWidth(4, 31 * 256);//设置第1列的宽度是31个字符宽度
        sheetTwo.setColumnWidth(5, 31 * 256);//设置第1列的宽度是31个字符宽度

        style = wb.createCellStyle();
        font = wb.createFont();   // 设置字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案颜色
        style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案背景色
        style.setFillPattern(HSSFCellStyle.THIN_FORWARD_DIAG);//设置图案样式
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        row = sheetTwo.createRow(0);
        row.setHeightInPoints(20);//设置行的高度是20个点
        cell = row.createCell(0);
        cell.setCellValue("产品类型");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("供应商");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("进货价");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("使用量");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("金额");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("统计区间");
        cell.setCellStyle(style);

        style = wb.createCellStyle();
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案颜色
        style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案背景色
        style.setFillPattern(HSSFCellStyle.THIN_FORWARD_DIAG);//设置图案样式
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        if (exportExcelListOurUserPartner != null){
            for (int j = 0; j < exportExcelListOurUserPartner.size(); j++) {
                ExportExcel exportExcel = exportExcelListOurUserPartner.get(j);
                row = sheetTwo.createRow(j + 1);
                //创建单元格并且添加数据
                cell = row.createCell(0);
                cell.setCellStyle(style);
                if(exportExcel.getApiTypeName_stidName() != null){
                    cell.setCellValue(exportExcel.getApiTypeName_stidName());
                }
                cell = row.createCell(1);
                cell.setCellStyle(style);
                if (exportExcel.getVendorName() != null) {
                    cell.setCellValue(exportExcel.getVendorName());
                }
                cell = row.createCell(2);
                cell.setCellStyle(style);
                if (exportExcel.getCost() != null) {
                    cell.setCellValue(exportExcel.getCost());
                }
                cell = row.createCell(3);
                cell.setCellStyle(style);
                if (exportExcel.getSumCount() != null) {
                    cell.setCellValue(exportExcel.getSumCount());
                }
                cell = row.createCell(4);
                cell.setCellStyle(style);
                if (exportExcel.getSumCount() != null) {
                    cell.setCellFormula("product(C"+(j+2)+",D"+(j+2)+")");
                }
                cell = row.createCell(5);
                cell.setCellStyle(style);
                if (exportExcel.getConsuTime() != null) {
                    cell.setCellValue(exportExcel.getConsuTime());
                }
            }
            row = sheetTwo.createRow(exportExcelListOurUserPartner.size() + 1);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue("总计");

            cell = row.createCell(1);
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellStyle(style);
            cell.setCellFormula("sum(E2:E"+(exportExcelListOurUserPartner.size() + 1)+")");

            cell = row.createCell(5);
            cell.setCellStyle(style);

        }

        Sheet sheetThree = wb.createSheet(partnerName + "使用我方源售卖");

        sheetThree.setColumnWidth(0, 31 * 256);//设置第0列的宽度是31个字符宽度
        sheetThree.setColumnWidth(1, 31 * 256);//设置第1列的宽度是31个字符宽度
        sheetThree.setColumnWidth(2, 31 * 256);//设置第1列的宽度是31个字符宽度
        sheetThree.setColumnWidth(3, 31 * 256);//设置第0列的宽度是31个字符宽度
        sheetThree.setColumnWidth(4, 31 * 256);//设置第1列的宽度是31个字符宽度
        sheetThree.setColumnWidth(5, 31 * 256);//设置第1列的宽度是31个字符宽度

        style = wb.createCellStyle();
        font = wb.createFont();   // 设置字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案颜色
        style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案背景色
        style.setFillPattern(HSSFCellStyle.THIN_FORWARD_DIAG);//设置图案样式
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        row = sheetThree.createRow(0);
        row.setHeightInPoints(20);//设置行的高度是20个点
        cell = row.createCell(0);
        cell.setCellValue("产品类型");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("供应商");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("售卖价");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("使用量");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("金额");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("统计区间");
        cell.setCellStyle(style);

        style = wb.createCellStyle();
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案颜色
        style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);//设置图案背景色
        style.setFillPattern(HSSFCellStyle.THIN_FORWARD_DIAG);//设置图案样式
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        if (exportExcelListPartnerUserOurSell != null){
            for (int j = 0; j < exportExcelListPartnerUserOurSell.size(); j++) {
                ExportExcel exportExcel = exportExcelListPartnerUserOurSell.get(j);
                row = sheetThree.createRow(j + 1);
                //创建单元格并且添加数据
                cell = row.createCell(0);
                cell.setCellStyle(style);
                if(exportExcel.getApiTypeName_stidName() != null){
                    cell.setCellValue(exportExcel.getApiTypeName_stidName());
                }
                cell = row.createCell(1);
                cell.setCellStyle(style);
                if (exportExcel.getVendorName() != null) {
                    cell.setCellValue(exportExcel.getVendorName());
                }
                cell = row.createCell(2);
                cell.setCellStyle(style);
                if (exportExcel.getCost() != null) {
                    cell.setCellValue(exportExcel.getCost());
                }
                cell = row.createCell(3);
                cell.setCellStyle(style);
                if (exportExcel.getSumCount() != null) {
                    cell.setCellValue(exportExcel.getSumCount());
                }
                cell = row.createCell(4);
                cell.setCellStyle(style);
                if (exportExcel.getSumCount() != null) {
                    cell.setCellFormula("product(C"+(j+2)+",D"+(j+2)+")");
                }
                cell = row.createCell(5);
                cell.setCellStyle(style);
                if (exportExcel.getConsuTime() != null) {
                    cell.setCellValue(exportExcel.getConsuTime());
                }
            }
            row = sheetThree.createRow(exportExcelListPartnerUserOurSell.size() + 1);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue("总计");

            cell = row.createCell(1);
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellStyle(style);
            cell.setCellFormula("sum(E2:E"+(exportExcelListPartnerUserOurSell.size() + 1)+")");

            cell = row.createCell(5);
            cell.setCellStyle(style);
        }
        return wb;
    }

}
