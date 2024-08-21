package com.example.waplan.goal.api;

import com.example.waplan.goal.application.SecondGoalService;
import com.example.waplan.goal.application.dto.ColorUpdateRequest;
import com.example.waplan.goal.application.dto.SecondGoalAddRequest;
import com.example.waplan.goal.application.dto.SecondGoalUpdateRequest;
import com.example.waplan.security.CurrentUser;
import com.example.waplan.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/secondgoal")
public class SecondGoalApi {

    private final SecondGoalService secondGoalService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> addSecondGoal(@CurrentUser User user, @Valid @RequestBody SecondGoalAddRequest secondGoalAddRequest){
        Long secondGoalId = secondGoalService.addSecondGoal(user, secondGoalAddRequest);
        return ResponseEntity.created(URI.create("/api/secondgoal/" + secondGoalId)).build();
    }

    @PutMapping("/color")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> updateGoalColor(@CurrentUser User user, @Valid @RequestBody ColorUpdateRequest colorUpdateRequest){
        secondGoalService.updateGoalColor(user, colorUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> updateName(@CurrentUser User user, @Valid @RequestBody SecondGoalUpdateRequest secondGoalUpdateRequest){
        secondGoalService.updateName(user, secondGoalUpdateRequest);
        return ResponseEntity.ok().build();
    }


}
