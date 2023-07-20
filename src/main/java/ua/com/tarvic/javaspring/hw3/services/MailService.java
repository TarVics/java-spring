package ua.com.tarvic.javaspring.hw3.services;

import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.com.tarvic.javaspring.hw3.models.User;

import java.io.File;

@Service
@Configuration
public class MailService {

    @Value("${spring.mail.username}")
    private String username;

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @SneakyThrows
    public void sendEmailToUser(User user, File... files) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setSubject("User registration");
        mimeMessageHelper.setFrom(username);
        mimeMessageHelper.setText(
                "<h1>hello user " + user.getName() + "</h1>" +
                " to activate your account click <a href='http://localhost:8080/users/activate/" +
                user.getId() +
                "'>here</a>",
                true
        );

        for (File file : files) {
            // FileSystemResource fileSystemResource = new FileSystemResource(file);
            // mimeMessageHelper.addAttachment(file.getName(), fileSystemResource);
            mimeMessageHelper.addAttachment(file.getName(), file);
        }

        javaMailSender.send(mimeMessage);

    }
}