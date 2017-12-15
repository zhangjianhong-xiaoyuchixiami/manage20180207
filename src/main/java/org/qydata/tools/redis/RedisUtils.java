//package org.qydata.tools.redis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Client;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//@Component
//public class RedisUtils {
//    @Autowired
//    JedisPool jedisPool;
//
//    // 获取redis 服务器信息
//    public String getRedisInfo() {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.auth("qydata");
//            Client client = jedis.getClient();
//            client.info();
//            String info = client.getBulkReply();
//            return info;
//        } finally {
//            // 关闭连接
//            jedis.close();
//        }
//    }
//
//    // 获取占用内存大小
//    public Long dbSize() {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            Client client = jedis.getClient();
//            client.dbSize();
//            return client.getIntegerReply();
//        } finally {
//            // 关闭连接
//            jedis.close();
//        }
//    }
//
//    /**
//     * 添加客户产品对应价格
//     * @param customerId
//     * @param apiTypeId
//     * @param Price
//     */
//    public Long addApiPrice(String customerId, String apiTypeId, String Price){
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.auth("qydata");
//            Long hset = jedis.hset("apiTypePrice" + ":" + customerId, apiTypeId, Price);
//            jedis.expire("apiTypePrice" + ":" + customerId, 3600*24*7);
//            return hset;
//        }finally {
//            //关闭连接
//            jedis.close();
//        }
//    }
//
//    /**
//     * 查询客户产品对应的价格
//     * @param customerId
//     * @param apiTypeId
//     * @return
//     */
//    public String getApiPrice(String customerId, String apiTypeId){
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.auth("qydata");
//            String hget = jedis.hget("apiTypePrice" + ":" + customerId, apiTypeId);
//            return  hget;
//        } finally {
//            //关闭连接
//            jedis.close();
//        }
//    }
//
//    /**
//     * 删除
//     * @param customerId
//     * @param apiTypeId
//     * @return
//     */
//    public Long deleteApiPrice(String customerId, String apiTypeId){
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.auth("qydata");
//            Long hdel = jedis.hdel("apiTypePrice" + ":" + customerId, apiTypeId);
//            System.out.println(hdel);
//            return hdel;
//        } finally {
//            //关闭连接
//            jedis.close();
//        }
//    }
//
//}
