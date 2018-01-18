package org.qydata.dst.valid;


import lombok.Data;

@Data
public class ValidResult {

    private String requestBody;
    private String result;
    private String responseBody;
    private String photo;
    private String dataSource;
    private String apiType;

}
