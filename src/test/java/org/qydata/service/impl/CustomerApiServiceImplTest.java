//package org.qydata.service.impl;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.qydata.service.CustomerApiService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by jonhn on 2016/12/14.
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CustomerApiServiceImplTest {
//
//
//    @Autowired
//    private CustomerApiService customerApiService;
//    @Test
//    public void findAllApi() throws Exception {
//        Map<String,Object> map = new HashMap<>();
//        map.put("customerId",12);
//        System.out.println(customerApiService.findAllApi(map));
//    }
//    @Test
//    public void findById() throws Exception {
//        System.out.println(customerApiService.findById(58));
//    }
//    @Test
//    public void insertCustomerApi() throws Exception {
//        System.out.println(customerApiService.insertCustomerApi("20","162","2","true"));
//    }
//    @Test
//    public void updateCustomerApiById() throws Exception {
//        System.out.println(customerApiService.updateCustomerApiById("73","355","1","true"));
//    }
//
////    @Test
////    public void apiList() throws Exception {
////       List<Api> list = customerApiService.apiList();
////       for (int i=0;i<list.size();i++){
////           System.out.println(list.get(i));
////       }
////    }
////    @Test
////    public void apiList1() throws Exception {
////        List<Api> list = customerApiService.apiList1();
////        for (int i=0;i<list.size();i++){
////            System.out.println(list.get(i));
////        }
////    }
//
//}