package com.example.dodakki.goal.api;

import com.example.dodakki.goal.application.MandalartService;
import com.example.dodakki.goal.application.dto.*;
import com.example.dodakki.goal.domain.Mandalart;
import com.example.dodakki.goal.domain.Status;
import com.example.dodakki.security.CurrentUser;
import com.example.dodakki.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mandalart")
public class MandalartApi {

    private final MandalartService mandalartService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> addFirstGoal(@CurrentUser User user, @Valid @RequestBody MandalartAddRequest mandalartAddRequest) {
        Long mandalartId = mandalartService.addMandalart(user, mandalartAddRequest);
        return ResponseEntity.created(URI.create("/api/mandalart/" + mandalartId)).build();
    }

    @GetMapping("/{mandalartId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MandalartResponse> getMandalart(@CurrentUser User user, @PathVariable("mandalartId") Long mandalartId) {
        Mandalart mandalart = mandalartService.getMandalart(user, mandalartId);
        return ResponseEntity.ok(MandalartResponse.of(mandalart, mandalartService.getNum(user, mandalartId)));
    }

    @DeleteMapping("/{mandalartId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMandalart(@CurrentUser User user, @PathVariable("mandalartId") Long mandalartId){
        mandalartService.deleteMandalart(user, mandalartId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/progress")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Status> updateProgress(@CurrentUser User user, @Valid @RequestBody MandalartProgressRequest mandalartProgressRequest){
        Status mandalartStatus = mandalartService.updateProgress(user, mandalartProgressRequest);
        return ResponseEntity.ok(mandalartStatus);
    }

    @PatchMapping("/bookmark")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<BookmarkUpdateResponse> updateBookmark(@CurrentUser User user, @Valid @RequestBody BookmarkUpdateRequest bookmarkUpdateRequest){
        BookmarkUpdateResponse response = mandalartService.updateBookmark(user, bookmarkUpdateRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/{mandalartId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MandalartAllResponse> findMandalartAll(@CurrentUser User user, @Valid @PathVariable("mandalartId") Long mandalartId){
        return ResponseEntity.ok(mandalartService.findMandalart(user, mandalartId));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<MandalartListResponse>> findMandalartList(@CurrentUser User user) {
        return ResponseEntity.ok(mandalartService.getMandalartList(user));
    }
}
