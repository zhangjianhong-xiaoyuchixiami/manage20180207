package org.qydata.mapper.mapper1;

import org.qydata.entity.CustomerDept;

/**
 * Created by jonhn on 2016/11/22.
 */
public interface CustomerDeptMapper {
    /**真实/测试
     * 向CustomerDept表中添加一条数据，实现部门表和客户表的映射
     * @param vo 包含了要添加的数据
     * @return 添加成功返回true，否则返回false
     */
    public boolean insertCustomerDept(CustomerDept vo)throws Exception;

}
