package com.example.dodakki.user.application.dto;

import com.example.dodakki.user.domain.AlarmType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NightAlarmRequest {
    private AlarmType alarm;

    public NightAlarmRequest(final AlarmType alarm){
        this.alarm = alarm;
    }

}
