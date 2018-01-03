package org.qydata.dst;

import lombok.Data;

import java.util.List;

@Data
public class NearlyWeekCondition {
    private Double totalIncome;
    private Double totalCost;
    private Double totalProfit;
    private String cycle;
}
