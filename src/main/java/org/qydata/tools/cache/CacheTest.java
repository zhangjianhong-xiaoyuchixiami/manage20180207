package org.qydata.tools.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CacheTest {


//    static Cache<Integer, String> cache = CacheBuilder.newBuilder()
//            //设置cache的初始大小为10，要合理设置该值
//            .initialCapacity(2000)
//            //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
//            .concurrencyLevel(100)
//            //设置cache中的数据在写入之后的存活时间为10秒
//            .expireAfterWrite(30, TimeUnit.DAYS)
//            //构建cache实例
//            .build();

//    public static void main(String[] args) throws Exception {
//        cache.put(1,"1");
//        System.out.println(cache.getIfPresent(1));
//        System.out.println(cache.size());
//        cache.put(2,"2");
//        System.out.println(cache.getIfPresent(2));
//        System.out.println(cache.size());
//    }



}
