package com.br.unifacef.notification.domains.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class EmailSchedulerDto {
    private String userName;
    private String startDate;
    private String endDate;
    private String startHour;
    private String endHour;
    private BigDecimal amount;

}
