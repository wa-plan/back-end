package com.example.waplan.goal.api;

import com.example.waplan.goal.application.GoalService;
import com.example.waplan.goal.application.dto.GoalAddRequest;
import com.example.waplan.security.CurrentUser;
import com.example.waplan.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/goal")
public class GoalApi {

    private final GoalService goalService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> addGoal(@CurrentUser User user, @Valid @RequestBody GoalAddRequest goalAddRequest){
        Long goalId = goalService.addGoal(user, goalAddRequest);
        return ResponseEntity.created(URI.create("/api/goal/" + goalId)).build();
    }
}
