package org.qydata.mapper.mapper2;

import org.qydata.dst.api.Aid;
import org.qydata.entity.ApiVendor;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/19.
 */
public interface ValidSelectMapper {

    /**
     * 根据请求地址查询上游
     * @param map
     * @return
     */
    public List<Aid> queryAidByTid(Map<String, Object> map);


    /**
     * 根据请求地址和通道编号查询该通道是否存在
     * @param tid
     * @param stid
     * @param aid
     * @return
     */
    public Integer queryAidByTypeIdAndStidAndAid(Integer tid,Integer stid,Integer aid);

    /**
     * 根据类型id和子类型id查找类型名称
     * @param tid
     * @param stid
     * @return
     */
    public String queryApiTypeByTypeIdAndStid(Integer tid,Integer stid);

    /**
     * 根据ApiId查询供应商
     * @param aid
     * @return
     */
    public ApiVendor queryApiVendorByAid(Integer aid);

}
