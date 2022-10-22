package com.br.unifacef.notification.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class HandleScheduleMessage {
    private final SendScheduleEmail sendScheduleEmail;

    public void handle(Integer message){
        try {
            // 1. Get Schedule Document from datasource
            // 2. Transform into DTO
            // 3. Send e-mail
        } catch (Exception e) {

        }
    }
}
