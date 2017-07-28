package org.qydata.tools.thread;

import org.qydata.entity.Api;
import org.qydata.entity.RecoverProbCheck;
import org.qydata.mapper.ApiMapper;
import org.qydata.mapper.CompanyMapper;
import org.qydata.tools.prob.RecoverProb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jonhn on 2017/7/19.
 */
public class RecoverApiProbThread implements Runnable {

    private Integer aid;
    private ApiMapper apiMapper;
    private CompanyMapper companyMapper;
    public RecoverApiProbThread(Integer aid, ApiMapper apiMapper,CompanyMapper companyMapper) {
        this.aid = aid;
        this.apiMapper = apiMapper;
        this.companyMapper = companyMapper;
    }

    @Override
    public void run() {
        RecoverProb recoverProb = new RecoverProb(apiMapper,companyMapper);
        //插入恢复日志
        Integer recoverProbId = recoverProb.addRunLog(aid,0);
        //获取起始时间
        Date oneBeginDate = new Date();
        String oneBegTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(oneBeginDate);
        Integer tid = 0;
        try{
            Integer apiStatus = apiMapper.queryApiStatusByApiId(aid);
            if (apiStatus != null) {
                //将状态修改为启用
                int updateSuccCode = recoverProb.updateApiStatus(aid, 0);
                if (200 == updateSuccCode){
                    //查询要恢复通道的产品类型Id
                    tid = apiMapper.queryApiTypeByApiId(aid);
                    //获取除了要恢复的产品同一类型并且状态启用的通道数是多少
                    List<Api> apiList = apiMapper.getCountUnifiedTypeNorStatusOther(aid, tid);
                    if (apiList != null && apiList.size() >= 0) {
                        //除了要恢复的产品筛选出统一类型价格最低做协作通道
                        Api apiTeam = apiMapper.getUnifiedTypeNorStatusOtherLowCost(aid, tid);
                        //修改配额第一阶段进行中
                        recoverProb.updateRunLog(recoverProbId,apiTeam.getId(),oneBeginDate,null,null,null,0,401);
                        if (recoverProb.isMeetTerm(aid,oneBegTime)) {
                            //修改通道配额
                            int code = recoverProb.updateProb(aid,apiTeam.getId(),1);
                            //修改协作通道配额
                            Date oneEndDate = new Date();
                            if (200 == code) {
                                //恢复成功，修改配额第一阶段完成
                                recoverProb.updateRunLog(recoverProbId,apiTeam.getId(),oneBeginDate,oneEndDate,null,null,1,503);
                            } else {
                                //恢复失败，接口请求异常
                                recoverProb.updateRunLog(recoverProbId,apiTeam.getId(),oneBeginDate,oneEndDate,null,null,0,-502);
                            }
                            Integer lowCostAid = apiMapper.getCurrApiIsLowCost(tid);
                            if ( aid == lowCostAid){
                                Thread.sleep(1000);
                                Date endDate = new Date();
                                String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDate);
                                //修改配额第二阶段进行中
                                recoverProb.updateRunLog(recoverProbId,apiTeam.getId(),oneBeginDate,oneEndDate,endDate,null,1,402);
                                if (recoverProb.isMeetTerm(aid,endTime)) {
                                    //修改通道配额
                                    int codeTwo = recoverProb.updateProb(aid,apiTeam.getId(),2);
                                    Date twoEndDate = new Date();
                                    if (200 == codeTwo) {
                                        //恢复成功，修改配额第二阶段完成
                                        recoverProb.updateRunLog(recoverProbId,apiTeam.getId(),oneBeginDate,oneEndDate,endDate,twoEndDate,1,504);
                                    } else {
                                        //恢复失败，接口请求异常
                                        recoverProb.updateRunLog(recoverProbId,apiTeam.getId(),oneBeginDate,oneEndDate,endDate,twoEndDate,0,-502);
                                    }
                                }else {
                                    //修改通道配额
                                    int codeBack = recoverProb.updateProb(aid,apiTeam.getId(),1);
                                    if (200 == codeBack){
                                        //恢复成功，修改配额第二阶段完成，修改配额第二阶段未满足恢复条件，已将配额修改为第一阶段配额
                                        recoverProb.updateRunLog(recoverProbId,apiTeam.getId(),oneBeginDate,oneEndDate,endDate,new Date(),1,505);
                                    }else {
                                        //恢复失败，接口请求异常
                                        recoverProb.updateRunLog(recoverProbId,apiTeam.getId(),oneBeginDate,oneEndDate,endDate,new Date(),0,-502);
                                    }
                                }
                            }else {
                                //恢复成功，因为通道价格不是同一类型最低价，不执行修改配额第二阶段，恢复完成
                                recoverProb.updateRunLog(recoverProbId,apiTeam.getId(),oneBeginDate,oneEndDate,null,null,1,502);
                            }
                        }else {
                            //恢复失败，修改配额第一阶段完成，修改配额第一阶段未满足恢复条件，已将状态修改为-2
                            recoverProb.updateApiStatus(aid,-2);
                            recoverProb.updateRunLog(recoverProbId,apiTeam.getId(),oneBeginDate,new Date(),null,null,0,-503);
                        }
                    }else{
                        //恢复成功，当前类型通道有且仅有一个，已启用该通道
                        recoverProb.updateApiStatus(aid,0);
                        recoverProb.updateRunLog(recoverProbId,null,null,null,null,null,1,501);
                    }
                }else {
                    //恢复失败，接口请求异常
                    recoverProb.updateRunLog(recoverProbId,null,null,null,null,null,0,-502);
                }
            }else {
                //恢复失败，该通道已被人工启用
                recoverProb.updateRunLog(recoverProbId,null,null,null,null,null,0,-501);
            }
        }catch (Exception e){
            e.printStackTrace();
            //恢复失败，程序异常，已将状态修改为-2，请联系管理员
            recoverProb.updateApiStatus(aid,-2);
            recoverProb.updateRunLog(recoverProbId,null,null,null,null,null,0,-500);
        }finally {
            RecoverProbCheck param = new RecoverProbCheck();
            param.setTid(tid);
            param.setValue(0);
            apiMapper.updateRecoverProbCheck(param);
        }
    }
}

