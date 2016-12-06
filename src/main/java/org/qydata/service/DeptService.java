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
    /**
     * 批量插入用户和部门映射
     * @param
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean insertUserDept(Integer[] userId,Integer[] deptNo )throws Exception;
}
