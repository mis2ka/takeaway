package com.example.takeaway.order;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private EmailCfg emailCfg;

    //dependencies injection to populate field emailCfg
    public FeedbackController(EmailCfg emailCfg) {
        this.emailCfg = emailCfg;
    }

    @PostMapping
    public void sendFeedback(@RequestBody Feedback feedback,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Informacja zwrotna nie przeszła walidacji");
        }

        // Tworzenie mail sender'a
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());

        // Tworzenie maila
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());
        mailMessage.setTo("256792@student.pwr.edu.pl");
        mailMessage.setSubject("Zamówienie z Nie-gotuj.pl dla " + feedback.getName());
        mailMessage.setText(feedback.getFeedback());

        // Wysylanie maila
        mailSender.send(mailMessage);
    }
}
