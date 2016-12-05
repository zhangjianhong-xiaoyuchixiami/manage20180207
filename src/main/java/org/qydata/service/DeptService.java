package org.qydata.service;

import org.qydata.entity.Dept;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */
public interface DeptService {
    /**
     * 新增部门
     * @param dept
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean addDept(Dept dept)throws Exception;

    /**
     * 查找所有的部门
     * @return
     * @throws Exception
     */
    public List<Dept> findAllDept()throws Exception;
}
