package org.qydata.service;

import org.qydata.dst.api.Aid;
import org.qydata.dst.valid.ValidResult;
import org.qydata.entity.ApiType;
import org.qydata.entity.ApiVendor;

import java.util.List;

/**
 * Created by Administrator on 2017/11/19.
 */
public interface ValidService {

    /**
     * 根据请求地址查询该地址所有通道
     * @param tid
     * @return
     */
    public List<Aid> queryAidByTid(String [] tid);

    /**
     * 核验数据
     * @param tid
     * @param aid
     * @param mob
     * @param id
     * @param name
     * @param bankId
     * @param omit
     * @param skip
     * @param address
     * @return
     */
    public List<ValidResult> queryValidResult(String [] tid, String [] aid, String [] mob, String [] id, String [] name, String [] bankId, String omit, String skip, String address);

    /**
     * 根据请求地址和通道编号查询该通道是否存在
     * @param tid
     * @param stid
     * @param aid
     * @return
     */
    public boolean queryAidByTypeIdAndStidAndAid(Integer tid,Integer stid,Integer aid);

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
    public String queryApiVendorByAid(Integer aid);

}
