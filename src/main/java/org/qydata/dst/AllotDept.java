package org.qydata.dst;

import org.qydata.entity.Dept;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2016/12/7.
 */
public class AllotDept implements Serializable {

    private List<Dept> dept;
    private List<Integer> userDeptNoList;

    public List<Dept> getDept() {
        return dept;
    }

    public void setDept(List<Dept> dept) {
        this.dept = dept;
    }

    public List<Integer> getUserDeptNoList() {
        return userDeptNoList;
    }

    public void setUserDeptNoList(List<Integer> userDeptNoList) {
        this.userDeptNoList = userDeptNoList;
    }
}
