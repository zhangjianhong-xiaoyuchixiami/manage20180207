package org.qydata.tools.finance;

import org.qydata.entity.agency.AgencyBillDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/10/26.
 */
public class AgencyBillUtil {

    public static Map<String, Object> dataProcessing(Integer agencyId, List<AgencyBillDetail> detailList, List<AgencyBillDetail> detailListOppo, List<AgencyBillDetail> detailListCache) {
        Double costRealOur = 0.0;  //对方调用我方源（不包含缓存，按真实成本价计算）
        Double costVirtualOur = 0.0;  //对方调用我方源（不包含缓存，按返佣起始价计算）
        Double costOpposite = 0.0;
        Double firstRebate = 0.0;
        Double secondaryRebate = 0.0;
        Double costCache = 0.0;
        Double netProfit = 0.0;

        //王翠英
        if (agencyId == 1) {
            if (detailList != null) {
                for (AgencyBillDetail detail : detailList) {
                    costRealOur += countCostRealOur(detail.getCost(), detail.getCostCount());
                    costVirtualOur += countCostVirtualOur(detail.getCost(), detail.getRebateBegPrice(), detail.getCostCount());
                    secondaryRebate += countSecondaryRebate(detail.getCost(), detail.getRebateBegPrice(), detail.getRebateEndPrice(), detail.getPrice(), detail.getCostCount());
                }
            }
            if (detailListOppo != null) {
                for (AgencyBillDetail detail : detailListOppo) {
                    costOpposite += countCostOpposite(detail.getCost(), detail.getCostCount());
                }
            }
            if (detailListCache != null) {
                for (AgencyBillDetail detail : detailListCache) {
                    costCache += countCostCache(detail.getPrice(), detail.getCostCount());
                }
            }
            netProfit = (costVirtualOur - costRealOur) + (secondaryRebate / 2.0) + (costCache / 2.0);
            Map<String, Object> map = new HashMap<>();
            map.put("costRealOur", costRealOur);
            map.put("costVirtualOur", costVirtualOur);
            map.put("costOpposite", costOpposite);
            map.put("firstRebate", "不参与首次返佣");
            map.put("secondaryRebate", secondaryRebate / 2.0);
            map.put("costCache", costCache);
            map.put("cacheRebate", costCache / 2.0);
            map.put("netProfit", netProfit);
            return map;
        }

        //尚阳
        if(agencyId ==100000){
            if (detailList != null) {
                for (AgencyBillDetail detail : detailList) {
                    costRealOur += countCostRealOur(detail.getCost(), detail.getCostCount());
                    costVirtualOur += countCostVirtualOur(detail.getCost(), detail.getRebateBegPrice(), detail.getCostCount());
                    firstRebate += countFirstRebate(detail.getPrice(), detail.getRebateEndPrice(), detail.getCostCount());
                    secondaryRebate += countSecondaryRebate(detail.getCost(), detail.getRebateBegPrice(), detail.getRebateEndPrice(), detail.getPrice(), detail.getCostCount());
                }
            }
            if (detailListCache != null) {
                for (AgencyBillDetail detail : detailListCache) {
                    costCache += countCostCache(detail.getPrice(), detail.getCostCount());
                }
            }
            netProfit = (costVirtualOur - costRealOur) + (secondaryRebate / 2.0) + costCache;
            Map<String, Object> map = new HashMap<>();
            map.put("costRealOur", costRealOur);
            map.put("costVirtualOur", costVirtualOur);
            map.put("costOpposite", "我方未使用对方源");
            map.put("firstRebate", firstRebate * 0.94);
            map.put("secondaryRebate", secondaryRebate / 2.0);
            map.put("costCache", costCache);
            map.put("cacheRebate", "缓存不参与分成");
            map.put("netProfit", netProfit);
            return map;
        }
        return null;
    }


    //对方调用我方源（不包含缓存，按真实成本价计算）
    public static Double countCostRealOur(Double cost,Integer count){
        if (cost != null && count != null){
            return cost * count;
        }
        return 0.0;
    }

    //对方调用我方源（不包含缓存，按返佣起始价计算）
    public static Double countCostVirtualOur(Double cost,Double begPrice,Integer count){
        if (cost != null && count != null){
            if (begPrice != null){
                return begPrice * count;
            }
            return cost * count;
        }
        return 0.0;
    }

    //计算首次业务回扣
    public static Double countFirstRebate(Double price,Double endPrice,Integer count){
        if (price != null && endPrice != null &&count != null){
            return (price - endPrice) * count;
        }
        return  0.0;
    }

    //计算二次业务回扣
    public static Double countSecondaryRebate(Double cost,Double begPrice,Double endPrice,Double price,Integer count) {
        if (cost != null && price != null && count != null){
            if (endPrice == null) {
                if (begPrice != null) {
                    return (price - begPrice) * count;
                }
                return (price - cost) * count;
            } else {
                if (begPrice != null) {
                    return (endPrice - begPrice) * count;
                }
                return (endPrice - cost) * count;
            }
        }
        return 0.0;
    }

    //我方调用对方源（不包含缓存）
    public static Double countCostOpposite(Double cost,Integer count) {
        if (cost != null && count != null){
            return cost * count;
        }
        return 0.0;
    }

    //计算缓存
    public static Double countCostCache(Double price,Integer count) {
        if (price != null && count != null){
            return price * count;
        }
        return 0.0;
    }
}
