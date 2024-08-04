package com.example.waplan.goal.api;

import com.example.waplan.goal.application.MandalartService;
import com.example.waplan.goal.application.dto.MandalartAddRequest;
import com.example.waplan.goal.application.dto.MandalartProgressRequest;
import com.example.waplan.goal.application.dto.MandalartResponse;
import com.example.waplan.goal.domain.Mandalart;
import com.example.waplan.goal.domain.Status;
import com.example.waplan.security.CurrentUser;
import com.example.waplan.user.application.dto.UserResponse;
import com.example.waplan.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mandalart")
public class MandalartApi {

    private final MandalartService mandalartService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Long> addFirstGoal(@CurrentUser User user, @Valid @RequestBody MandalartAddRequest mandalartAddRequest) {
        Long mandalartId = mandalartService.addMandalart(user, mandalartAddRequest);
        return ResponseEntity.ok(mandalartId);
    }

    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MandalartResponse> getMandalart(@CurrentUser User user, @RequestParam String mandalartName) {
        Mandalart mandalart = mandalartService.getMandalart(user, mandalartName);
        return ResponseEntity.ok(MandalartResponse.of(mandalart));
    }

    @PatchMapping("/progress")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Status> updateProgress(@CurrentUser User user, @Valid @RequestBody MandalartProgressRequest mandalartProgressRequest){
        Status mandalartStatus = mandalartService.updateProgress(user, mandalartProgressRequest);
        return ResponseEntity.ok(mandalartStatus);
    }
}
