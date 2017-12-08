package org.qydata.config.properties;

public class UrlProperties {


    private static String url;

    private static String burl;

    private static String authId;

    private static String authPass;


    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        UrlProperties.url = url;
    }

    public static String getBurl() {
        return burl;
    }

    public static void setBurl(String burl) {
        UrlProperties.burl = burl;
    }

    public static String getAuthId() {
        return authId;
    }

    public static void setAuthId(String authId) {
        UrlProperties.authId = authId;
    }

    public static String getAuthPass() {
        return authPass;
    }

    public static void setAuthPass(String authPass) {
        UrlProperties.authPass = authPass;
    }
}
