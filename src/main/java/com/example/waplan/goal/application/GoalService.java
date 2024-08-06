package com.example.waplan.goal.application;

import com.example.waplan.goal.application.dto.*;
import com.example.waplan.goal.domain.*;
import com.example.waplan.goal.domain.repository.*;
import com.example.waplan.goal.exception.GoalException;
import com.example.waplan.goal.exception.GoalExceptionType;
import com.example.waplan.goal.exception.MandalartException;
import com.example.waplan.goal.exception.MandalartExceptionType;
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
    private final MandalartRepository mandalartRepository;

    public Long addGoal(User user, GoalAddRequest goalAddRequest){
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        ThirdGoal thirdGoal = thirdGoalRepository.findById(goalAddRequest.getThirdGoalId()).orElseThrow(() -> new GoalException(
                GoalExceptionType.NOT_FOUND_GOAL));
        Mandalart mandalart = mandalartRepository.findById(thirdGoal.getSecondGoal().getMandalart().getId()).orElseThrow(() -> new MandalartException(
                MandalartExceptionType.NOT_FOUND_MANDALART));
        Goal goal = new Goal(goalAddRequest.getName(), Status.IN_PROGRESS, mandalart, thirdGoal);
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
        mandalart.setGoalCount(mandalart.getGoalCount() + 1);
        return goal.getId();
    }

    public void updateGoalStatus(User user, GoalUpdateStatusRequest request){
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        Goal goal = goalRepository.findById(request.getGoalId()).orElseThrow(() -> new GoalException(
                GoalExceptionType.NOT_FOUND_GOAL));
        Mandalart mandalart = mandalartRepository.findById(goal.getMandalart().getId()).orElseThrow(() -> new MandalartException(
                MandalartExceptionType.NOT_FOUND_MANDALART));
        goal.setAttainment(request.getAttainment());
        if(request.getAttainment() == Status.SUCCESS){
            mandalart.setAttainmentCount(mandalart.getAttainmentCount() + 1);
        }
    }

    public void updateGoal(User user, GoalUpdateRequest request){
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        Goal goal = goalRepository.findById(request.getGoalId()).orElseThrow(() -> new GoalException(
                GoalExceptionType.NOT_FOUND_GOAL));
        goal.setName(request.getNewGoal());
    }

    public void deleteGoal(User user, Long goalId){
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        Goal goal = goalRepository.findById(goalId).orElseThrow(() -> new GoalException(
                GoalExceptionType.NOT_FOUND_GOAL));
        Mandalart mandalart = mandalartRepository.findById(goal.getMandalart().getId()).orElseThrow(() -> new MandalartException(
                MandalartExceptionType.NOT_FOUND_MANDALART));
        mandalart.setGoalCount(mandalart.getGoalCount() - 1);
        goalRepository.delete(goal);
    }
    public GoalResponse getGoal(User user, GoalRequest request){
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));

    }
}
