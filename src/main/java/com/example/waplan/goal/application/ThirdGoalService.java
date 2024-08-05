package com.example.waplan.goal.application;

import com.example.waplan.goal.application.dto.SecondGoalAddRequest;
import com.example.waplan.goal.application.dto.SecondGoalUpdateRequest;
import com.example.waplan.goal.application.dto.ThirdGoalAddRequest;
import com.example.waplan.goal.application.dto.ThirdGoalUpdateRequest;
import com.example.waplan.goal.domain.Mandalart;
import com.example.waplan.goal.domain.SecondGoal;
import com.example.waplan.goal.domain.ThirdGoal;
import com.example.waplan.goal.domain.repository.SecondGoalRepository;
import com.example.waplan.goal.domain.repository.ThirdGoalRepository;
import com.example.waplan.goal.exception.*;
import com.example.waplan.user.domain.User;
import com.example.waplan.user.domain.repository.UserRepository;
import com.example.waplan.user.exception.UserException;
import com.example.waplan.user.exception.UserExceptionType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ThirdGoalService {

    private final UserRepository userRepository;
    private final SecondGoalRepository secondGoalRepository;
    private final ThirdGoalRepository thirdGoalRepository;

    public Long addThirdGoal(User user, ThirdGoalAddRequest request){
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        SecondGoal secondGoal = secondGoalRepository.findById(request.getSecondGoalId()).orElseThrow(() -> new SecondGoalException(
                SecondGoalExceptionType.NOT_FOUND_SECONDGOAL));
        ThirdGoal thirdGoal = new ThirdGoal(request.getName(), secondGoal);
        return thirdGoalRepository.save(thirdGoal).getId();
    }

    public void updateName(User user, ThirdGoalUpdateRequest request){
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        ThirdGoal thirdGoal = thirdGoalRepository.findById(request.getThirdGoalId()).orElseThrow(() -> new ThirdGoalException(
                ThirdGoalExceptionType.NOT_FOUND_THIRDGOAL));
        thirdGoal.setName(request.getNewThirdGoal());
    }

}
