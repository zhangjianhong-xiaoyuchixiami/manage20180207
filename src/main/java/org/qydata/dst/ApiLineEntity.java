package org.qydata.dst;

import lombok.Data;

import java.util.List;

@Data
public class ApiLineEntity {
    private String name;
    private List<Double> data;
}
