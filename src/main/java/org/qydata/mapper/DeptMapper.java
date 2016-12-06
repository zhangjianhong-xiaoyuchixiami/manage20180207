package org.qydata.mapper;

import org.qydata.entity.Dept;

import java.util.List;
import java.util.Map;

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

    /**
     * 批量插入用户和部门映射
     * @param map
     * @return
     * @throws Exception
     */
    public boolean insertUserDept(Map<String,Object> map)throws Exception;
}
