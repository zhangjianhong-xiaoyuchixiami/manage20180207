package org.qydata.dst;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/3/2.
 */
public class CompanyApiCount implements Serializable {

    private Integer sumApiTypeIdAmount;
    private Integer countTotle;
    private Integer countSuccess;

    public Integer getSumApiTypeIdAmount() {
        return sumApiTypeIdAmount;
    }

    public void setSumApiTypeIdAmount(Integer sumApiTypeIdAmount) {
        this.sumApiTypeIdAmount = sumApiTypeIdAmount;
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
}
