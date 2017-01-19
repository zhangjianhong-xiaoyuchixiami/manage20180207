package org.qydata.config;

/**
 * Created by Administrator on 2017/1/3.
 */
public class DataSourceContextHolder {

    //写库对应的数据源key
    private static final String MASTER = "master";

    //读库对应的数据源key
    private static final String SLAVE = "slave";

    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    /**
     * 设置数据源key
     * @param key
     */
    public static void putDataSourceKey(String key) {
        local.set(key);
    }

    /**
     * 获取数据源key
     * @return
     */
    public static String getDataSourceKey() {
        return local.get();
    }

    /**
     * 标记写库
     */
    public static void markMaster(){
        putDataSourceKey(MASTER);
    }

    /**
     * 标记读库
     */
    public static void markSlave(){
        putDataSourceKey(SLAVE);
    }
}
