package evenementen.security.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(List<String> emails, String body, String subject) {

        SimpleMailMessage message = new SimpleMailMessage();

        for (String email : emails) {
            message.setFrom("evenementen.service@gmail.com");
            message.setTo(email);
            message.setText(body);
            message.setSubject(subject);

            mailSender.send(message);
        }
    }
}
