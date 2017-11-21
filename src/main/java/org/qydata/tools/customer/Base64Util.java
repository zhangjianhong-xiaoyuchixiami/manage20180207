package org.qydata.tools.customer;


import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class Base64Util {


    public static boolean isImageFromBase64(String base64Str) {
        boolean flag = false;
        try {
            BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(base64Str)));
            if (null == bufImg) {
                return flag;
            }
            flag = true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return flag;
    }

}