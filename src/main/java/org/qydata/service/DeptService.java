package org.qydata.service;

import org.qydata.entity.Dept;
import org.qydata.tools.PageModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
    public List<Dept> findAllDept();
    /**
     * 批量插入用户和部门映射
     * @param
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean insertUserDept(Integer userId,String [] deptId )throws Exception;

    public PageModel<Dept> findAll(Map<String,Object> map)throws Exception;

    /**
     * 根据用户Id查找所属部门
     * @param userId
     * @return
     */
    public List<Dept> findDeptByUserId(Integer userId);

}
