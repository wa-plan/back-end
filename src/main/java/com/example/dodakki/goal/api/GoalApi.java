package com.example.dodakki.goal.api;

import com.example.dodakki.goal.application.GoalService;
import com.example.dodakki.goal.application.dto.*;
import com.example.dodakki.security.CurrentUser;
import com.example.dodakki.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

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

    @PutMapping("/status")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> updateGoalStatus(@CurrentUser User user, @Valid @RequestBody GoalUpdateStatusRequest goalUpdateStatusRequest){
        goalService.updateGoalStatus(user, goalUpdateStatusRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> updateGoal(@CurrentUser User user, @Valid @RequestBody GoalUpdateRequest goalUpdateRequest){
        goalService.updateGoal(user, goalUpdateRequest);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{goalId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteGoal(@CurrentUser User user, @Valid @PathVariable("goalId") Long goalId ){
        goalService.deleteGoal(user, goalId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<GoalResponse>> getGoal(@CurrentUser User user, @Valid @RequestParam("date") LocalDate goalRequest){
        return ResponseEntity.ok(goalService.getGoal(user, goalRequest));
    }


}
