package com.example.waplan.goal.application;

import com.example.waplan.goal.application.dto.SecondGoalDto;
import com.example.waplan.goal.domain.SecondGoal;
import com.example.waplan.goal.domain.repository.SecondGoalRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecondGoalService {
    @Autowired
    private SecondGoalRepository secondGoalRepository;

    public SecondGoal updateSecondGoal(SecondGoalDto goalDto){
        SecondGoal goal = secondGoalRepository.findById(goalDto.getId()).orElseThrow();
        goal.setTitle(goalDto.getTitle());
        goal.setField(goalDto.getField());
        return secondGoalRepository.save(goal);
    }

    public void deleteSecondGoal(Long id){
        secondGoalRepository.deleteById(id);
    }

    public Optional<SecondGoal> getSecondGoalById(Long id) {
        return secondGoalRepository.findById(id);
    }

    public List<SecondGoal> getSecondGoalByFirstGoalId(Long firstGoalId){
        return secondGoalRepository.findByFirstGoalId(firstGoalId);
    }
}
