package org.qydata.util;

import java.util.List;

/**
 * Created by Administrator on 2016/12/10.
 */
public class Test {

    public static void main(String[] args) {

       List list = get();
       for(int i=0;i<list.size();i++){
           System.out.println(list.get(i));
       }
    }

    public static List get(){
        List list = null;
        return list;
    }
}
