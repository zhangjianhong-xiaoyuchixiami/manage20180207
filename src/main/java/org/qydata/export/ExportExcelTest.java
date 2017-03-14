package org.qydata.export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jonhn on 2017/3/14.
 */
public class ExportExcelTest {

    public static void main(String[] args) throws FileNotFoundException {
        List<WebDto> list = new ArrayList<WebDto>();
        list.add(new WebDto("知识林", "http://www.zslin.com", "admin", "111111", 555));
        list.add(new WebDto("权限系统", "http://basic.zslin.com", "admin", "111111", 111));
        list.add(new WebDto("校园网", "http://school.zslin.com", "admin", "222222", 333));

        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "网站信息表");
        map.put("total", list.size()+" 条");
        map.put("date", getDate());

        ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, "web-info-template.xls", new FileOutputStream("E:/temp/out.xls"),
                list, WebDto.class, true);
    }

    private static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(new Date());
    }

}
