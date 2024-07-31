package com.example.waplan.goal.api;

import com.example.waplan.goal.application.MandalartService;
import com.example.waplan.goal.application.dto.MandalartAddRequest;
import com.example.waplan.goal.application.dto.MandalartResponse;
import com.example.waplan.goal.domain.Mandalart;
import com.example.waplan.security.CurrentUser;
import com.example.waplan.user.application.dto.UserResponse;
import com.example.waplan.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{mandalartId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MandalartResponse> getMandalart(@CurrentUser User user, @PathVariable("mandalartId") Long mandalartId) {
        Mandalart mandalart = mandalartService.getMandalart(user, mandalartId);
        return ResponseEntity.ok(MandalartResponse.of(mandalart));
    }
}
