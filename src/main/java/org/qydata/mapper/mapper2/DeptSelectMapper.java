package org.qydata.mapper.mapper2;

import org.qydata.entity.Dept;
import org.qydata.entity.UserDept;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/3.
 */
public interface DeptSelectMapper {

    /**
     * 查找所有的部门
     * @return
     * @throws Exception
     */
    public List<Dept> findAllDept()throws Exception;

    /**
     *
     * @param map
     * @return
     * @throws Exception
     */
    public List<Dept> findAll(Map<String, Object> map)throws Exception;

    /**
     *
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getAllCount(Map<String, Object> map)throws Exception;

    /**
     * 根据用户Id查找所属部门
     * @param userId
     * @return
     * @throws Exception
     */
    public List<Dept> findDeptByUserId(Integer userId)throws Exception;



}
