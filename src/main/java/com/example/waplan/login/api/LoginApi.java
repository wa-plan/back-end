package com.example.waplan.login.api;

import com.example.waplan.login.application.LoginService;
import com.example.waplan.login.application.dto.FindUserIdRequest;
import com.example.waplan.login.application.dto.ResetPasswordRequest;
import com.example.waplan.login.application.dto.SignUpRequest;
import com.example.waplan.mail.application.MailService;
import com.example.waplan.mail.domain.EmailMessage;
import com.example.waplan.user.application.UserService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class LoginApi {
    private final PasswordEncoder passwordEncoder;

    private final LoginService loginService;
    private final MailService mailService;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signup")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        Long userId = loginService.registerUser(signUpRequest);
        return ResponseEntity.created(URI.create("/api/user/" + userId)).build();
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/find_userId")
    public ResponseEntity<String> findUserId(@RequestBody @Valid FindUserIdRequest findUserIdRequest) {
        String userId = loginService.findUserId(findUserIdRequest);
        return ResponseEntity.ok(userId);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/reset_password")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid ResetPasswordRequest resetPasswordRequest) {
        EmailMessage emailMessage = new EmailMessage(resetPasswordRequest.getEmail(), "[WA-PLAN]비밀번호 재설정 안내");
        String newPassword = mailService.sendEmail(emailMessage, "password");
        return ResponseEntity.ok(newPassword);
    }
}
