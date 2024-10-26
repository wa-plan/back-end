package com.example.dodakki.goal.application;

import com.example.dodakki.Photo.domain.Photo;
import com.example.dodakki.Photo.domain.repository.PhotoRepository;
import com.example.dodakki.goal.application.dto.*;
import com.example.dodakki.goal.domain.*;
import com.example.dodakki.goal.domain.repository.GoalDateMapRepository;
import com.example.dodakki.goal.domain.repository.GoalDateRepository;
import com.example.dodakki.goal.domain.repository.GoalRepository;
import com.example.dodakki.goal.domain.repository.MandalartRepository;
import com.example.dodakki.goal.exception.MandalartException;
import com.example.dodakki.goal.exception.MandalartExceptionType;
import com.example.dodakki.user.domain.User;
import com.example.dodakki.user.domain.repository.UserRepository;
import com.example.dodakki.user.exception.UserException;
import com.example.dodakki.user.exception.UserExceptionType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MandalartService {

    private final MandalartRepository mandalartRepository;
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;
    private final GoalDateRepository goalDateRepository;
    private final GoalRepository goalRepository;
    private final GoalDateMapRepository goalDateMapRepository;

    public Long addMandalart(User user, MandalartAddRequest request) {
        User persistUser = userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
            UserExceptionType.NOT_FOUND_MEMBER));
        Mandalart mandalart = new Mandalart(persistUser, request.getName(), request.getDescription(), request.getColor(), 0L, 0L);
        for(String picture : request.getPicture()) {
            Photo photo = new Photo(picture);
            photoRepository.save(photo);
            mandalart.addPhoto(photo);
        }
        mandalartRepository.save(mandalart);
        Optional<GoalDate> goalDate = goalDateRepository.findByDate(request.getDate());
        if(goalDate.isPresent()) {
            mandalart.setGoalDate(goalDate.get());
        }
        else{
            GoalDate goalDate1 =  goalDateRepository.save(new GoalDate(persistUser, request.getDate()));
            mandalart.setGoalDate(goalDate1);
        }
        return mandalart.getId();
    }

    public Mandalart getMandalart(User user, Long mandalartId) {
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
            UserExceptionType.NOT_FOUND_MEMBER));
        return mandalartRepository.findById(mandalartId).orElseThrow(() -> new MandalartException(
            MandalartExceptionType.NOT_FOUND_MANDALART));
    }

    public Status updateProgress(User user, MandalartProgressRequest request){
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        Mandalart mandalart = mandalartRepository.findById(request.getId()).orElseThrow(() -> new MandalartException(
                MandalartExceptionType.NOT_FOUND_MANDALART));
        mandalart.setStatus(request.getStatus());
        return mandalart.getStatus();
    }
    public BookmarkUpdateResponse updateBookmark(User user, BookmarkUpdateRequest request){
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        Mandalart mandalart = mandalartRepository.findById(request.getId()).orElseThrow(() -> new MandalartException(
                MandalartExceptionType.NOT_FOUND_MANDALART));
        mandalart.setBookmark(request.getBookmark());
        return new BookmarkUpdateResponse(mandalart.getBookmark());
    }

    public void deleteMandalart(User user, Long mandalartId) {
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        mandalartRepository.deleteById(mandalartId);
    }

    public MandalartAllResponse findMandalart(User user, Long mandalartId) {
        userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        Mandalart mandalart = mandalartRepository.findById(mandalartId).orElseThrow(() -> new MandalartException(
                MandalartExceptionType.NOT_FOUND_MANDALART));
        return MandalartAllResponse.of(mandalart);
    }

    public List<MandalartListResponse> getMandalartList(User user) {
        User persistUser = userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        return MandalartListResponse.of(persistUser);
    }

    public MandalartStatusNumResponse getNum(User user, Long mandalartId) {
        User persistUser = userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
                UserExceptionType.NOT_FOUND_MEMBER));
        Mandalart mandalart = mandalartRepository.findById(mandalartId).orElseThrow(() -> new MandalartException(
                MandalartExceptionType.NOT_FOUND_MANDALART));
        int success = 0;
        int inProgress = 0;
        int failed = 0;
        for (Goal goal : mandalart.getGoals()) {
            for (GoalDateMap goalDateMap : goal.getGoalDateMapList()) {
                if (goalDateMap.getAttainment() == Status.SUCCESS){
                    success++;
                }
                else if (goalDateMap.getAttainment() == Status.IN_PROGRESS){
                    inProgress++;
                }
                else if (goalDateMap.getAttainment() == Status.FAIL){
                    failed++;
                }
            }
        }
        return new MandalartStatusNumResponse(success, inProgress, failed);
    }

}
