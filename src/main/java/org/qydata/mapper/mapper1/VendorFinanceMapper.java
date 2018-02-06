package org.qydata.mapper.mapper1;

import org.qydata.dst.vendor.VendorFinance;
import org.qydata.entity.ApiVendor;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/12.
 */
public interface VendorFinanceMapper {

    /**
     * 修改比率
     * @param vid
     * @param rate
     * @return
     */
    public int updateRate(Integer vid, Integer rate) ;

    /**
     * 新增比率
     * @param vid
     * @param rate
     * @return
     */
    public int insertRate(Integer vid, Integer rate) ;

}
