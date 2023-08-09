package com.hangtien2k3.emailsenderapi.controller;

import com.hangtien2k3.emailsenderapi.model.EmailRequest;
import com.hangtien2k3.emailsenderapi.service.EmailService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class EmailController {

    private static final String EMAIL_SENT_SUCCESS = "Email is sent successfully ...";
    private static final String EMAIL_NOT_SENT = "Email not sent.";

    @Autowired
    private EmailService emailService;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Hello, this is my email API.");
    }

    @PostMapping("/send_email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        logRequest(emailRequest);

        boolean resultEmail = emailService.sendEmail(emailRequest.getSubject(), emailRequest.getMessage(), emailRequest.getTo());
        if (resultEmail) {
            return ResponseEntity.ok(EMAIL_SENT_SUCCESS);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(EMAIL_NOT_SENT);
        }
    }

    private void logRequest(EmailRequest emailRequest) {
        // Sử dụng hệ thống log thay vì in ra màn hình
        Logger logger = (Logger) LoggerFactory.getLogger(EmailController.class);
        logger.info("Received email request: {}");
    }
}
