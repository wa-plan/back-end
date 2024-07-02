package com.example.waplan.others.api;

import com.example.waplan.others.application.CheeringService;
import com.example.waplan.others.application.dto.CheeringDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cheering")
public class CheeringApi {
    @Autowired
    private CheeringService cheeringService;

    @GetMapping
    public ResponseEntity<CheeringDto> getCheering(){
        CheeringDto cheering = cheeringService.getRandomCheering();
        return ResponseEntity.ok(cheering);
    }
}
