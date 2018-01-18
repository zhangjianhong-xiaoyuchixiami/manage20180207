package org.qydata.service.impl;

import com.google.gson.Gson;
import org.qydata.dst.api.Aid;
import org.qydata.dst.valid.ReqParam;
import org.qydata.dst.valid.ValidResult;
import org.qydata.entity.ApiType;
import org.qydata.entity.ApiVendor;
import org.qydata.mapper.mapper1.ValidMapper;
import org.qydata.mapper.mapper2.ValidSelectMapper;
import org.qydata.service.ValidService;
import org.qydata.tools.RegexUtil;
import org.qydata.tools.customer.ApiTypeList;
import org.qydata.tools.customer.OperatorList;
import org.qydata.tools.customer.RequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/19.
 */
@Service
public class ValidServiceImpl implements ValidService {

    @Autowired
    private ValidMapper mapper;

    @Autowired
    private ValidSelectMapper selectMapper;

    @Autowired RequestData requestData;

    @Override
    public List<Aid> queryAidByTid(String [] tid) {
        List<Integer> typeList = new ArrayList<>();
        if (tid == null) {
            return null;
        }
        for (String s : tid) {
            typeList.add(Integer.valueOf(s));
        }
        Map<String,Object> map = new HashMap<>();
        map.put("typeList",typeList);
        List<Aid> aidList = selectMapper.queryAidByTid(map);
        if (aidList !=  null){
            for (Aid aid : aidList) {
                aid.setName( "(aid:" + aid.getAid() + ")" + aid.getName());
            }
        }
        return aidList;
    }

    @Override
    public List<ValidResult> queryValidResult(String[] tid, String[] aid, String[] mob, String[] id, String[] name, String[] bankId, String omit, String skip, String address) {
        List<ReqParam> paramList = new ArrayList<>();
        if (tid != null){
            for (String tid_1 : tid) {
                if (!RegexUtil.isNull(tid_1)) {
                    for (int i = 0; i < mob.length; i++) {
                        if (aid == null || aid.length <= 0) {
                            ReqParam param = ApiTypeList.packageReqParam(tid_1,omit,skip,address,mob[i],id[i],name[i],bankId[i]);
                            if (param != null){
                                paramList.add(param);
                            }
                        } else {
                            int aidCount = 0;
                            for (String aid_1 : aid) {
                                if (!RegexUtil.isNull(aid_1)) {
                                    ReqParam param = ApiTypeList.packageReqParam(tid_1,omit,skip,address,mob[i],id[i],name[i],bankId[i]);
                                    if (param != null){
                                        boolean flag = queryAidByTypeIdAndStidAndAid(Integer.valueOf(tid_1), param.stid, Integer.valueOf(aid_1));
                                        if (flag) {
                                            aidCount ++;
                                            param.aid = Integer.valueOf(aid_1);
                                            paramList.add(param);
                                        }
                                    }
                                }
                            }
                            if (aidCount == 0){
                                ReqParam param = ApiTypeList.packageReqParam(tid_1,omit,skip,address,mob[i],id[i],name[i],bankId[i]);
                                if (param != null){
                                    paramList.add(param);
                                }
                            }
                        }
                    }
                }
            }
        }
        for (ReqParam param : paramList) {
            System.out.println(new Gson().toJson(param));
        }
        return requestData.result(paramList);
    }



    @Override
    public boolean queryAidByTypeIdAndStidAndAid(Integer tid, Integer stid, Integer aid) {
        Integer result = selectMapper.queryAidByTypeIdAndStidAndAid(tid,stid,aid);
        if (result == null) {
            return false;
        }
        return true;
    }

    @Override
    public String queryApiTypeByTypeIdAndStid(Integer tid, Integer stid) {
        return selectMapper.queryApiTypeByTypeIdAndStid(tid,stid);
    }

    @Override
    public String queryApiVendorByAid(Integer aid) {
        ApiVendor apiVendor = selectMapper.queryApiVendorByAid(aid);
        if (apiVendor != null){
            if (apiVendor.getName() != null){
                apiVendor.setName("(apiId:" + apiVendor.getId() + ")" + apiVendor.getName());
                return apiVendor.getName();
            }
        }
        return null;
    }
}
