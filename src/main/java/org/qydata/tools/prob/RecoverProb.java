package org.qydata.tools.prob;

import org.qydata.constants.GlobalStaticConstants;
import org.qydata.entity.ApiExt;
import org.qydata.entity.ApiResponseLog;
import org.qydata.entity.RecoverProbLog;
import org.qydata.mapper.ApiMapper;
import org.qydata.mapper.CompanyMapper;
import org.qydata.tools.https.HttpClientUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by jonhn on 2017/7/20.
 */
public class RecoverProb {

    private ApiMapper apiMapper;
    private CompanyMapper companyMapper;

    public RecoverProb(ApiMapper apiMapper,CompanyMapper companyMapper){
        this.apiMapper = apiMapper;
        this.companyMapper = companyMapper;
    }

    public boolean isMeetTerm(Integer aid,String time){
        int restTimeCount = 0;
        int averRestTime = 5001;
        int failCount = 0;
        for (int count = 0; count < 5; ) {
            List<ApiResponseLog> apiResponseLogList = null;
            try {
                apiResponseLogList = apiMapper.queryApiRequestLog(aid, time);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (apiResponseLogList != null && apiResponseLogList.size() > 0) {
                for (int i = 0; i < apiResponseLogList.size(); i++) {
                    ApiResponseLog apiResponseLog = apiResponseLogList.get(i);
                    if (apiResponseLog != null) {
                        if (apiResponseLog.getResTime() != null){
                            restTimeCount += apiResponseLog.getResTime();
                        }
                        if (apiResponseLog.getOk() == 0) {
                            failCount++;
                        }
                    }
                }
                count = apiResponseLogList.size();
                if (count != 0){
                    averRestTime = (restTimeCount) / count;
                }
            }
        }
        if (averRestTime <= 5000 && failCount <= 1) {
            return true;
        }
        return false;
    }

    public int updateProb(Integer aid,Integer taid,Integer typeId){
        if (typeId == null){
            typeId = 1;
        }
        try {
            //修改通道配额
            String value = companyMapper.queryAuthKey("admin.k");
            ApiExt apiExt = apiMapper.queryDefProbDefPropByApiId(aid);
            ApiExt apiExtTeam = apiMapper.queryDefProbDefPropByApiId(taid);
            if (apiExt != null && apiExtTeam != null) {
                if (typeId == 1){
                    int prob =(int) (apiExt.getDefProb() * (apiExt.getDefProp() / 100.0));
                    int code = updateProb(value, aid, prob);
                    int probTeam =(int) (apiExtTeam.getDefProb() * (apiExt.getDefProp() / 10.0));
                    int codeTeam = updateProb(value, taid, probTeam);
                    if (code == 200 && codeTeam == 200){
                        return 200;
                    }
                }
                if (typeId == 2){
                    int prob = apiExt.getDefProb();
                    int code = updateProb(value, aid, prob);
                    int probTeam = apiExtTeam.getDefProb();
                    int codeTeam = updateProb(value, taid, probTeam);
                    if (code == 200 && codeTeam == 200){
                        return 200;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateApiStatus(Integer aid,Integer sid){
        String value = companyMapper.queryAuthKey("admin.k");
        int code = updateApiStatus(value,aid,sid);
        return code;
    }

    public void updateRunLog(Integer id, Integer tApiId, Date phaseOneBeginTime,Date phaseOneEndTime,Date phaseTwoBeginTime,Date phaseTwoEndTime,Integer ok,Integer statusCode){
        try {
            RecoverProbLog recoverProbLog = new RecoverProbLog();
            recoverProbLog.setId(id);
            recoverProbLog.settApiId(tApiId);
            recoverProbLog.setPhaseOneBeginTime(phaseOneBeginTime);
            recoverProbLog.setPhaseOneEndTime(phaseOneEndTime);
            recoverProbLog.setPhaseTwoBeginTime(phaseTwoBeginTime);
            recoverProbLog.setPhaseTwoEndTime(phaseTwoEndTime);
            recoverProbLog.setOk(ok);
            recoverProbLog.setStatusCode(statusCode);
            apiMapper.updateRecoverProbLog(recoverProbLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int addRunLog(Integer aid,Integer ok){
        try {
            RecoverProbLog recoverProbLog = new RecoverProbLog();
            recoverProbLog.setrApiId(aid);
            recoverProbLog.setOk(ok);
            apiMapper.addRecoverProbLog(recoverProbLog);
            return recoverProbLog.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int updateProb(String value,Integer aid,Integer prob){
        String uri = GlobalStaticConstants.API_MODIFY_PROB;
        Map<String,Object> mapParam = new HashMap<>();
        mapParam.put("k",value);
        mapParam.put("aid",aid);
        mapParam.put("v",prob);
        int code = HttpClientUtil.doGet(uri,mapParam,null);
        return code;
    }

    private int updateApiStatus(String value,Integer aid,Integer sid){
        String uri = GlobalStaticConstants.API_STATUS;
        Map<String,Object> map = new HashMap<>();
        map.put("k",value);
        map.put("aid",aid);
        map.put("s",sid);
        int code = HttpClientUtil.doGet(uri,map,null);
        return code;
    }


}
