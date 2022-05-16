package com.zsener.ReadingIsGood.request;

import lombok.Data;
import java.util.Date;

@Data
public class GetCustomerStatsRequest {
    private Date startDate;
    private Date endDate;
    private String customerId;
}
