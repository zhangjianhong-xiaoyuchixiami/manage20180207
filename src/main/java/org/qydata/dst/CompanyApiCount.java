package org.qydata.dst;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/3/2.
 */
public class CompanyApiCount implements Serializable {

    private Integer sumAmount;
    private Integer countTotle;
    private Integer countSuccess;
    private Integer stidSumAmount;

    public Integer getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(Integer sumAmount) {
        this.sumAmount = sumAmount;
    }

    public Integer getCountTotle() {
        return countTotle;
    }

    public void setCountTotle(Integer countTotle) {
        this.countTotle = countTotle;
    }

    public Integer getCountSuccess() {
        return countSuccess;
    }

    public void setCountSuccess(Integer countSuccess) {
        this.countSuccess = countSuccess;
    }

    public Integer getStidSumAmount() {
        return stidSumAmount;
    }

    public void setStidSumAmount(Integer stidSumAmount) {
        this.stidSumAmount = stidSumAmount;
    }
}
