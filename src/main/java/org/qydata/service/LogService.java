package org.qydata.service;

import org.qydata.entity.log.Log;

/**
 * Created by jonhn on 2017/3/22.
 */
public interface LogService {

    public boolean createLog(Log log);

    public boolean updateLog(Log log);

}
