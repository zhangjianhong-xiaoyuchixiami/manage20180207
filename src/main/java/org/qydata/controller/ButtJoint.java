package org.qydata.controller;

import org.qydata.entity.Customer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by jonhn on 2017/3/31.
 */
public interface ButtJoint {

/*    @RequestMapping("customer/add-package")
    public List<Customer> customerAddPackage(@RequestParam("name")String name, @RequestParam("key")String key, @RequestParam(value = "ats", defaultValue = "") String apiTypes, @RequestParam(value = "ips", defaultValue = "") String ips)

    例如/admin/customer/add-package?name=千眼内部测试2&key=qydata2&ats=5-1:50&ips=xxx&k=xxx*/


    //添加公司
    @Transactional
    @RequestMapping("company/add")
    public List<Customer> companyAdd(@RequestParam("name")String name, @RequestParam("key")String key);

    //禁用账号
    @RequestMapping("customer/ban")
    public void customerBan(@RequestParam("authid") String authid);

    //解禁账号
    @RequestMapping("customer/unban")
    public void customerUnban(@RequestParam("authid") String authid);

    //禁用公司
    @RequestMapping("company/ban")
    public void companyBan(@RequestParam("cid") Integer companyId);

    //解禁公司
    @RequestMapping("company/unban")
    public void companyUnban(@RequestParam("cid") Integer companyId);

    //添加Ip
    @RequestMapping("customer/ip/add")
    public void customerIpAdd(@RequestParam("cid")int customerId, @RequestParam("bip")String beginIp, @RequestParam("eip")String endIp);

    //删除Ip
    @RequestMapping("customer/ip/del")
    public void customerIpDel(@RequestParam("cid")int customerId, @RequestParam("id")int id);

    //分配产品权限
    @RequestMapping("company/api/put")
    public void companyApiPut(@RequestParam("cid")int cid, @RequestParam("tid")int tid, @RequestParam(value = "stid", defaultValue = "0") Integer stid, @RequestParam("price")int price);

    //禁用产品权限
    @RequestMapping("company/api/del")
    public void companyApiDel(@RequestParam("cid")int cid, @RequestParam("id")int id);

    //账号充值
    @RequestMapping("customer/balance/charge")
    public void customerCharge(@RequestParam("rid")int reasonId, @RequestParam("cid")int customerId, @RequestParam("amount")int amount);


}
