package com.example.waplan.goal.api;

import com.example.waplan.goal.application.ThirdGoalService;
import com.example.waplan.goal.application.dto.ThirdGoalAchievementDto;
import com.example.waplan.goal.application.dto.ThirdGoalDto;
import com.example.waplan.goal.application.dto.ThirdGoalNewTitleDto;
import com.example.waplan.goal.domain.ThirdGoal;
import com.example.waplan.security.CurrentUser;
import com.example.waplan.user.domain.User;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goal")
public class ThirdGoalApi {
    @Autowired
    private ThirdGoalService thirdGoalService;

    @PostMapping("/edit/third")
    public ResponseEntity<ThirdGoalDto> editThirdGoal(@RequestBody ThirdGoalDto thirdGoalDto, @CurrentUser User user){
        Optional<ThirdGoal> thirdGoal = thirdGoalService.getThirdGoalById(thirdGoalDto.getId());
        if(thirdGoal.isPresent() && thirdGoal.get().getSecondGoal().getFirstGoal().getUser().getUserId().equals(user.getUserId())){
            ThirdGoal updatedGoal = thirdGoalService.updateThirdGoal(thirdGoalDto);
            return ResponseEntity.ok(ThirdGoalDto.fromEntity(updatedGoal));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/third/{id}")
    public ResponseEntity<Void> deleteThirdGoal(@PathVariable Long id, @CurrentUser User user) {
        Optional<ThirdGoal> thirdGoal = thirdGoalService.getThirdGoalById(id);
        if (thirdGoal.isPresent() && thirdGoal.get().getSecondGoal().getFirstGoal().getUser().getUserId().equals(user.getUserId())) {
            thirdGoalService.deleteThirdGoal(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/third/{thirdId}/newTitle")
    public ResponseEntity<ThirdGoalDto> addNewTitle(@PathVariable Long thirdId, @RequestParam String newTitle, @CurrentUser User user){
        Optional<ThirdGoal> thirdGoal = thirdGoalService.getThirdGoalById(thirdId);
        if (thirdGoal.isPresent() && thirdGoal.get().getSecondGoal().getFirstGoal().getUser().getUserId().equals(user.getUserId())) {
            ThirdGoal updatedGoal = thirdGoalService.addNewTitle(thirdId, newTitle);
            return ResponseEntity.ok(ThirdGoalDto.fromEntity(updatedGoal));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/third/{id}/newTitle")
    public ResponseEntity<ThirdGoalNewTitleDto> getNewTitle(@PathVariable Long id) {
        Optional<ThirdGoalNewTitleDto> newTitleDto = thirdGoalService.getNewTitle(id);
        if (newTitleDto.isPresent() && newTitleDto.get().getNewTitle() != null) {
            return ResponseEntity.ok(newTitleDto.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/second/{secondGoalId}/thirds")
    public ResponseEntity<List<ThirdGoalDto>> getSecondGoalsByFirstGoalId(@PathVariable Long secondGoalId, @CurrentUser User user){
        List<ThirdGoal> thirdGoals = thirdGoalService.getThirdGoalsBySecondGoalId(secondGoalId);

        if (thirdGoals.isEmpty() || !thirdGoals.get(0).getSecondGoal().getFirstGoal().getUser().getUserId().equals(user.getUserId())) {
            return ResponseEntity.notFound().build();
        }

        List<ThirdGoalDto> thirdGoalDtos = thirdGoals.stream()
            .map(ThirdGoalDto::fromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(thirdGoalDtos);
    }

    @PostMapping("/edit/third/{thirdGoalId}/color")
    public ResponseEntity<ThirdGoalDto> editThirdGoalColor(@PathVariable Long thirdGoalId, @RequestParam String color, @CurrentUser User user) {
        Optional<ThirdGoal> thirdGoal = thirdGoalService.getThirdGoalById(thirdGoalId);
        if (thirdGoal.isPresent() && thirdGoal.get().getSecondGoal().getFirstGoal().getUser().getUserId().equals(user.getUserId())) {
            ThirdGoal updatedGoal = thirdGoalService.updateColor(thirdGoalId, color);
            return ResponseEntity.ok(ThirdGoalDto.fromEntity(updatedGoal));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/third/{thirdId}/achievementLevel")
    public ResponseEntity<ThirdGoalAchievementDto> updateAchievementLevel(@PathVariable Long thirdId, @RequestParam String achievement, @CurrentUser User user) {
        Optional<ThirdGoal> thirdGoal = thirdGoalService.getThirdGoalById(thirdId);
        if (thirdGoal.isPresent() && thirdGoal.get().getSecondGoal().getFirstGoal().getUser().getUserId().equals(user.getUserId())) {
            ThirdGoalAchievementDto updatedGoal = thirdGoalService.updateAchievementLevel(thirdId, achievement);
            return ResponseEntity.ok(updatedGoal);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/third/{thirdId}/dates")
    public ResponseEntity<ThirdGoalDto> updateDates(@PathVariable Long thirdId, @RequestBody List<LocalDate> dates, @CurrentUser User user) {
        Optional<ThirdGoal> thirdGoal = thirdGoalService.getThirdGoalById(thirdId);
        if (thirdGoal.isPresent() && thirdGoal.get().getSecondGoal().getFirstGoal().getUser().getUserId().equals(user.getUserId())) {
            ThirdGoal updatedGoal = thirdGoalService.updateDates(thirdId, dates);
            return ResponseEntity.ok(ThirdGoalDto.fromEntity(updatedGoal));
        }
        return ResponseEntity.notFound().build();
    }
}
