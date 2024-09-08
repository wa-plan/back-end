package com.example.dodakki.goal.application;

import com.example.dodakki.goal.application.dto.ThirdGoalAddRequest;
import com.example.dodakki.goal.application.dto.ThirdGoalUpdateRequest;
import com.example.dodakki.goal.domain.SecondGoal;
import com.example.dodakki.goal.domain.ThirdGoal;
import com.example.dodakki.goal.domain.repository.SecondGoalRepository;
import com.example.dodakki.goal.domain.repository.ThirdGoalRepository;
import com.example.dodakki.goal.exception.SecondGoalException;
import com.example.dodakki.goal.exception.SecondGoalExceptionType;
import com.example.dodakki.goal.exception.ThirdGoalException;
import com.example.dodakki.goal.exception.ThirdGoalExceptionType;
import com.example.dodakki.user.domain.User;
import com.example.dodakki.user.domain.repository.UserRepository;
import com.example.dodakki.user.exception.UserException;
import com.example.dodakki.user.exception.UserExceptionType;
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
