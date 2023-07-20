package ua.com.tarvic.javaspring.lesson1.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender javaMailsender;

    public void send(String email) {
        MimeMessage mimeMessage = javaMailsender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessage.setFrom(new InternetAddress("mr.java2022@gmail.com"));
            helper.setTo(email);
            helper.setText("message", true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailsender.send(mimeMessage);
    }

}
