package org.qydata.tools;

import com.google.common.net.InetAddresses;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonhn on 2016/11/24.
 */
public class IpTool {

    public static List<String> spiltStr(String str){
        String [] strChar = str.split(",");
        List<String> list = new ArrayList<String>();
        for (int i=0;i<strChar.length;i++){
            list.add(strChar[i]);
        }
        return list;
    }

    public static List spiltIp(String str){
        String [] strChar = str.split(",");
        List list = new ArrayList();
        for (int i=0;i<strChar.length;i++){
            Long ip = ipToLong(strChar[i]);
            list.add(ip);
        }
        return list;
    }

    public static long ipToLong(String ip) {

        return ipToLong(InetAddresses.forString(ip));
    }
    public static long ipToLong(InetAddress ip) {
        byte[] octets = ip.getAddress();
        long result = 0;
        for (byte octet : octets) {
            result <<= 8;
            result |= octet & 0xff;
        }
        return result;
    }
}
