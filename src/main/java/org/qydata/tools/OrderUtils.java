package org.qydata.tools;

import org.qydata.dst.OperaCondition;
import org.springframework.stereotype.Component;

import java.util.Comparator;
public class OrderUtils implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        OperaCondition operaCondition1 = (OperaCondition)o1;
        OperaCondition operaCondition2 = (OperaCondition)o2;
        int i = operaCondition1.getCurrIncomeAccount().compareTo(operaCondition2.getCurrIncomeAccount());
        return -i;
    }

}
