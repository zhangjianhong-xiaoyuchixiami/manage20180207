package org.qydata.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2016/12/5.
 */
@Data
public class ApiVendor implements Serializable {

    private Integer id;
    private String name;
    private Partner partner;
    private Integer status;

}
