package org.qydata.dst;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.qydata.entity.Customer;
import org.qydata.entity.Dept;

import java.io.Serializable;

/**
 * Created by jonhn on 2016/12/5.
 */
@Data
public class CustomerDeptInfo implements Serializable {

    private Customer customer;
    private Dept dept;


}
