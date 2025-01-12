package com.example.dodakki.goal.application;

import com.example.dodakki.goal.application.dto.ColorUpdateRequest;
import com.example.dodakki.goal.application.dto.SecondGoalAddRequest;
import com.example.dodakki.goal.application.dto.SecondGoalUpdateRequest;
import com.example.dodakki.goal.domain.Mandalart;
import com.example.dodakki.goal.domain.SecondGoal;
import com.example.dodakki.goal.domain.repository.MandalartRepository;
import com.example.dodakki.goal.domain.repository.SecondGoalRepository;
import com.example.dodakki.goal.exception.MandalartException;
import com.example.dodakki.goal.exception.MandalartExceptionType;
import com.example.dodakki.goal.exception.SecondGoalException;
import com.example.dodakki.goal.exception.SecondGoalExceptionType;
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
public class SecondGoalService {

    private final SecondGoalRepository secondGoalRepository;
    private final UserRepository userRepository;
    private final MandalartRepository mandalartRepository;

    public Long addSecondGoal(User user, SecondGoalAddRequest request){
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        Mandalart mandalart = mandalartRepository.findById(request.getMandalartId()).orElseThrow(() -> new MandalartException(
                MandalartExceptionType.NOT_FOUND_MANDALART));
        SecondGoal secondGoal = new SecondGoal(request.getName(), request.getColor(), mandalart);
        return secondGoalRepository.save(secondGoal).getId();
    }

    public void updateGoalColor(User user, ColorUpdateRequest request){
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        SecondGoal secondGoal = secondGoalRepository.findById(request.getSecondGoalId()).orElseThrow(() -> new SecondGoalException(
                SecondGoalExceptionType.NOT_FOUND_SECONDGOAL));
        secondGoal.setColor(request.getColor());
    }

    public void updateName(User user, SecondGoalUpdateRequest request){
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        SecondGoal secondGoal = secondGoalRepository.findById(request.getSecondGoalId()).orElseThrow(() -> new SecondGoalException(
                SecondGoalExceptionType.NOT_FOUND_SECONDGOAL));
        secondGoal.setName(request.getNewSecondGoal());
    }

    public void deleteGoal(User user, Long secondGoalId){
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        secondGoalRepository.deleteById(secondGoalId);
    }
}
