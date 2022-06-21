package com.nttdata.bc19.mstransfer.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nttdata.bc19.mstransfer.model.BaseModel;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferRequest extends BaseModel {
    private String numberAccountSource;
    private String accountTypeSource;
    private String numberAccountDestiny;
    private String accountTypeDestiny;
    private double amount;
}
