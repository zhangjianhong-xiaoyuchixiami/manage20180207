package org.qydata.mapper;

import org.qydata.entity.Dept;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */
public interface DeptMapper {

    /**
     * 新增部门
     * @param dept
     * @return
     * @throws Exception
     */
    public boolean insertDept(Dept dept)throws Exception;
    /**
     * 查找所有的部门
     * @return
     * @throws Exception
     */
    public List<Dept> findAllDept()throws Exception;
}
