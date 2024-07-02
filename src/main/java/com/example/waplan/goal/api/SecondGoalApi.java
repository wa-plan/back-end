package com.example.waplan.goal.api;

import com.example.waplan.goal.application.SecondGoalService;
import com.example.waplan.goal.application.dto.SecondGoalDto;
import com.example.waplan.goal.domain.SecondGoal;
import com.example.waplan.security.CurrentUser;
import com.example.waplan.user.domain.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
public class SecondGoalApi {
    @Autowired
    private SecondGoalService secondGoalService;

    @GetMapping("/first/{firstGoalId}/seconds")
    public ResponseEntity<List<SecondGoalDto>> getSecondGoalsByFirstGoalId(@PathVariable Long firstGoalId, @CurrentUser User user){
        List<SecondGoal> secondGoals = secondGoalService.getSecondGoalByFirstGoalId(firstGoalId);

        if (secondGoals.isEmpty() || !secondGoals.get(0).getFirstGoal().getUser().getUserId().equals(user.getUserId())) {
            return ResponseEntity.notFound().build();
        }

        List<SecondGoalDto> secondGoalDtos = secondGoals.stream()
            .map(SecondGoalDto::fromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(secondGoalDtos);
    }

    @PostMapping("/edit/second")
    public ResponseEntity<SecondGoalDto> editSecondGoal(@RequestBody SecondGoalDto secondGoalDto, @CurrentUser User user){
        Optional<SecondGoal> secondGoal = secondGoalService.getSecondGoalById(secondGoalDto.getId());
        if (secondGoal.isPresent() && secondGoal.get().getFirstGoal().getUser().getUserId().equals(user.getUserId())) {
            SecondGoal updatedGoal = secondGoalService.updateSecondGoal(secondGoalDto);
            return ResponseEntity.ok(SecondGoalDto.fromEntity(updatedGoal));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/second/{id}")
    public ResponseEntity<Void> deleteSecondGoal(@PathVariable Long id, @CurrentUser User user) {
        Optional<SecondGoal> secondGoal = secondGoalService.getSecondGoalById(id);
        if (secondGoal.isPresent() && secondGoal.get().getFirstGoal().getUser().getUserId().equals(user.getUserId())) {
            secondGoalService.deleteSecondGoal(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
