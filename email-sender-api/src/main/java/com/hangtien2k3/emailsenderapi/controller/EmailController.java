package com.hangtien2k3.emailsenderapi.controller;

import com.hangtien2k3.emailsenderapi.model.EmailRequest;
import com.hangtien2k3.emailsenderapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/welcome")
    public String welCome() {
        return "hello this is my email api";
    }


    // api to send email
    @PostMapping("/send_email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {

        // this.emailService.sendEmail();
        System.out.println(emailRequest);

        boolean resultEmail =  emailService.sendEmail(emailRequest.getSubject(), emailRequest.getMessage(), emailRequest.getTo());

        if (resultEmail) {
            return ResponseEntity.ok("Email is sent successfully ...");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent.");
        }

    }

}
