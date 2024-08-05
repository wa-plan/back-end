package com.example.waplan.goal.application;

import com.example.waplan.goal.application.dto.GoalAddRequest;
import com.example.waplan.goal.domain.*;
import com.example.waplan.goal.domain.repository.GoalDateMapRepository;
import com.example.waplan.goal.domain.repository.GoalDateRepository;
import com.example.waplan.goal.domain.repository.GoalRepository;
import com.example.waplan.goal.domain.repository.ThirdGoalRepository;
import com.example.waplan.goal.exception.GoalException;
import com.example.waplan.goal.exception.GoalExceptionType;
import com.example.waplan.user.domain.User;
import com.example.waplan.user.domain.repository.UserRepository;
import com.example.waplan.user.exception.UserException;
import com.example.waplan.user.exception.UserExceptionType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GoalService {

    private final UserRepository userRepository;
    private final ThirdGoalRepository thirdGoalRepository;
    private final GoalDateRepository goalDateRepository;
    private final GoalDateMapRepository goalDateMapRepository;
    private final GoalRepository goalRepository;

    public Long addGoal(User user, GoalAddRequest goalAddRequest){
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        ThirdGoal thirdGoal = thirdGoalRepository.findById(goalAddRequest.getThirdGoalId()).orElseThrow(() -> new GoalException(
                GoalExceptionType.NOT_FOUND_GOAL));
        Goal goal = new Goal(goalAddRequest.getName(), Status.IN_PROGRESS, thirdGoal);
        goalRepository.save(goal);
        List<GoalDateMap> goalDateMapList = goalAddRequest.getDates().stream()
                .map(date -> {
                    GoalDateMap goalDateMap;
                    Optional<GoalDate> goalDate = goalDateRepository.findByDate(date);
                    if(goalDate.isPresent()){
                        goalDateMap = goalDateMapRepository.save(new GoalDateMap(goal, goalDate.get()));
                    }
                    else{
                        GoalDate goalDate1 = goalDateRepository.save(new GoalDate(date));
                        goalDateMap = goalDateMapRepository.save(new GoalDateMap(goal, goalDate1));
                    }
                    return goalDateMap;
                }).toList();
        goal.setGoalDateMapList(goalDateMapList);
        return goal.getId();
    }
}
