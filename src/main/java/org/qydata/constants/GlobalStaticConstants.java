package org.qydata.constants;

import org.qydata.config.properties.UrlProperties;


/**
 * Created by jonhn on 2017/7/28.
 */
public class GlobalStaticConstants {


 public static final String REQUEST_URL = UrlProperties.getUrl();

 //新增客户
 public static final String ADD_COMPANY = REQUEST_URL + "customer/add-package";
 //客户账号禁用
 public static final String CUSTOMER_BAN = REQUEST_URL + "customer/ban";
 //客户账号启用
 public static final String CUSTOMER_UNBAN = REQUEST_URL +  "customer/unban";
 //公司禁用
 public static final String COMPANY_BAN = REQUEST_URL + "company/ban";
 //公司启用
 public static final String COMPANY_UNBAN = REQUEST_URL + "company/unban";
 //客户Ip新增
 public static final String CUSTOMER_ADD_IP = REQUEST_URL + "customer/ip/add";
 //客户Ip删除
 public static final String CUSTOMER_DEL_IP = REQUEST_URL + "customer/ip/del";
 //客户余额充值
 public static final String CUSTOMER_BALANCE_CHARGE = REQUEST_URL + "customer/balance/charge";
 //客户余额扣费
 public static final String CUSTOMER_BALANCE_DEDUCT = REQUEST_URL + "customer/balance/deduct";
 //客户产品权限管理
 public static final String COMPANY_API_PUT = REQUEST_URL + "company/api/put";
 //客户产品权限禁用
 public static final String COMPANY_API_DEL = REQUEST_URL + "company/api/del";
 //客户产品权限启用
 public static final String COMPANY_API_ENABLE = REQUEST_URL + "company/api/enable";
 //修改信用额度
 public static final String CUSTOMER_CREDIT_PUT = REQUEST_URL + "customer/credit/put";
 //产品状态管理
 public static final String API_STATUS = REQUEST_URL + "api/status";
 //产品配额修改
 public static final String API_MODIFY_PROB = REQUEST_URL + "api/modify-prob";
 //产品价格修改
 public static final String API_MODIFY_COST = REQUEST_URL + "api/modify-cost";

}
