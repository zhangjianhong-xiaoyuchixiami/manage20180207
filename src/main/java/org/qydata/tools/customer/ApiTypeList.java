package org.qydata.tools.customer;

import org.qydata.dst.valid.ReqParam;
import org.qydata.tools.RegexUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/15.
 */
public class ApiTypeList {

    public final static Map<String,String> codeMap = new HashMap<String,String>();

    static {
        codeMap.put("1","/mobile/verify/3f");
        codeMap.put("21","/mobile/verify/3f");
        codeMap.put("4","/mobile/query/duration");
        codeMap.put("5","/mobile/query/status");
        codeMap.put("20","/mobile/verify/2f-name");
        codeMap.put("19","/mobile/verify/2f-id");
        codeMap.put("2","/id/verify/2f");
        codeMap.put("15","/id/verify/2f");
        codeMap.put("18","/id/query/photo");
        codeMap.put("13","/bankcard/verify/3f");
        codeMap.put("3","/bankcard/verify/4f");
    }

    public static ReqParam packageReqParam(String tid,String omit,String skip,String address,
                                           String mob,String id,String name,String bankId)
    {
        if ("1".equals(tid) || "21".equals(tid)) {
            if (!RegexUtil.isNull(mob)) {
                if (!RegexUtil.isNull(id)) {
                    if (!RegexUtil.isNull(name)) {
                        ReqParam param = new ReqParam();
                        //存缓存
                        param.omit = omit;
                        //调换存
                        param.skip = skip;
                        //产品类型
                        param.tid = Integer.valueOf(tid);
                        //产品子类型
                        param.stid = OperatorList.isOperator(mob);
                        //请求地址
                        param.address = address + ApiTypeList.codeMap.get(tid);
                        param.mob = mob;
                        param.id = id;
                        param.name = name;
                        return param;
                    }
                }
            }
        }
        if ("4".equals(tid) || "5".equals(tid)) {
            if (!RegexUtil.isNull(mob)) {
                ReqParam param = new ReqParam();
                //存缓存
                param.omit = omit;
                //调换存
                param.skip = skip;
                //产品类型
                param.tid = Integer.valueOf(tid);
                //产品子类型
                param.stid = OperatorList.isOperator(mob);
                //请求地址
                param.address = address + ApiTypeList.codeMap.get(tid);
                param.mob = mob;
                return param;
            }
        }
        if ("20".equals(tid)) {
            if (!RegexUtil.isNull(mob)) {
                if (!RegexUtil.isNull(name)) {
                    ReqParam param = new ReqParam();
                    //存缓存
                    param.omit = omit;
                    //调换存
                    param.skip = skip;
                    //产品类型
                    param.tid = Integer.valueOf(tid);
                    //产品子类型
                    param.stid = OperatorList.isOperator(mob);
                    //请求地址
                    param.address = address + ApiTypeList.codeMap.get(tid);
                    param.mob = mob;
                    param.name = name;
                    return param;
                }
            }
        }
        if ("19".equals(tid)) {
            if (!RegexUtil.isNull(mob)) {
                if (!RegexUtil.isNull(id)) {
                    ReqParam param = new ReqParam();
                    //存缓存
                    param.omit = omit;
                    //调换存
                    param.skip = skip;
                    //产品类型
                    param.tid = Integer.valueOf(tid);
                    //产品子类型
                    param.stid = OperatorList.isOperator(mob);
                    //请求地址
                    param.address = address + ApiTypeList.codeMap.get(tid);
                    param.mob = mob;
                    param.id = id;
                    return param;
                }
            }
        }
        if ("2".equals(tid) || "15".equals(tid) || "18".equals(tid)) {
            if (!RegexUtil.isNull(id)) {
                if (!RegexUtil.isNull(name)) {
                    ReqParam param = new ReqParam();
                    //存缓存
                    param.omit = omit;
                    //调换存
                    param.skip = skip;
                    //产品类型
                    param.tid = Integer.valueOf(tid);
                    //产品子类型
                    param.stid = 0;
                    //请求地址
                    param.address = address + ApiTypeList.codeMap.get(tid);
                    param.id = id;
                    param.name = name;
                    return param;
                }
            }
        }
        if ("13".equals(tid)) {
            if (!RegexUtil.isNull(id)) {
                if (!RegexUtil.isNull(name)) {
                    if (!RegexUtil.isNull(bankId)) {
                        ReqParam param = new ReqParam();
                        //存缓存
                        param.omit = omit;
                        //调换存
                        param.skip = skip;
                        //产品类型
                        param.tid = Integer.valueOf(tid);
                        //产品子类型
                        param.stid = 0;
                        //请求地址
                        param.address = address + ApiTypeList.codeMap.get(tid);
                        param.id = id;
                        param.name = name;
                        param.bankId = bankId;
                        return param;
                    }
                }
            }
        }
        if ("3".equals(tid)) {
            if (!RegexUtil.isNull(mob)) {
                if (!RegexUtil.isNull(id)) {
                    if (!RegexUtil.isNull(name)) {
                        if (!RegexUtil.isNull(bankId)) {
                            ReqParam param = new ReqParam();
                            //存缓存
                            param.omit = omit;
                            //调换存
                            param.skip = skip;
                            //产品类型
                            param.tid = Integer.valueOf(tid);
                            //产品子类型
                            param.stid = 0;
                            //请求地址
                            param.address = address + ApiTypeList.codeMap.get(tid);
                            param.mob = mob;
                            param.id = id;
                            param.name = name;
                            param.bankId = bankId;
                            return param;
                        }
                    }
                }
            }
        }
        return null;
    }

}
