package com.example.waplan.cheering.api;

import com.example.waplan.cheering.application.CheeringService;
import com.example.waplan.cheering.application.dto.CheeringResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cheering")
public class CheeringApi {

    private final CheeringService cheeringService;

    @GetMapping
    public ResponseEntity<CheeringResponse> getCheering(){
        CheeringResponse cheering = cheeringService.getRandomCheering();
        return ResponseEntity.ok(cheering);
    }
}
