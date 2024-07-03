package com.example.waplan.others.application;

import com.example.waplan.others.application.dto.CheeringDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class CheeringService {
    private final List<CheeringDto> cheerings = new ArrayList<>();

    private CheeringService(){
        cheerings.add(new CheeringDto("실패하는 것이 두려운 게 아니라 노력하지 않는 것이 두렵다.", "마이클 조던"));
        cheerings.add(new CheeringDto("실패하는 것이 두려운 게 아니라 노력하지 않는 것이 두렵다.", "마이클 조던"));
        cheerings.add(new CheeringDto("실패하는 것이 두려운 게 아니라 노력하지 않는 것이 두렵다.", "마이클 조던"));
    }

    public CheeringDto getRandomCheering(){
        Random random = new Random();
        return cheerings.get(random.nextInt(cheerings.size()));
    }
}
