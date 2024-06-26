package com.example.waplan.user.api;

import com.example.waplan.user.application.dto.goal.FirstGoalDto;
import com.example.waplan.user.application.goal.GoalService;
import com.example.waplan.user.domain.goal.FirstGoal;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goal")
public class FirstGoalApi {
    @Autowired
    private GoalService goalService;

    @GetMapping("/first")
    public ResponseEntity<List<FirstGoal>> getAllFirstGoals(){
        List<FirstGoal> goals = goalService.getAllFirstGoals();
        return ResponseEntity.ok(goals);
    }

    @PostMapping("/add")
    public ResponseEntity<FirstGoal> addGoal(@RequestBody FirstGoalDto goalDto){
        FirstGoal goal = goalService.addGoal(goalDto);
        return ResponseEntity.ok(goal);
    }

    @GetMapping("/detail/{firstGoalId}")
    public ResponseEntity<FirstGoal> getFirstGoalById(@PathVariable Long firstGoalId){
        Optional<FirstGoal> goal = goalService.getFirstGoalById(firstGoalId);
        return goal.map(ResponseEntity::ok)
            .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{firstGoalId}")
    public ResponseEntity<Void> deleteFirstGoal(@PathVariable Long firstGoalId){
        goalService.deleteFirstGoal(firstGoalId);
        return ResponseEntity.noContent().build();
    }
}
