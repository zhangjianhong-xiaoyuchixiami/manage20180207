package org.qydata.service.impl;

import org.qydata.dst.api.Aid;
import org.qydata.mapper.mapper1.ValidMapper;
import org.qydata.mapper.mapper2.ValidSelectMapper;
import org.qydata.service.ValidService;
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
    @Override
    public List<Aid> queryAidByUrl(String url) {
        List<Integer> typeList = new ArrayList<>();
        if (url != null && url != ""){
            if (url.trim().equals("/mobile/verify/3f")){
                typeList.add(1);
                typeList.add(21);
            }
            if (url.trim().equals("/mobile/query/duration")){
                typeList.add(4);
            }
            if (url.trim().equals("/mobile/query/status")){
                typeList.add(5);
            }
            if (url.trim().equals("/mobile/verify/2f-name")){
                typeList.add(20);
            }
            if (url.trim().equals("/id/verify/2f")){
                typeList.add(2);
                typeList.add(15);
            }
            if (url.trim().equals("/id/query/photo")){
                typeList.add(18);
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("typeList",typeList);
        List<Aid> aidList = selectMapper.queryAidByUrl(map);
        return aidList;
    }
}
