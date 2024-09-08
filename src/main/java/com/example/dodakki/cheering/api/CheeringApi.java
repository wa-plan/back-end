package com.example.dodakki.cheering.api;

import com.example.dodakki.cheering.application.CheeringService;
import com.example.dodakki.cheering.application.dto.CheeringResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cheering")
public class CheeringApi {

    private final CheeringService cheeringService;

    @GetMapping
    public ResponseEntity<CheeringResponse> getCheering(){
        CheeringResponse cheering = cheeringService.getRandomCheering();
        return ResponseEntity.ok(cheering);
    }
}
