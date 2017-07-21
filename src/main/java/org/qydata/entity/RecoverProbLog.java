package org.qydata.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jonhn on 2017/7/20.
 */
public class RecoverProbLog implements Serializable {

    private Integer id;
    private Integer rApiId;
    private Integer tApiId;
    private Date phaseOneBeginTime;
    private Date phaseOneEndTime;
    private Date phaseTwoBeginTime;
    private Date phaseTwoEndTime;
    private Integer statusCode;
    private Integer ok;
    private String statusName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getrApiId() {
        return rApiId;
    }

    public void setrApiId(Integer rApiId) {
        this.rApiId = rApiId;
    }

    public Integer gettApiId() {
        return tApiId;
    }

    public void settApiId(Integer tApiId) {
        this.tApiId = tApiId;
    }

    public Date getPhaseOneBeginTime() {
        return phaseOneBeginTime;
    }

    public void setPhaseOneBeginTime(Date phaseOneBeginTime) {
        this.phaseOneBeginTime = phaseOneBeginTime;
    }

    public Date getPhaseOneEndTime() {
        return phaseOneEndTime;
    }

    public void setPhaseOneEndTime(Date phaseOneEndTime) {
        this.phaseOneEndTime = phaseOneEndTime;
    }

    public Date getPhaseTwoBeginTime() {
        return phaseTwoBeginTime;
    }

    public void setPhaseTwoBeginTime(Date phaseTwoBeginTime) {
        this.phaseTwoBeginTime = phaseTwoBeginTime;
    }

    public Date getPhaseTwoEndTime() {
        return phaseTwoEndTime;
    }

    public void setPhaseTwoEndTime(Date phaseTwoEndTime) {
        this.phaseTwoEndTime = phaseTwoEndTime;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getOk() {

        return ok;
    }

    public void setOk(Integer ok) {
        this.ok = ok;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
