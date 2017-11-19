package org.qydata.service;

import org.qydata.dst.api.Aid;

import java.util.List;

/**
 * Created by Administrator on 2017/11/19.
 */
public interface ValidService {

    public List<Aid> queryAidByUrl(String url);

}
