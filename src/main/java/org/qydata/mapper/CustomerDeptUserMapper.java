package org.qydata.mapper;

import org.qydata.entity.CustomerDeptUser;

/**
 * Created by jonhn on 2016/11/22.
 */
public interface CustomerDeptUserMapper {
    /**真实/测试
     * 向CustomerDept表中添加一条数据，实现部门表和客户表的映射
     * @param vo 包含了要添加的数据
     * @return 添加成功返回true，否则返回false
     */
    public boolean insertCustomerDeptUser(CustomerDeptUser vo)throws Exception;

}
