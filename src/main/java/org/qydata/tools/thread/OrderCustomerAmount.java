package org.qydata.tools.thread;

import org.qydata.dst.CustomerIncome;

import java.util.Comparator;

public class OrderCustomerAmount implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        CustomerIncome customerIncome1 = (CustomerIncome)o1;
        CustomerIncome customerIncome2 = (CustomerIncome)o2;
        int i = customerIncome1.getAmount().compareTo(customerIncome2.getAmount());
        return -i;
    }
}
