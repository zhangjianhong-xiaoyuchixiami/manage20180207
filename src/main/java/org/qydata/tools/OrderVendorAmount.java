package org.qydata.tools;

import org.qydata.dst.VendorCost;

import java.util.Comparator;

public class OrderVendorAmount implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        VendorCost vendorCost1 = (VendorCost)o1;
        VendorCost vendorCost2 = (VendorCost)o2;
        int i = vendorCost1.getAmount().compareTo(vendorCost2.getAmount());
        return -i;
    }
}
