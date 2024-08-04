package com.example.waplan.goal.application;

import com.example.waplan.Photo.domain.Photo;
import com.example.waplan.Photo.domain.repository.PhotoRepository;
import com.example.waplan.goal.application.dto.BookmarkUpdateRequest;
import com.example.waplan.goal.application.dto.BookmarkUpdateResponse;
import com.example.waplan.goal.application.dto.MandalartAddRequest;
import com.example.waplan.goal.application.dto.MandalartProgressRequest;
import com.example.waplan.goal.domain.Bookmark;
import com.example.waplan.goal.domain.GoalDate;
import com.example.waplan.goal.domain.Mandalart;
import com.example.waplan.goal.domain.Status;
import com.example.waplan.goal.domain.repository.GoalDateRepository;
import com.example.waplan.goal.domain.repository.MandalartRepository;
import com.example.waplan.goal.exception.MandalartException;
import com.example.waplan.goal.exception.MandalartExceptionType;
import com.example.waplan.user.domain.User;
import com.example.waplan.user.domain.repository.UserRepository;
import com.example.waplan.user.exception.UserException;
import com.example.waplan.user.exception.UserExceptionType;
import jakarta.transaction.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MandalartService {

    private final MandalartRepository mandalartRepository;
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;
    private final GoalDateRepository goalDateRepository;

    public Long addMandalart(User user, MandalartAddRequest request) {
        User persistUser = userRepository.findById(user.getId()).orElseThrow(() -> new UserException(
            UserExceptionType.NOT_FOUND_MEMBER));
        Mandalart mandalart = new Mandalart(persistUser, request.getName(), request.getDescription(), request.getColor(), 0L, Period.between(request.getDate(), LocalDate.now()).getDays(), 0L);
        for(String picture : request.getPicture()) {
            Photo photo = new Photo(picture);
            photoRepository.save(photo);
            mandalart.addPhoto(photo);
        }
        GoalDate goalDate = new GoalDate(request.getDate());
        mandalart.setGoalDate(goalDateRepository.save(goalDate));
        return mandalartRepository.save(mandalart).getId();
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

}
