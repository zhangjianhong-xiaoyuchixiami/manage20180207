package org.qydata.tools;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2016/12/22.
 */
public class Md5Tools {
    public static String md5(String plainText) {
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return  buf.toString().toUpperCase();
    }

}
