package com.nttdata.bc19.mstransfer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transfer extends BaseModel {
    private String operationNumber;
    private String numberAccountSource;
    private String accountTypeSource;
    private String numberAccountDestiny;
    private String accountTypeDestiny;
    private double amount;
}
