package com.example.waplan.goal.api;

import com.example.waplan.goal.application.FirstGoalService;
import com.example.waplan.goal.application.dto.FirstGoalAddDto;
import com.example.waplan.goal.application.dto.FirstGoalDetailDto;
import com.example.waplan.goal.application.dto.FirstGoalDto;
import com.example.waplan.goal.domain.FirstGoal;
import com.example.waplan.security.CurrentUser;
import com.example.waplan.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/goal")
public class FirstGoalApi {
    @Autowired
    private FirstGoalService goalService;

    @GetMapping("/first")
    public ResponseEntity<List<FirstGoalDto>> getAllFirstGoals(@CurrentUser User user){
        List<FirstGoalDto> goals = goalService.getAllGoals(user.getUserId()).stream()
            .map(FirstGoalDto::fromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(goals);
    }

    @PostMapping("/add")
    public ResponseEntity<FirstGoalDto> addGoal(@RequestBody FirstGoalAddDto goalDto, @CurrentUser User user){
        FirstGoal goal = goalService.addGoal(goalDto, user);
        return ResponseEntity.ok(FirstGoalDto.fromEntity(goal));
    }

    @GetMapping("/detail/{firstGoalId}")
    public ResponseEntity<FirstGoalDetailDto> getFirstGoalById(@PathVariable Long firstGoalId, @CurrentUser User user){
        Optional<FirstGoal> goal = goalService.getFirstGoalById(firstGoalId);
        if(goal.isPresent() && goal.get().getUser().getUserId().equals(user.getUserId())){
            return ResponseEntity.ok(FirstGoalDetailDto.fromEntity(goal.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{firstGoalId}")
    public ResponseEntity<Void> deleteFirstGoal(@PathVariable Long firstGoalId, @CurrentUser User user){
        Optional<FirstGoal> goal = goalService.getFirstGoalById(firstGoalId);
        if(goal.isPresent() && goal.get().getUser().getUserId().equals(user.getUserId())){
            goalService.deleteFirstGoal(firstGoalId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
