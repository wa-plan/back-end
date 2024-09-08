package com.example.dodakki.login.application;

import com.example.dodakki.login.domain.EmailMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final LoginService loginService;
    private final SpringTemplateEngine templateEngine;

    public String sendEmail(EmailMessage emailMessage, String type){
        String authNum = createRandomPw();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        loginService.setTempPassword(emailMessage.getTo(), authNum); // 비밀번호 변경

        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo());
            mimeMessageHelper.setSubject(emailMessage.getSubject());
            mimeMessageHelper.setText(setContext(authNum, type), true);
            javaMailSender.send(mimeMessage);
            log.info("이메일 발송 성공");
            return authNum;
        } catch(MessagingException e){
            log.info("이메일 발송 실패");
            throw new RuntimeException(e);
        }
    }
    public String createRandomPw() {
        String[] StringSet = new String[]{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
        };

        String pwd = "";

        int randIndex = 0;
        for(int i=0;i<6;i++){
            randIndex = (int)(StringSet.length * Math.random());
            pwd += StringSet[randIndex];
        }
        return pwd;
    }

    public String setContext(String code, String type) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process(type, context);
    }
}
