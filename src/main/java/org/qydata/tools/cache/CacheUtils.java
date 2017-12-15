package org.qydata.tools.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public class CacheUtils {

   final static Cache<String, Double> cache = CacheBuilder.newBuilder()
            //设置cache的初始大小为10，要合理设置该值
            .initialCapacity(2000)
            //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
            .concurrencyLevel(100)
            //设置cache中的数据在写入之后的存活时间为10秒
            .expireAfterWrite(30, TimeUnit.DAYS)
            //构建cache实例
            .build();

    public static Cache<String, Double> getCache(){
        return cache;
    }

//    static Cache<String, Integer> cache = CacheBuilder.newBuilder()
//            //设置cache的初始大小为10，要合理设置该值
//            .initialCapacity(2000)
//            //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
//            .concurrencyLevel(100)
//            //设置cache中的数据在写入之后的存活时间为10秒
//            .expireAfterWrite(30, TimeUnit.DAYS)
//            //构建cache实例
//            .build();

}
