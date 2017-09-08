package org.qydata.tools.finance;

import org.qydata.dst.ApiTurnoverBill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonhn on 2017/9/6.
 */
public class ApiTurnoverBillUtils {


    public static List<ApiTurnoverBill> packageBill(List<ApiTurnoverBill> customerBillList,List<ApiTurnoverBill> vendorBillList){

        if (customerBillList == null && vendorBillList == null){
            return null;
        }
        if (customerBillList == null && vendorBillList != null){
            for (ApiTurnoverBill vendorBill : vendorBillList) {
                if (vendorBill.getVendorConsume() != null){
                    vendorBill.setVendorConsume(vendorBill.getVendorConsume()/100.0);
                }else {
                    vendorBill.setVendorConsume(0.0);
                }
                if (vendorBill.getVendorAmount() == null){
                    vendorBill.setVendorAmount(0);
                }
                if (vendorBill.getCustomerConsume() == null){
                    vendorBill.setCustomerConsume(0.0);
                }
                if (vendorBill.getCustomerAmount() == null){
                    vendorBill.setCustomerAmount(0);
                }
                if (vendorBill.getVendorAmount() - vendorBill.getCustomerAmount() > 0){
                    vendorBill.setStatus(0);//0代表异常
                }else {
                    vendorBill.setStatus(1);//1代表正常
                }
                vendorBill.setProfit(vendorBill.getCustomerConsume() - vendorBill.getVendorConsume());
            }
            return vendorBillList;
        }
        if (customerBillList != null && vendorBillList == null){
            for (ApiTurnoverBill customerBill : customerBillList) {
                if (customerBill.getCustomerConsume() != null){
                    customerBill.setCustomerConsume(customerBill.getCustomerConsume()/100.0);
                }else {
                    customerBill.setCustomerConsume(0.0);
                }
                if (customerBill.getCustomerAmount() == null){
                    customerBill.setCustomerAmount(0);
                }
                if (customerBill.getVendorConsume() == null){
                    customerBill.setVendorConsume(0.0);
                }
                if (customerBill.getVendorAmount() == null){
                    customerBill.setVendorAmount(0);
                }
                if (customerBill.getVendorAmount() - customerBill.getCustomerAmount() > 0){
                    customerBill.setStatus(0);//0代表异常
                }else {
                    customerBill.setStatus(1);//1代表正常
                }
                customerBill.setProfit(customerBill.getCustomerConsume() - customerBill.getVendorConsume());
            }
            return customerBillList;
        }
        List list = new ArrayList();
        for (ApiTurnoverBill customerBill : customerBillList) {
            list.add(customerBill.getType_stid_name());
        }
        for (ApiTurnoverBill vendorBill : vendorBillList) {
            if (!list.contains(vendorBill.getType_stid_name())){
                customerBillList.add(vendorBill);
            }
        }
        for (ApiTurnoverBill customerBill : customerBillList) {
            if (customerBill.getCustomerConsume() != null){
                customerBill.setCustomerConsume(customerBill.getCustomerConsume()/100.0);
            }else {
                customerBill.setCustomerConsume(0.0);
            }
            if (vendorBillList != null){
                for (ApiTurnoverBill vendorBill : vendorBillList) {
                    if (customerBill.getApiTypeId() != null && customerBill.getStid() != null){
                        if (customerBill.getApiTypeId() == vendorBill.getApiTypeId() || customerBill.getApiTypeId().equals(vendorBill.getApiTypeId())){
                            if (customerBill.getStid() == vendorBill.getStid() || customerBill.getStid().equals(vendorBill.getStid())){
                                customerBill.setVendorAmount(vendorBill.getVendorAmount());
                                customerBill.setVendorConsume(vendorBill.getVendorConsume()/100.0);
                            }
                        }
                    }
                }
            }
            if (customerBill.getCustomerAmount() == null){
                customerBill.setCustomerAmount(0);
            }
            if (customerBill.getVendorConsume() == null){
                customerBill.setVendorConsume(0.0);
            }
            if (customerBill.getVendorAmount() == null){
                customerBill.setVendorAmount(0);
            }
            if (customerBill.getVendorAmount() - customerBill.getCustomerAmount() > 0){
                customerBill.setStatus(0);//0代表异常
            }else {
                customerBill.setStatus(1);//1代表正常
            }
            customerBill.setProfit(customerBill.getCustomerConsume() - customerBill.getVendorConsume());
        }
        return customerBillList;
    }


    public static List<ApiTurnoverBill> packageBillType(List<ApiTurnoverBill> customerTypeList,List<ApiTurnoverBill> vendorTypeList){

        if (customerTypeList == null && vendorTypeList == null){
            return null;
        }
        if (customerTypeList == null && vendorTypeList != null){
            return vendorTypeList;
        }
        if (customerTypeList != null && vendorTypeList == null){
            return customerTypeList;
        }
        List list = new ArrayList();
        for (ApiTurnoverBill customerBill : customerTypeList) {
            list.add(customerBill.getType_stid_name());
        }
        for (ApiTurnoverBill vendorBill : vendorTypeList) {
            if (!list.contains(vendorBill.getType_stid_name())){
                customerTypeList.add(vendorBill);
            }
        }
        return customerTypeList;
    }
}
