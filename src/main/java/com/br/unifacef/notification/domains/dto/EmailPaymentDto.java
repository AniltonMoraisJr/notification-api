package com.br.unifacef.notification.domains.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class EmailPaymentDto {
    private String userName;
    private String paymentType;
    private String paymentCode;
    private String receiveDate;
    private BigDecimal amount;

}
