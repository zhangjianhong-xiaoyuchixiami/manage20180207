package org.qydata.util;

import org.qydata.tools.IpTool;

import java.util.List;

/**
 * Created by jonhn on 2017/1/6.
 */
public class Test {
    public static void main(String[] args) {
        List<String> strList = IpTool.spiltStr("2016-10-27");
        for (int i=0;i<strList.size();i++){
            System.out.println(strList.get(i));
        }
    }
}
