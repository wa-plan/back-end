package com.example.waplan.user.application.dto;

import com.example.waplan.user.domain.AlarmType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MorningAlarmRequest {

    private AlarmType alarm;

    public MorningAlarmRequest(final AlarmType alarm){
        this.alarm = alarm;
    }
}
