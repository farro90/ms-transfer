package com.nttdata.bc19.mstransfer.model.responseWC;

import lombok.Data;

@Data
public class ActiveProduct {
    private String id;
    private String name;
    private double interestRateMonth;
    private Boolean allowBusinessClient;
    private Boolean allowPersonClient;
}
