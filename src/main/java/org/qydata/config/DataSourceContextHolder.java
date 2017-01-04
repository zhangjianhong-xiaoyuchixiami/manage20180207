package org.qydata.config;

/**
 * Created by Administrator on 2017/1/3.
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /** * 写只有一个库 */
    public static void master() {
        local.set(DataSourceType.master.getType());
    }

    /** * 读可能是多个库 */
    public static void slave() {
        local.set(DataSourceType.slave.getType());
    }

    public static String getJdbcType() {
        return local.get();
    }
}
