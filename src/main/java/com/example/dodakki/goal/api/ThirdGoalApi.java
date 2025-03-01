package com.example.dodakki.goal.api;

import com.example.dodakki.goal.application.ThirdGoalService;
import com.example.dodakki.goal.application.dto.ThirdGoalAddRequest;
import com.example.dodakki.goal.application.dto.ThirdGoalUpdateRequest;
import com.example.dodakki.security.CurrentUser;
import com.example.dodakki.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/thirdgoal")
public class ThirdGoalApi {


    private final ThirdGoalService thirdGoalService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> addThirdGoal(@CurrentUser User user, @Valid @RequestBody ThirdGoalAddRequest thirdGoalAddRequest){
        Long thirdGoalId = thirdGoalService.addThirdGoal(user, thirdGoalAddRequest);
        return ResponseEntity.created(URI.create("/api/thirdgoal/" + thirdGoalId)).build();
    }


    @PutMapping()
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> updateName(@CurrentUser User user, @Valid @RequestBody ThirdGoalUpdateRequest thirdGoalUpdateRequest){
        thirdGoalService.updateName(user, thirdGoalUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{thirdGoalId}")
    public ResponseEntity<Void> deleteThirdGoal(@CurrentUser User user, @PathVariable Long thirdGoalId){
        thirdGoalService.deleteGoal(user, thirdGoalId);
        return ResponseEntity.noContent().build();
    }
}
