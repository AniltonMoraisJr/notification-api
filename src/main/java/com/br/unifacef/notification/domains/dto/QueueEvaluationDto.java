package com.br.unifacef.notification.domains.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueueEvaluationDto {
    @JsonProperty("scheduleId")
    private Integer scheduleId;
}
