package org.qydata.mapper;

import org.qydata.entity.Dept;
import org.qydata.entity.UserDept;

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
     * 根据用户Id删除用户和部门映射
     * @return
     * @throws Exception
     */
    public boolean deleteUserDeptByUserId(Integer userId)throws Exception;

    /**
     * 批量插入用户和部门映射
     * @param map
     * @return
     * @throws Exception
     */
    public boolean insertUserDept(Map<String,Object> map)throws Exception;

    /**
     *
     * @param map
     * @return
     * @throws Exception
     */
    public List<Dept> findAll(Map<String,Object> map)throws Exception;

    /**
     *
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getAllCount(Map<String,Object> map)throws Exception;

    /**
     * 根据指定信息添加用户部门映射
     * @param userDept
     * @return
     * @throws Exception
     */
    public boolean addUserDeptById(UserDept userDept)throws Exception;





}
