package org.qydata.config;

import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2017/1/3.
 */
public class DataSourceContextHolder {

    private final Logger log = Logger.getLogger(this.getClass());

    //写库对应的数据源key
    private static final String MASTER = "master";

    //读库对应的数据源key
    private static final String SLAVE = "slave";

    //数据源线程池
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
        System.out.println("---------------获取数据源key---------------"+local.get());
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
