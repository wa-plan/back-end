package com.example.waplan.goal.api;

import com.example.waplan.goal.application.dto.FirstGoalDto;
import com.example.waplan.goal.application.GoalService;
import com.example.waplan.goal.domain.FirstGoal;
import com.example.waplan.security.CurrentUser;
import com.example.waplan.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goal")
public class FirstGoalApi {
    @Autowired
    private GoalService goalService;

    @GetMapping("/first")
    public ResponseEntity<List<FirstGoal>> getAllFirstGoals(@CurrentUser User user){
        List<FirstGoal> goals = goalService.getAllGoals(user.getUserId());
        return ResponseEntity.ok(goals);
    }

    @PostMapping("/add")
    public ResponseEntity<FirstGoal> addGoal(@RequestBody FirstGoalDto goalDto, @CurrentUser User user){
        goalDto.setUserId(user.getUserId());
        FirstGoal goal = goalService.addGoal(goalDto, user);
        return ResponseEntity.ok(goal);
    }

    @GetMapping("/detail/{firstGoalId}")
    public ResponseEntity<FirstGoal> getFirstGoalById(@PathVariable Long firstGoalId, @CurrentUser User user){
        Optional<FirstGoal> goal = goalService.getFirstGoalById(firstGoalId);
        if(goal.isPresent() && goal.get().getUser().getUserId().equals(user.getUserId())){
            return ResponseEntity.ok(goal.get());
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
